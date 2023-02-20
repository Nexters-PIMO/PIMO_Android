package com.nexters.pimo.ui.upload

import android.graphics.Color
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoDialog
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.component.FimoToast
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.model.TextBitmap
import com.nexters.pimo.ui.state.UiState
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun UploadScreen(
    viewModel: UploadViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val state = viewModel.collectAsState().value

    var showToast by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    viewModel.collectSideEffect {
        when (it) {
            UploadSideEffect.ShowNonTextImageToast -> {
                showToast = true
            }
        }
    }

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let {
                val bitmap =
                    if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        ImageDecoder.decodeBitmap(source)
                    }
                viewModel.onPickImage(result.getUriFilePath(context, false)!!, bitmap)
            }
        }
    }

    val imageCropperOptions = CropImageOptions(
        cropShape = CropImageView.CropShape.RECTANGLE,
        fixAspectRatio = true,
        aspectRatioX = 1,
        aspectRatioY = 1,
        toolbarColor = Color.WHITE,
        toolbarBackButtonColor = Color.BLACK,
        toolbarTintColor = Color.BLACK,
        allowFlipping = false,
        allowRotation = false,
        cropMenuCropButtonTitle = context.getString(R.string.done),
        imageSourceIncludeCamera = false
    )

    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            val cropOptions = CropImageContractOptions(uri, imageCropperOptions)
            imageCropLauncher.launch(cropOptions)
        }

    BackHandler {
        showDialog = true
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        FimoSimpleAppBar(
            backIconRes = R.drawable.ic_close,
            onBack = { showDialog = true },
            titleStringRes = when (state.mode) {
                UploadState.Mode.New -> R.string.new_post
                UploadState.Mode.Edit -> R.string.edit_post
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp, bottom = 60.dp)
        ) {
            ImageBar(
                images = state.textBitmaps,
                pickImage = { imagePickerLauncher.launch("image/*") },
                selectedIndex = state.selectedIndex,
                selectImage = viewModel::selectImage,
                removeImage = viewModel::removeImage
            )
            Spacer(modifier = Modifier.height(40.dp))
            with(state.textBitmaps) {
                if (isNotEmpty()) {
                    Image(
                        bitmap = get(state.selectedIndex).bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    ImagePlaceholder(modifier = Modifier.fillMaxWidth())
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onBack,
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(2.dp),
                enabled = state.textBitmaps.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(id = R.string.post),
                    style = FimoTheme.typography.medium.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.white
                    )
                )
            }
        }
    }
    AnimatedVisibility(
        visible = state.uiState == UiState.Loading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Loading(modifier = Modifier.fillMaxSize())
    }
    FimoDialog(
        visible = showDialog,
        titleRes = R.string.edit_post_dialog,
        subtitleRes = R.string.edit_post_dialog_sub,
        leftStringRes = R.string.cancel,
        rightStringRes = R.string.exit,
        onLeftClick = { showDialog = false },
        onRightClick = { onBack() },
        onDismiss = { showDialog = false }
    )
    FimoToast(
        visible = showToast,
        modifier = Modifier.padding(bottom = 24.dp),
        titleRes = R.string.non_text_image_toast,
        subtitleRes = R.string.non_text_image_toast_sub,
        onDismiss = { showToast = false }
    )
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = FimoTheme.colors.black.copy(alpha = 0.5f)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ImageBar(
    images: List<TextBitmap>,
    pickImage: () -> Unit,
    selectedIndex: Int,
    selectImage: (Int) -> Unit,
    removeImage: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box {
                Button(
                    onClick = { if (images.size < MAX_IMAGE_NUM) pickImage() },
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FimoTheme.colors.greyF7),
                    contentPadding = PaddingValues(horizontal = 23.dp),
                    interactionSource = NoRippleInteractionSource,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                        .size(72.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_image),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = FimoTheme.colors.black)) {
                                    append(images.size.toString())
                                }
                                withStyle(style = SpanStyle(color = FimoTheme.colors.grey7F)) {
                                    append("/$MAX_IMAGE_NUM")
                                }
                            },
                            style = FimoTheme.typography.medium.copy(fontSize = 16.sp)
                        )
                    }
                }
                FilledIconButton(
                    onClick = pickImage,
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = FimoTheme.colors.greyEF,
                        contentColor = FimoTheme.colors.grey7F
                    ),
                    interactionSource = NoRippleInteractionSource,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_image),
                        contentDescription = null,
                        tint = FimoTheme.colors.grey7F,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        items(images.size) { index ->
            Box {
                Button(
                    onClick = { selectImage(index) },
                    shape = RoundedCornerShape(2.dp),
                    border = BorderStroke(
                        width = if (selectedIndex == index) 2.dp else 0.dp,
                        color = if (selectedIndex == index) {
                            FimoTheme.colors.primary
                        } else {
                            FimoTheme.colors.white
                        }
                    ),
                    interactionSource = NoRippleInteractionSource,
                    colors = ButtonDefaults.buttonColors(containerColor = FimoTheme.colors.white),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 12.dp)
                        .size(72.dp)
                ) {
                    Image(
                        bitmap = images[index].bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(72.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                }
                FilledIconButton(
                    onClick = { removeImage(index) },
                    shape = CircleShape,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = FimoTheme.colors.black,
                        contentColor = FimoTheme.colors.white
                    ),
                    interactionSource = NoRippleInteractionSource,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close_tooltip),
                        contentDescription = null,
                        tint = FimoTheme.colors.white,
                        modifier = Modifier.size(13.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.then(Modifier.aspectRatio(1f)),
        shape = RoundedCornerShape(4.dp),
        color = FimoTheme.colors.greyF7
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_logo_grey),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp)
                )
                Text(
                    text = stringResource(id = R.string.thumbnail),
                    style = FimoTheme.typography.medium.copy(
                        fontSize = 19.sp,
                        color = FimoTheme.colors.black
                    )
                )
                Text(
                    text = stringResource(id = R.string.thumbnail_help),
                    style = FimoTheme.typography.light.copy(
                        fontSize = 14.sp,
                        color = FimoTheme.colors.grey7F
                    )
                )
            }
        }
    }
}

private fun handleSideEffect(sideEffect: UploadSideEffect) {
    when (sideEffect) {
        UploadSideEffect.ShowNonTextImageToast -> {

        }
    }
}

private const val MAX_IMAGE_NUM = 5

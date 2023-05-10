package com.nexters.pimo.ui.profile

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoDialog
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.component.FimoToast
import com.nexters.pimo.ui.component.NoRippleInteractionSource
import com.nexters.pimo.ui.component.ProfileTextField
import com.nexters.pimo.ui.profile.state.Mode
import com.nexters.pimo.ui.profile.state.ProfileState
import com.nexters.pimo.ui.profile.state.TextFieldState
import com.nexters.pimo.ui.profile.state.TextFieldState.Companion.MAX_LENGTH_EN
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val profileState = viewModel.collectAsState().value

    if (profileState.mode == Mode.New) {
        when (profileState.pageIdx) {
            0, 1 -> ProfileAddText(viewModel, profileState)
            2 -> ProfileAddImage(viewModel, profileState)
            3 -> ProfileAddComplete(viewModel)
        }
    } else {
        ProfileEdit(viewModel, profileState, onBack)
    }
}

@Composable
fun ProfileEdit(viewModel: ProfileViewModel, profileState: ProfileState, onBack: () -> Unit) {
    var showToast by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

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
                viewModel.onPickImage(bitmap)
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
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        FimoSimpleAppBar(
            backIconRes = R.drawable.ic_back,
            onBack = { showDialog = true },
            titleText = stringResource(id = R.string.profile_edit)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 55.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .width(132.dp)
                        .height(127.dp)
                        .align(Alignment.Center),
                ) {
                    ProfileImagePlaceholder(
                        viewModel, modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .align(Alignment.Center), profileState = profileState
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(32.dp)
                            .aspectRatio(1f)
                            .background(FimoTheme.colors.primary, shape = CircleShape)
                            .clickable(
                                onClick = { imagePickerLauncher.launch("image/*") },
                                interactionSource = NoRippleInteractionSource,
                                indication = null,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(16.dp),
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = null,
                            tint = FimoTheme.colors.white
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(46.dp))
            Text(
                text = stringResource(id = R.string.profile_nickname),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 16.sp,
                    color = FimoTheme.colors.black,
                ),
            )
            Spacer(modifier = Modifier.height(11.5.dp))
            ProfileTextField(
                viewModel = viewModel,
                profileState = profileState,
                textFieldState = profileState.nicknameState,
                trailingIcon = {
                    ProfileTrailingIcon(textFieldState = profileState.nicknameState,
                        onClick = { viewModel.checkNickname() })
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.height(14.dp),
            ) {
                if (profileState.nicknameState.isDuplicateChecked) {
                    Text(
                        text = stringResource(profileState.nicknameState.inputCheckMsg),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 12.sp,
                            color = if (profileState.nicknameState.isValidInput) {
                                FimoTheme.colors.black
                            } else {
                                FimoTheme.colors.primary
                            },
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = stringResource(id = R.string.profile_archive_name),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 16.sp,
                    color = FimoTheme.colors.black,
                ),
            )
            Spacer(modifier = Modifier.height(11.5.dp))
            ProfileTextField(
                viewModel = viewModel,
                profileState = profileState,
                textFieldState = profileState.archiveNameState,
                trailingIcon = {
                    ProfileTrailingIcon(textFieldState = profileState.archiveNameState,
                        onClick = { viewModel.checkArchiveName() })
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.height(14.dp),
            ) {
                if (profileState.archiveNameState.isDuplicateChecked) {
                    Text(
                        text = stringResource(profileState.archiveNameState.inputCheckMsg),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 12.sp,
                            color = if (profileState.archiveNameState.isValidInput) {
                                FimoTheme.colors.black
                            } else {
                                FimoTheme.colors.primary
                            },
                        ),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 60.dp)
            ) {
                Button(
                    onClick = {
                        showToast = true
                    },
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .background(FimoTheme.colors.primaryDark)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(2.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 16.sp,
                            color = FimoTheme.colors.white
                        )
                    )
                }
            }
        }
    }
    FimoDialog(
        visible = showDialog,
        title = stringResource(id = R.string.profile_edit_exit_title),
        subtitle = stringResource(id = R.string.profile_edit_exit_subtitle),
        leftStringRes = R.string.cancel,
        rightStringRes = R.string.exit,
        onLeftClick = { showDialog = false },
        onRightClick = { onBack() },
        onDismiss = { showDialog = false }
    )
    FimoToast(
        visible = showToast,
        modifier = Modifier.padding(bottom = 24.dp),
        titleRes = R.string.profile_edit_complete,
        subtitleRes = null,
        onDismiss = { showToast = false }
    )
}

@Composable
fun ProfileAddText(viewModel: ProfileViewModel, profileState: ProfileState) {
    val textFieldState: TextFieldState
    if (profileState.pageIdx == 0) {
        textFieldState = profileState.nicknameState
    } else {
        textFieldState = profileState.archiveNameState
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        AddProfileTopBar(pageIdx = profileState.pageIdx, onBack = viewModel::goBack)
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(42.dp))
            Text(
                text = if (profileState.pageIdx == 0) {
                    stringResource(id = R.string.profile_add_nickname_title)
                } else {
                    stringResource(id = R.string.profile_add_archive_title)
                },
                style = FimoTheme.typography.semibold.copy(
                    fontSize = 20.sp,
                    color = FimoTheme.colors.black,
                ),
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(id = R.string.profile_input_msg),
                style = FimoTheme.typography.medium.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.grey7F,
                ),
            )
            Spacer(modifier = Modifier.height(32.dp))
            ProfileTextField(
                viewModel = viewModel,
                profileState = profileState,
                textFieldState = textFieldState,
                trailingIcon = {
                    ProfileTrailingIcon(textFieldState = textFieldState,
                        onClick = if (profileState.pageIdx == 0) {
                            { viewModel.checkNickname() }
                        } else {
                            { viewModel.checkArchiveName() }
                        })
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier.height(14.dp),
            ) {
                if (textFieldState.isDuplicateChecked) {
                    Text(
                        text = stringResource(textFieldState.inputCheckMsg),
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 12.sp,
                            color = if (textFieldState.isValidInput) {
                                FimoTheme.colors.black
                            } else {
                                FimoTheme.colors.primary
                            },
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(66.dp))
            ProfileNextButton(textFieldState = textFieldState, goForward = viewModel::goForward)
        }
    }
}

@Composable
fun ProfileAddImage(viewModel: ProfileViewModel, profileState: ProfileState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        AddProfileTopBar(pageIdx = profileState.pageIdx, onBack = viewModel::goBack)
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(42.dp))
            Text(
                text = stringResource(id = R.string.profile_add_image_title),
                style = FimoTheme.typography.semibold.copy(
                    fontSize = 20.sp,
                    color = FimoTheme.colors.black,
                ),
            )
            Spacer(modifier = Modifier.height(44.dp))
            ProfileImagePlaceholder(
                viewModel,
                modifier = Modifier.fillMaxWidth(),
                profileState = profileState
            )
            Spacer(modifier = Modifier.height(82.dp))
            ProfileCompleteButton(
                imageState = profileState.imageState,
                goForward = viewModel::goForward
            )
        }
    }
}

@Composable
fun ProfileImagePlaceholder(
    viewModel: ProfileViewModel,
    modifier: Modifier = Modifier,
    profileState: ProfileState
) {
    val context = LocalContext.current

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
                viewModel.onPickImage(bitmap)
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

    Surface(
        modifier = modifier
            .then(Modifier.aspectRatio(1f))
            .clickable(
                onClick = {
                    if (profileState.mode == Mode.New) {
                        imagePickerLauncher.launch("image/*")
                    }
                },
                interactionSource = NoRippleInteractionSource,
                indication = null,
            ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = FimoTheme.colors.greyD9
        )
    ) {
        if (profileState.imageState != null) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    bitmap = profileState.imageState.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop,
                )
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = .99f
                        }
                ) {
                    drawRect(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f))
                    drawCircle(
                        androidx.compose.ui.graphics.Color.Transparent,
                        blendMode = BlendMode.Clear
                    )
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_photo),
                    contentDescription = null,
                    modifier = Modifier.width(20.dp)
                )
            }
        }
    }

}

@Composable
fun ProfileAddComplete(viewModel: ProfileViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
            .padding(horizontal = 20.dp)
            .padding(top = 56.dp, bottom = 60.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(44.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.profile_add_complete_title),
                style = FimoTheme.typography.bold.copy(
                    fontSize = 28.sp,
                    color = FimoTheme.colors.black,
                ),
            )
            Spacer(modifier = Modifier.height(67.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .width(20.dp),
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.profile_add_complete_msg_1_1),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.black,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = stringResource(id = R.string.profile_add_complete_msg_1_2),
                style = FimoTheme.typography.light.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.black
                ),
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .background(FimoTheme.colors.greyD9)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .width(20.dp),
                    painter = painterResource(id = R.drawable.ic_export),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.profile_add_complete_msg_2_1),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.black,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = stringResource(id = R.string.profile_add_complete_msg_2_2),
                style = FimoTheme.typography.light.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.black
                ),
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .background(FimoTheme.colors.greyD9)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .width(20.dp),
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.profile_add_complete_msg_3_1),
                    style = FimoTheme.typography.semibold.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.black,
                    ),
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = stringResource(id = R.string.profile_add_complete_msg_3_2),
                style = FimoTheme.typography.light.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.black
                ),
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(153.dp))
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileStartButton(goForward = viewModel::goForward)
        }
    }
}

@Composable
fun ProfileTrailingIcon(textFieldState: TextFieldState, onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(end = 20.dp)
            .clickable(
                onClick = if (textFieldState.text.isNotEmpty()) {
                    onClick
                } else {
                    ({})
                },
                interactionSource = NoRippleInteractionSource,
                indication = null,
            ),
        text = stringResource(id = R.string.profile_input_duplicate_check),
        style = FimoTheme.typography.medium.copy(
            fontSize = 16.sp,
            color = if (textFieldState.text.isNotEmpty()) {
                FimoTheme.colors.black
            } else {
                FimoTheme.colors.grey7F
            },
        ),
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun ProfileNextButton(textFieldState: TextFieldState, goForward: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        color = if (textFieldState.isValidInput) {
            FimoTheme.colors.primaryDark
        } else {
            FimoTheme.colors.greyD9
        },
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .clickable(
                onClick = if (textFieldState.isValidInput) {
                    goForward
                } else {
                    ({})
                },
                interactionSource = NoRippleInteractionSource,
                indication = null,
            )
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.next),
                color = FimoTheme.colors.white,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ProfileCompleteButton(imageState: Bitmap?, goForward: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        color = if (imageState == null) {
            FimoTheme.colors.greyD9
        } else {
            FimoTheme.colors.primaryDark
        },
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .clickable(
                onClick = if (imageState != null) {
                    goForward
                } else {
                    ({})
                },
                interactionSource = NoRippleInteractionSource,
                indication = null,
            )
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.done),
                color = FimoTheme.colors.white,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ProfileStartButton(goForward: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        color = FimoTheme.colors.primaryDark,
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .clickable(
                onClick = goForward,
                interactionSource = NoRippleInteractionSource,
                indication = null,
            )
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.start),
                color = FimoTheme.colors.white,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ProfileTextField(
    viewModel: ProfileViewModel,
    profileState: ProfileState,
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)
) {
    Column(modifier = modifier) {
        ProfileTextField(
            value = textFieldState.text,
            onValueChange = {
                textFieldState.text = it
                if (profileState.pageIdx == 0) {
                    viewModel.initNicknameState()
                } else {
                    viewModel.initArchiveNameState()
                }
            },
            modifier = Modifier
                .onFocusChanged { textFieldState.onFocusChange(it.isFocused) },
            placeholder = stringResource(id = R.string.profile_input_hint),
            isError = textFieldState.isDuplicateChecked && !textFieldState.isValidInput,
            counterMaxLength = MAX_LENGTH_EN,
            trailingIcon = trailingIcon
        )
    }
}

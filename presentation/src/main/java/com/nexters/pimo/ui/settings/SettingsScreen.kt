package com.nexters.pimo.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.CircleImage
import com.nexters.pimo.ui.component.FimoDialog
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.theme.FimoTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBack: () -> Unit,
    startOnboardActivity: () -> Unit,
    startProfileActivity: () -> Unit,
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val state = viewModel.collectAsState().value

    var dialogState by remember { mutableStateOf(DialogState(false, R.string.setting_logout, R.string.setting_logout_dialog_subtitle, R.string.cancel, R.string.setting_logout)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        FimoSimpleAppBar(
            backIconRes = R.drawable.ic_back,
            onBack = onBack,
            titleText = stringResource(id = R.string.settings)
        )
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.padding(vertical = 24.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    CircleImage(
                        imageSource = state.user.profileImageUrl,
                        size = 52.dp
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    Text(
                        text = state.user.nickname,
                        style = FimoTheme.typography.medium.copy(
                            fontSize = 16.sp,
                            color = FimoTheme.colors.black
                        )
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = state.user.archiveName,
                        style = FimoTheme.typography.regular.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF818284)
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .width(92.dp)
                            .height(32.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = startProfileActivity
                            ),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(
                            width = 1.dp,
                            color = FimoTheme.colors.greyD9
                        ),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.setting_profile),
                                style = FimoTheme.typography.regular.copy(
                                    fontSize = 14.sp,
                                    color = FimoTheme.colors.grey7F
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .background(FimoTheme.colors.greyD9)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {}
            Text(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 37.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = startOnboardActivity
                    ),
                text = stringResource(id = R.string.setting_guide),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.black
                )
            )
            Text(
                text = stringResource(id = R.string.setting_source_license),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.black
                )
            )
            Text(
                modifier = Modifier
                    .padding(top = 37.dp, bottom = 25.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = { uriHandler.openUri(SettingsViewModel.PRIVACY_POLICY_URL) }
                    ),
                text = stringResource(id = R.string.setting_privacy_policy),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.black
                )
            )
            Box(
                modifier = Modifier
                    .background(FimoTheme.colors.greyD9)
                    .height(1.dp)
                    .fillMaxWidth()
            ) {}
            Text(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 37.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            dialogState = DialogState(
                                visible = true,
                                title = R.string.setting_logout,
                                subtitle = R.string.setting_logout_dialog_subtitle,
                                leftStringRes = R.string.cancel,
                                rightStringRes = R.string.setting_logout
                            )
                        }
                    ),
                text = stringResource(id = R.string.setting_logout),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 18.sp,
                    color = FimoTheme.colors.black
                )
            )
            Row() {
                Text(
                    text = stringResource(id = R.string.setting_version),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 18.sp,
                        color = FimoTheme.colors.black
                    )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        text = context.packageManager.getPackageInfo(context.packageName, 0).versionName,
                        style = FimoTheme.typography.regular.copy(
                            fontSize = 14.sp,
                            color = FimoTheme.colors.grey7F
                        )
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 150.dp, start = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            dialogState = DialogState(
                                visible = true,
                                title = R.string.setting_withdrawal_dialog_title,
                                subtitle = R.string.setting_withdrawal_dialog_subtitle,
                                leftStringRes = R.string.setting_withdraw,
                                rightStringRes = R.string.cancel
                            )
                        }
                    ),
                text = stringResource(id = R.string.setting_withdrawal),
                style = FimoTheme.typography.regular.copy(
                    fontSize = 14.sp,
                    color = FimoTheme.colors.grey7F
                ),
                textDecoration = TextDecoration.Underline
            )
        }
    }
    FimoDialog(
        visible = dialogState.visible,
        contentPadding = PaddingValues(top = 47.dp, bottom = 29.dp),
        title = stringResource(dialogState.title),
        subtitle = stringResource(dialogState.subtitle),
        leftStringRes = dialogState.leftStringRes,
        rightStringRes = dialogState.rightStringRes,
        onLeftClick = {
            if(dialogState.leftStringRes == R.string.cancel) {
                dialogState = DialogState(visible = false, dialogState.title, dialogState.subtitle, dialogState.leftStringRes, dialogState.rightStringRes)
            }
        },
        onRightClick = {
            if(dialogState.rightStringRes == R.string.cancel) {
                dialogState = DialogState(visible = false, dialogState.title, dialogState.subtitle, dialogState.leftStringRes, dialogState.rightStringRes)

            }
        },
        onDismiss = { dialogState = DialogState(visible = false, dialogState.title, dialogState.subtitle, dialogState.leftStringRes, dialogState.rightStringRes) },
    )
}
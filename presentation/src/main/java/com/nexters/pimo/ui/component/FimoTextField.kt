package com.nexters.pimo.ui.component

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.theme.FimoTheme

private val TextFieldMinHeight = 56.dp

@Composable
fun FimoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle,
    placeholderTextStyle: TextStyle,
    placeholder: String = "",
    helperTextEnabled: Boolean = false,
    helperText: String = "",
    counterEnabled: Boolean = false,
    counterMaxLength: Int = 0,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    minHeight: Dp = TextFieldMinHeight,
    maxHeight: Dp = minHeight,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingIcon: @Composable (() -> Unit),
) {
    val focusManager = LocalFocusManager.current
    val colors = FimoTextFieldDefaults.colors

    Column(modifier = modifier) {

        if (helperTextEnabled || counterEnabled) {
            FimoTextFieldHelper(
                modifier = Modifier.fillMaxWidth(),
                helperText = helperText,
                helperTextEnabled = helperTextEnabled,
                color1 = FimoTheme.colors.grey7F,
                color2 = FimoTheme.colors.primary,
                counterLength = value.length,
                counterMaxLength = counterMaxLength,
                counterEnabled = counterEnabled,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        BasicTextField(
            value = value,
            onValueChange = {
                val newValue = if (counterMaxLength > 0) {
                    it.take(counterMaxLength)
                } else {
                    it
                }
                onValueChange(newValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = minHeight, max = maxHeight),
            enabled = enabled,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions { focusManager.clearFocus() },
            singleLine = singleLine,
            maxLines = maxLines,
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            decorationBox = @Composable { innerTextField ->
                FimoTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    isError = isError,
                    singleLine = singleLine,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    placeholder = placeholder,
                    placeholderTextStyle = placeholderTextStyle,
                    colors = colors,
                    trailingIcon = trailingIcon,
                )
            }
        )
    }
}

@Composable
fun ProfileTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    helperTextEnabled: Boolean = false,
    helperText: String = "",
    counterEnabled: Boolean = false,
    counterMaxLength: Int = 0,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    trailingIcon: @Composable (() -> Unit),
) {
    FimoTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = FimoTheme.typography.medium.copy(
            fontSize = 16.sp,
            color = FimoTheme.colors.black,
        ),
        placeholderTextStyle = FimoTheme.typography.medium.copy(
            fontSize = 16.sp,
            color = FimoTheme.colors.greyD9,
        ),
        placeholder = placeholder,
        helperTextEnabled = helperTextEnabled,
        helperText = helperText,
        counterEnabled = counterEnabled,
        counterMaxLength = counterMaxLength,
        enabled = enabled,
        isError = isError,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        trailingIcon = trailingIcon
    )
}

@Composable
private fun FimoTextFieldHelper(
    modifier: Modifier = Modifier,
    color1: Color,
    color2: Color,
    helperTextEnabled: Boolean,
    helperText: String,
    counterEnabled: Boolean,
    counterLength: Int,
    counterMaxLength: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        if (helperTextEnabled) {
            Text(
                text = helperText,
                modifier = Modifier
                    .weight(1f),
                style = FimoTheme.typography.regular,
                color = color1,
            )
        }
        if (helperTextEnabled && counterEnabled) {
            Spacer(modifier = Modifier.width(10.dp))
        }
        if (counterEnabled) {
            Text(
                text = "${counterLength}/",
                style = FimoTheme.typography.regular,
                color = color1,
            )
            Text(
                text = "${counterMaxLength}",
                style = FimoTheme.typography.regular,
                color = color2,
            )
        }
    }
}

@Immutable
object FimoTextFieldDefaults {
    private val BorderThickness = 1.dp
    private val ContentPadding = PaddingValues(horizontal = 20.dp)
    private val TextFieldShape = RoundedCornerShape(0.dp)

    @OptIn(ExperimentalMaterial3Api::class)
    val colors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            placeholderColor = FimoTheme.colors.greyD9,
            textColor = FimoTheme.colors.black,
            unfocusedBorderColor = FimoTheme.colors.greyD9,
            focusedBorderColor = FimoTheme.colors.greyD9,
            errorBorderColor = FimoTheme.colors.primary,
            errorCursorColor = FimoTheme.colors.primary,
            errorLabelColor = FimoTheme.colors.primary,
            unfocusedLabelColor = FimoTheme.colors.grey7F,
            focusedLabelColor = FimoTheme.colors.black,
            cursorColor = FimoTheme.colors.black,
        )

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun DecorationBox(
        value: String,
        innerTextField: @Composable () -> Unit,
        enabled: Boolean,
        singleLine: Boolean,
        visualTransformation: VisualTransformation,
        interactionSource: InteractionSource,
        isError: Boolean,
        placeholder: String,
        placeholderTextStyle: TextStyle,
        colors: TextFieldColors,
        trailingIcon: @Composable (() -> Unit),
    ) {
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            innerTextField = innerTextField,
            placeholder = {
                if (value.isEmpty()) {
                    Text(text = placeholder, style = placeholderTextStyle)
                }
            },
            singleLine = singleLine,
            isError = isError,
            colors = colors,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = ContentPadding,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
        )
    }
}


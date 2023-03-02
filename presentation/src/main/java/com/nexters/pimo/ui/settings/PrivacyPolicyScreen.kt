package com.nexters.pimo.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nexters.pimo.ui.R
import com.nexters.pimo.ui.component.FimoSimpleAppBar
import com.nexters.pimo.ui.theme.FimoTheme

@Composable
fun PrivacyPolicyScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FimoTheme.colors.white)
    ) {
        FimoSimpleAppBar(
            backIconRes = R.drawable.ic_back,
            onBack = onBack,
            titleText = stringResource(id = R.string.setting_privacy_policy)
        )

        LazyColumn(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.privacy_policy_content),
                    style = FimoTheme.typography.regular.copy(
                        fontSize = 16.sp,
                        color = FimoTheme.colors.black
                    ),
                    lineHeight = 30.sp
                )
            }
        }
    }

}
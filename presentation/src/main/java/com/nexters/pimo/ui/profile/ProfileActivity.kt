package com.nexters.pimo.ui.profile

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.ui.base.BaseActivity
import com.nexters.pimo.ui.main.MainActivity
import com.nexters.pimo.ui.profile.state.Mode
import com.nexters.pimo.ui.profile.state.ProfileSideEffect
import com.nexters.pimo.ui.profile.state.ProfileState
import com.nexters.pimo.ui.theme.FimoTheme
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileViewModel.observe(
            lifecycleOwner = this,
            state = ::handleState,
            sideEffect = ::handleSideEffect
        )

        setContent {
            FimoTheme {
                ProfileScreen(
                    onBack = { finish() },
                )
            }
        }
    }

    private fun handleState(state: ProfileState) {
        when (state.loginResult) {
            is LoginResult.SignedIn -> {
                startMainActivity()
            }

            else -> {}
        }
    }

    private fun handleSideEffect(sideEffect: ProfileSideEffect) {
    }

    private fun startMainActivity() {
        MainActivity.startActivity(this)
        finish()
    }

    companion object {
        fun getIntent(context: Context, mode: Mode, provider: String, identifier: String) =
            Intent(context, ProfileActivity::class.java)
                .addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra(ProfileViewModel.KEY_MODE, mode)
                .putExtra(ProfileViewModel.EXTRA_KEY_PROVIDER, provider)
                .putExtra(ProfileViewModel.EXTRA_KEY_IDENTIFIER, identifier)
    }
}

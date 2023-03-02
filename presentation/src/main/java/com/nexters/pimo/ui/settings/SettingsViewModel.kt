package com.nexters.pimo.ui.settings

import com.nexters.pimo.domain.model.User
import com.nexters.pimo.ui.base.BaseViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
) : ContainerHost<SettingsState, SettingsSideEffect>,
    BaseViewModel() {

    override val container = container<SettingsState, SettingsSideEffect>(SettingsState())

    val tempUser = User(
        id = 0,
        profileImageUrl = "https://avatars.githubusercontent.com/u/72238126?v=4",
        nickname = "yjyoon"
    )

    init {
        intent {
            reduce {
                state.copy(
                    user = tempUser,
                )
            }
        }
    }

}
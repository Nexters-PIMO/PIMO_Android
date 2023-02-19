package com.nexters.pimo.domain.usecase

import com.nexters.pimo.domain.model.LoginResult
import com.nexters.pimo.domain.model.ProviderToken
import com.nexters.pimo.domain.repository.AuthRepository
import com.nexters.pimo.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(

) {
    suspend operator fun invoke(
        providerToken: ProviderToken
    ): Result<LoginResult> = runCatching {
       //TODO: 서버로 토큰 보내 jwt 토큰 받아서, preference에 저장
        LoginResult.Onboard
    }
}

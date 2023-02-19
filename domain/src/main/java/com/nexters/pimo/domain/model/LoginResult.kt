package com.nexters.pimo.domain.model

sealed interface LoginResult {
    //처음 로그인 X
    object Signed : LoginResult
    //처음 로그인 O
    object SignedFirst : LoginResult
}

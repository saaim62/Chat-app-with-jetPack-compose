package com.example.mapBoxAndOneSignal.domain.usecase.authScreen

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticatedInFirebase,
    val signIn: SignIn,
    val signUp: SignUp,
)
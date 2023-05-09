package com.app.features.home.profile.presentation

import com.google.firebase.auth.FirebaseUser

sealed class ProfileState {
    data class Success(val profileUri: FirebaseUser?) : ProfileState()
    object FinishAffinity : ProfileState()

}
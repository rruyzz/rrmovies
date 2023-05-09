package com.app.features.home.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.features.home.home.presentation.fragment.HomeState
import com.app.features.home.profile.domain.usecase.ProfilePictureUseCase
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel(
    private val useCaseProfile: ProfilePictureUseCase,
    private val oneTapClient: SignInClient
): ViewModel() {

    private val _profileState = MutableSharedFlow<ProfileState>(0)
    val profileState = _profileState.asSharedFlow()

    init {
        getProfileImage()
    }
    private fun getProfileImage() = viewModelScope.launch(Dispatchers.IO) {
        useCaseProfile()
            .collect {
                _profileState.emit(ProfileState.Success(it))
            }
    }

    fun signOut() = viewModelScope.launch(Dispatchers.IO) {
        Firebase.auth.signOut()
        oneTapClient.signOut().await()
        _profileState.emit(ProfileState.FinishAffinity)
    }

}
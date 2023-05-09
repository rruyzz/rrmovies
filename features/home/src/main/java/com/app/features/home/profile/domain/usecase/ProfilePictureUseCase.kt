package com.app.features.home.profile.domain.usecase

import android.net.Uri
import com.app.features.home.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class ProfilePictureUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke(): Flow<FirebaseUser?> {
        return repository.getProfilePicture()
    }
}
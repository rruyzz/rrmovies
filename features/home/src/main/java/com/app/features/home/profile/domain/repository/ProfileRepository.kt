package com.app.features.home.profile.domain.repository

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getProfilePicture(): Flow<FirebaseUser?>

}
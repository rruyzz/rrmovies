package com.app.features.home.profile.data.repository

import android.net.Uri
import com.app.features.home.profile.domain.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProfileRepositoryImpl: ProfileRepository {

    override fun getProfilePicture(): Flow<FirebaseUser?> {
        var profilePic = FirebaseAuth.getInstance().currentUser
        return flow {
            emit(profilePic)
        }

    }
}
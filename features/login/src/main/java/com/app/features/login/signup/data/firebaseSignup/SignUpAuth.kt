package com.app.features.login.signup.data.firebaseSignup

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SignUpAuth {

    private var auth = Firebase.auth

    suspend fun createUser(email: String, password: String): AuthResult {
        val something: AuthResult = try {
            auth.createUserWithEmailAndPassword(email, password).await()
        } catch (exception: Exception) {
            throw exception
        }
        return something
    }

    suspend fun loginEmail(email: String, password: String): AuthResult {
        val something: AuthResult = try {
            auth.signInWithEmailAndPassword(email, password).await()
        } catch (exception: Exception) {
            throw exception
        }
        return something
    }

}
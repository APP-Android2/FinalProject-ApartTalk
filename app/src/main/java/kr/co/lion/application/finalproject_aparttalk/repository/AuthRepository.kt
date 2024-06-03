package kr.co.lion.application.finalproject_aparttalk.repository

import android.content.Context
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.auth.GoogleCredentialManagerService

class AuthRepository(private val firebaseAuthService: FirebaseAuthService) {

    fun getCurrentUser() = firebaseAuthService.getCurrentUser()

    fun signOut() = firebaseAuthService.signOut()

    suspend fun signInWithGoogle(googleAccount: GoogleIdTokenCredential): AuthResult {
        return firebaseAuthService.signInWithGoogle(googleAccount)
    }

    suspend fun getGoogleCredential(context: Context): GoogleIdTokenCredential? {
        val googleCredentialManagerService = GoogleCredentialManagerService()
        return googleCredentialManagerService.getGoogleCredential(context)
    }
}
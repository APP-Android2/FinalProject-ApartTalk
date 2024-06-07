package kr.co.lion.application.finalproject_aparttalk.auth

import android.util.Log
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {

    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun signOut() {
        firebaseAuth.signOut()
    }

    suspend fun signInWithGoogle(googleAccount: GoogleIdTokenCredential): AuthResult? {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        return try {
            firebaseAuth.signInWithCredential(credential).await()
        } catch (e: FirebaseAuthUserCollisionException) {
            handleUserCollision(credential)
        }
    }

    suspend fun signInWithKaKao(oAuthToken: OAuthToken): AuthResult? {
        val providerId = "oidc.aparttalk"
        val credential = OAuthProvider.newCredentialBuilder(providerId).setIdToken(oAuthToken.idToken).build()
        return try {
            firebaseAuth.signInWithCredential(credential).await()
        } catch (e: FirebaseAuthUserCollisionException) {
            handleUserCollision(credential)
        }
    }

    suspend fun signInWithNaver(customToken: String?): AuthResult? {
        return customToken?.let { token ->
            firebaseAuth.signInWithCustomToken(token).await()
        }
    }

    private suspend fun handleUserCollision(credential: AuthCredential): AuthResult? {
        Log.d("test1234", "handleUserCollision1")
        val currentUser = firebaseAuth.currentUser
        Log.d("test1234", "handleUserCollision2")
        val authResult = currentUser?.linkWithCredential(credential)?.await()
        Log.d("test1234", "handleUserCollision3 : ${authResult}")
        return authResult
    }
}
package kr.co.lion.application.finalproject_aparttalk.auth

import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
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

    fun signOut() = firebaseAuth.signOut()

    suspend fun signInWithGoogle(googleAccount: GoogleIdTokenCredential): AuthResult {
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        return firebaseAuth.signInWithCredential(credential).await()
    }

    suspend fun signInWithKaKao(oAuthToken: OAuthToken): AuthResult {
        val providerId = "oidc.aparttalk"
        val credential = OAuthProvider.newCredentialBuilder(providerId).setIdToken(oAuthToken.idToken).build()
        return firebaseAuth.signInWithCredential(credential).await()
    }

    suspend fun signInWithNaver(customToken: String?): AuthResult? {
        return customToken?.let { token ->
            firebaseAuth.signInWithCustomToken(token).await()
        }
    }
}
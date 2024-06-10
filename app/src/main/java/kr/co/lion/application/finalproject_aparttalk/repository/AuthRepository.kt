package kr.co.lion.application.finalproject_aparttalk.repository

import android.content.Context
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.kakao.sdk.auth.model.OAuthToken
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.auth.GoogleCredentialManagerService
import kr.co.lion.application.finalproject_aparttalk.auth.KaKaoAccountService
import kr.co.lion.application.finalproject_aparttalk.auth.NaverAccountService

class AuthRepository(private val firebaseAuthService: FirebaseAuthService) {

    fun getCurrentUser(): FirebaseUser? = firebaseAuthService.getCurrentUser()

    fun signOut() {
        App.apartmentRepository.clearApartment()
        App.userRepository.clearUser()
        firebaseAuthService.signOut()
    }

    suspend fun signInWithGoogle(googleAccount: GoogleIdTokenCredential): AuthResult {
        return firebaseAuthService.signInWithGoogle(googleAccount)
    }

    suspend fun getGoogleCredential(context: Context): GoogleIdTokenCredential? {
        val googleCredentialManagerService = GoogleCredentialManagerService()
        return googleCredentialManagerService.getGoogleCredential(context)
    }

    suspend fun signInWithKaKao(oAuthToken: OAuthToken): AuthResult {
        return firebaseAuthService.signInWithKaKao(oAuthToken)
    }

    suspend fun getKaKaoAccount(context: Context): OAuthToken? {
        val kaKaoAccountService = KaKaoAccountService()
        return kaKaoAccountService.getKaKaoAccount(context)
    }

    suspend fun signInWithNaver(customToken: String?): AuthResult? {
        return firebaseAuthService.signInWithNaver(customToken)
    }

    suspend fun getNaverAccessToken(context: Context): String? {
        val naverAccountService = NaverAccountService()
        return naverAccountService.getNaverAccessToken(context)
    }

    suspend fun getNaverCustomToken(accessToken: String): String? {
        val naverAccountService = NaverAccountService()
        return naverAccountService.getNaverCustomToken(accessToken)
    }

    suspend fun signInWithPhone(verificationId: String?, code: String): AuthResult {
        return firebaseAuthService.signInWithPhone(verificationId, code)
    }
}
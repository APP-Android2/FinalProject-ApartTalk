package kr.co.lion.application.finalproject_aparttalk.auth

import android.content.Context
import android.credentials.GetCredentialException
import android.os.Build
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import java.security.MessageDigest
import java.util.UUID

class GoogleCredentialManagerService {

    suspend fun getGoogleCredential(context: Context): GoogleIdTokenCredential? = withContext(Dispatchers.IO) {

        val hashedNonce = getHashedNonce()

        val credentialManager = CredentialManager.create(context)
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.SERVER_CLIENT_ID)
            .setNonce(hashedNonce)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(
                request = request,
                context = context
            )

            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            return@withContext googleIdTokenCredential

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE && e is GetCredentialException) {
                    Log.d("test1234", "에러 ${e.message}")
                } else if (e is GoogleIdTokenParsingException) {
                    Log.d("test1234", "에러 ${e.message}")
                } else {
                    Log.d("test1234", "에러 ${e.message}")
                }
                null
            }
        }
        null
    }

    private fun getHashedNonce(): String{
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }

        return hashedNonce
    }
}
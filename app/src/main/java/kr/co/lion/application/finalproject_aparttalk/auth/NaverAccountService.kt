package kr.co.lion.application.finalproject_aparttalk.auth

import android.content.Context
import android.util.Log
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NaverAccountService {

    suspend fun getNaverAccessToken(context: Context): String? = withContext(Dispatchers.IO) {
        NaverIdLoginSDK.initialize(context, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, BuildConfig.CLIENT_NAME)
        suspendCoroutine { continuation ->
            NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
                override fun onSuccess() {
                    val accessToken = NaverIdLoginSDK.getAccessToken()
                    continuation.resume(accessToken)
                }

                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    Log.w("test1234", "errorCode:$errorCode, errorDesc:$errorDescription")
                    continuation.resume(null)
                }

                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            })
        }
    }

    suspend fun getNaverCustomToken(accessToken: String): String? = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            val requestBody = FormBody.Builder()
                .add("naverAccessToken", accessToken)
                .build()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BuildConfig.NAVER_CALLBACK_URL)
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("test1234", "Failed to fetch user profile", e)
                    continuation.resume(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val customToken = responseBody?.let { JSONObject(it).getString("firebaseToken") }
                        continuation.resume(customToken)
                    } else {
                        Log.e("test1234", "Failed to fetch user profile: ${response.code}")
                        continuation.resume(response.code.toString())
                    }
                }
            })
        }
    }
}

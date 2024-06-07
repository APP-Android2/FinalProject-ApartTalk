package kr.co.lion.application.finalproject_aparttalk.auth

import android.content.Context
import android.util.Log
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NaverAccountService {

    suspend fun getNaverAccount(context: Context): String? = withContext(Dispatchers.IO) {
        NaverIdLoginSDK.initialize(context, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, BuildConfig.CLIENT_NAME)

        return@withContext suspendCoroutine<String?> { continuation ->
            NaverIdLoginSDK.authenticate(context, object : OAuthLoginCallback {
                override fun onSuccess() {
                    val accessToken = NaverIdLoginSDK.getAccessToken()
                    if (accessToken != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val token = getNaverCustomToken(accessToken)
                            continuation.resume(token)
                        }
                    } else {
                        continuation.resume(null)
                    }
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

    private suspend fun getNaverCustomToken(accessToken: String): String? = withContext(Dispatchers.IO) {
        val requestBody = FormBody.Builder()
            .add("naverAccessToken", accessToken)
            .build()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BuildConfig.NAVER_CALLBACK_URL)
            .post(requestBody)
            .build()

        val response = client.newCall(request).await()
        if (response.isSuccessful) {
            val responseBody = response.body?.string()
            responseBody?.let { JSONObject(it).getString("firebaseToken") }
        } else {
            Log.e("test1234", "Failed to fetch user profile: ${response.code}")
            null
        }
    }

    // OkHttp Call을 코루틴에서 동기적으로 처리하기 위한 확장 함수
    private suspend fun Call.await(): Response {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    continuation.resume(response)
                }
            })
        }
    }
}
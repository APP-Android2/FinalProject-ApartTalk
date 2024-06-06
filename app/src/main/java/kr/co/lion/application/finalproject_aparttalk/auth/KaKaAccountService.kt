package kr.co.lion.application.finalproject_aparttalk.auth

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KaKaoAccountService {

    suspend fun getKaKaoAccount(context: Context): OAuthToken? {
        KakaoSdk.init(context, BuildConfig.KAKAO_API_KEY)

        return suspendCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("test1234", "카카오계정으로 로그인 실패", error)
                    continuation.resume(null)
                } else if (token != null) {
                    Log.i("test1234", "카카오계정으로 로그인 성공 ${token.accessToken}")
                    continuation.resume(token)
                }
            }

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e("test1234", "카카오톡으로 로그인 실패", error)

                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            continuation.resume(null)
                            return@loginWithKakaoTalk
                        }

                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i("test1234", "카카오톡으로 로그인 성공 ${token.accessToken}")
                        continuation.resume(token)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }
}
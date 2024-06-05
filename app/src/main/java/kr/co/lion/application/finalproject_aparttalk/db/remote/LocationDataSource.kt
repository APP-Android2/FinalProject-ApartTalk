package kr.co.lion.application.finalproject_aparttalk.db.remote

import android.util.Log
import kr.co.lion.application.finalproject_aparttalk.BuildConfig
import kr.co.lion.application.finalproject_aparttalk.model.KakaoApi
import kr.co.lion.application.finalproject_aparttalk.model.ResultKeyword
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationDataSource {

    object RetrofitInstance {
        // HTTP 로깅 인터셉터
        private fun httpLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor { message -> Log.e("MyHttp", message) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        // Authorization 헤더를 추가하는 인터셉터
        private fun authorizationInterceptor(): Interceptor {
            return Interceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeaders: Request = originalRequest.newBuilder()
                    .header("Authorization", "KakaoAK ${BuildConfig.KAKAO_REST_API_KEY}")  // 수정된 부분
                    .build()
                chain.proceed(requestWithHeaders)
            }
        }

        // OkHttpClient 설정
        val client: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor(authorizationInterceptor())
                .build()
        }

        // Retrofit 설정
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://dapi.kakao.com/")
                .build()
        }

        // KakaoAPI 서비스 생성
        val retrofitService: KakaoApi by lazy {
            retrofit.create(KakaoApi::class.java)
        }
    }

    // 키 파라미터를 제거한 메서드
    suspend fun getSearchKeyword(categoryGroupCode: String, x: String, y: String, radius: Int): ResultKeyword {
        return RetrofitInstance.retrofitService.getSearchKeyWord(
            categoryGroupCode = categoryGroupCode,
            x = x,
            y = y,
            radius = radius
        )
    }
}

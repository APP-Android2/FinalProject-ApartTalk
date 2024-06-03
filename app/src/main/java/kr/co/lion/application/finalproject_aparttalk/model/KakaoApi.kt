package kr.co.lion.application.finalproject_aparttalk.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApi {
    @GET("v2/local/search/category.json")
    suspend fun getSearchKeyWord(
        @Query("category_group_code") categoryGroupCode : String,
        @Query("x") x:String,
        @Query("y") y:String,
        @Query("radius") radius: Int
    ) : ResultKeyword
}
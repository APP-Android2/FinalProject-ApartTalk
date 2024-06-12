package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.db.remote.CommunityLikeDataSource
import kr.co.lion.application.finalproject_aparttalk.model.LikeData

class CommunityLikeRepository {

    private val communityLikeDataSource = CommunityLikeDataSource()

    suspend fun getLikeSequence() = communityLikeDataSource.getLikeSequence()

    suspend fun updateLikeSequence(likeSequence:Int) = communityLikeDataSource.updateLikeSequence(likeSequence)

    suspend fun insertLikeData(postApartId: String, likeData: LikeData) = communityLikeDataSource.insertLikeData(postApartId, likeData)

    suspend fun deleteLikeData(postApartId: String, likeData: LikeData) = communityLikeDataSource.deleteLikeData(postApartId, likeData)

    suspend fun gettingLikeList(postApartId: String, postId: String) = communityLikeDataSource.gettingLikeList(postApartId, postId)
}
package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.db.remote.CommunityCommentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.util.CommentState

class CommunityCommentRepository {

    private val communityCommentDataSource = CommunityCommentDataSource()
    private val userDataSource = UserDataSource()

    suspend fun getCommunityCommentSequence() = communityCommentDataSource.getCommunityCommentSequence()

    suspend fun updateCommunityCommentSequence(commentSequence: Int) = communityCommentDataSource.updateCommunityCommentSequence(commentSequence)

    suspend fun insertCommunityCommentData(postApartId: String, commentData: CommentData) = communityCommentDataSource.insertCommunityCommentData(postApartId, commentData)

    suspend fun gettingCommunityCommentList(postApartId: String, postId: String) = communityCommentDataSource.gettingCommunityCommentList(postApartId, postId)

    suspend fun updateCommunityCommentState(postApartId: String, commentData: CommentData, newState: CommentState) = communityCommentDataSource.updateCommunityCommentState(postApartId, commentData, newState)

    suspend fun updateCommunityCommentData(postApartId: String, commentData: CommentData, mapComment: MutableMap<String, Any>) = communityCommentDataSource.updateCommunityCommentData(postApartId, commentData, mapComment)

    suspend fun getApartmentUserList(apartmentUid: String) = userDataSource.getApartmentUserList(apartmentUid)
}
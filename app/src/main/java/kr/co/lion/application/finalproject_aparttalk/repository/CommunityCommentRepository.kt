package kr.co.lion.application.finalproject_aparttalk.repository

import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.db.remote.CommunityCommentDataSource
import kr.co.lion.application.finalproject_aparttalk.util.CommentState

class CommunityCommentRepository {

    private val communityCommentDataSource = CommunityCommentDataSource()

    suspend fun getCommunityCommentSequence() = communityCommentDataSource.getCommunityCommentSequence()

    suspend fun updateCommunityCommentSequence(commentSequence: Int) = communityCommentDataSource.updateCommunityCommentSequence(commentSequence)

    suspend fun insertCommunityCommentData(commentData: CommentData) = communityCommentDataSource.insertCommunityCommentData(commentData)

    suspend fun gettingCommunityCommentList(postId: String) = communityCommentDataSource.gettingCommunityCommentList(postId)

    suspend fun updateCommunityCommentState(commentData: CommentData, newState: CommentState) = communityCommentDataSource.updateCommunityCommentState(commentData, newState)

    suspend fun updateCommunityCommentData(commentData: CommentData, mapComment: MutableMap<String, Any>) = communityCommentDataSource.updateCommunityCommentData(commentData, mapComment)
}
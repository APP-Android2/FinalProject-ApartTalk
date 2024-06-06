package kr.co.lion.application.finalproject_aparttalk.repository

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.db.remote.CommunityPostDataSource
import kr.co.lion.application.finalproject_aparttalk.util.PostState

class CommunityPostRepository {

    private val communityPostDataSource = CommunityPostDataSource()

    suspend fun uploadImage(context: Context, postIdx:Int, uriList:MutableList<Uri>) = communityPostDataSource.uploadImage(context, postIdx, uriList)

    suspend fun gettingCommunityPostImage(context:Context, imageFileName:String, imageView: ImageView) = communityPostDataSource.gettingCommunityPostImage(context, imageFileName, imageView)

    suspend fun getCommunityPostSequence() = communityPostDataSource.getCommunityPostSequence()

    suspend fun updateCommunityPostSequence(communityPostSequence:Int) = communityPostDataSource.updateCommunityPostSequence(communityPostSequence)

    suspend fun insertCommunityPostData(postData: PostData) = communityPostDataSource.insertCommunityPostData(postData)

    suspend fun selectCommunityPostData(postIdx: Int) = communityPostDataSource.selectCommunityPostData(postIdx)

    suspend fun gettingCommunityPostList() = communityPostDataSource.gettingCommunityPostList()

    suspend fun updateCommunityPostState(postIdx: Int, newState: PostState) = communityPostDataSource.updateCommunityPostState(postIdx, newState)
}
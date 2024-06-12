package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.LikeData
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityCommentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityLikeRepository
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository
import kr.co.lion.application.finalproject_aparttalk.util.CommentState
import kr.co.lion.application.finalproject_aparttalk.util.PostState

class CommunityDetailViewModel: ViewModel() {

    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    private val communityLikeRepository = CommunityLikeRepository()

    // 커뮤니티 상세조회 글 종류
    private val _textViewCommunityDetailToolbarTitle = MutableLiveData<String>()
    val textViewCommunityDetailToolbarTitle: LiveData<String> get() = _textViewCommunityDetailToolbarTitle

    // 커뮤니티 상세조회 날짜
    private val _textViewCommunityDetailDate = MutableLiveData<String>()
    val textViewCommunityDetailDate: LiveData<String> get() = _textViewCommunityDetailDate

    // 커뮤니티 상세조회 글 작성자
    private val _textViewCommunityDetailWriter = MutableLiveData<String>()
    val textViewCommunityDetailWriter: LiveData<String> get() = _textViewCommunityDetailWriter

    // 커뮤니티 상세조회 글 제목
    private val _textViewCommunityDetailSubject = MutableLiveData<String>()
    val textViewCommunityDetailSubject: LiveData<String> get() = _textViewCommunityDetailSubject

    // 커뮤니티 상세조회 글 내용
    private val _textViewCommunityDetailContent = MutableLiveData<String>()
    val textViewCommunityDetailContent: LiveData<String> get() = _textViewCommunityDetailContent

    // 커뮤니티 상세조회 좋아요 개수
    private val _textViewCommunityDetailLikeCnt = MutableLiveData<String>()
    val textViewCommunityDetailLikeCnt: LiveData<String> get() = _textViewCommunityDetailLikeCnt

    // 커뮤니티 상세조회 댓글 개수
    private val _textViewCommunityDetailCommentCnt = MutableLiveData<String>()
    val textViewCommunityDetailCommentCnt: LiveData<String> get() = _textViewCommunityDetailCommentCnt

    // 커뮤니티 상세조회 댓글 입력요소
    private val _textInputCommunityDetailSendComment = MutableLiveData<String>()
    val textInputCommunityDetailSendComment: LiveData<String> get() = _textInputCommunityDetailSendComment

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 글 번호를 이용해 글 데이터를 가져와 반환한다.
    suspend fun selectCommunityPostData(postApartId: String, postId: String): PostData?{
        return communityPostRepository.selectCommunityPostData(postApartId, postId)
    }

    // 댓글 번호 시퀀스값을 가져온다.
    suspend fun getCommunityCommentSequence():Int{
        return communityCommentRepository.getCommunityCommentSequence()
    }

    // 댓글 시퀀스 값을 업데이트 한다.
    suspend fun updateCommunityCommentSequence(commentSequence:Int){
        return communityCommentRepository.updateCommunityCommentSequence(commentSequence)
    }

    // 댓글 정보를 저장한다.
    suspend fun insertCommunityCommentData(postApartId: String, commentData: CommentData){
        return communityCommentRepository.insertCommunityCommentData(postApartId, commentData)
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }

    // 댓글의 내용을 변경하는 메서드
    suspend fun updateCommunityCommentData(postApartId: String, commentData: CommentData, mapComment: MutableMap<String, Any>){
        return communityCommentRepository.updateCommunityCommentData(postApartId, commentData, mapComment)
    }

    // 댓글의 상태를 변경하는 메서드
    suspend fun updateCommunityCommentState(postApartId: String, commentData: CommentData, newState: CommentState){
        return communityCommentRepository.updateCommunityCommentState(postApartId, commentData, newState)
    }

    // 글의 상태를 변경하는 메서드
    suspend fun updateCommunityPostState(postApartId: String, postIdx: Int, newState: PostState) {
        return communityPostRepository.updateCommunityPostState(postApartId, postIdx, newState)
    }

    // 한 아파트의 모든 사용자 정보 가져오는 메서드
    suspend fun getApartmentUserList(apartmentUid: String): List<UserModel?> {
        return communityCommentRepository.getApartmentUserList(apartmentUid)
    }

    // 좋아요 번호 시퀀스값을 가져온다.
    suspend fun getLikeSequence():Int{
        return communityLikeRepository.getLikeSequence()
    }

    // 좋아요 시퀀스 값을 업데이트 한다.
    suspend fun updateLikeSequence(likeSequence:Int){
        return communityLikeRepository.updateLikeSequence(likeSequence)
    }

    // 게시글의 좋아요 정보를 저장한다.
    suspend fun insertLikeData(postApartId: String, likeData: LikeData){
        return communityLikeRepository.insertLikeData(postApartId, likeData)
    }

    // 게시글의 좋아요 정보 삭제
    suspend fun deleteLikeData(postApartId: String, likeData: LikeData) {
        return communityLikeRepository.deleteLikeData(postApartId, likeData)
    }

    // 좋아요 목록을 가져온다.
    suspend fun gettingLikeList(postApartId: String, postId: String): MutableList<LikeData>{
        return communityLikeRepository.gettingLikeList(postApartId, postId)
    }


}
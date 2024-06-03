package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.CommunityPostRepository

class CommunityDetailViewModel: ViewModel() {

    private val communityPostRepository = CommunityPostRepository()

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
}
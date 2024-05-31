package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityNotificationViewModel: ViewModel() {


    // 커뮤니티 글 리스트 공지 탭 라벨
    private val _textViewCommunityListLabelNotification = MutableLiveData<String>()
    val textViewCommunityListLabelNotification: LiveData<String> get() = _textViewCommunityListLabelNotification

    // 커뮤니티 글 리스트 공지 탭 제목
    private val _textViewCommunityListTitleNotification = MutableLiveData<String>()
    val textViewCommunityListTitleNotification: LiveData<String> get() = _textViewCommunityListTitleNotification

    // 커뮤니티 글 리스트 공지 탭 좋아요 수
    private val _textViewCommunityListLikeCntNotification = MutableLiveData<Int>()
    val textViewCommunityListLikeCntNotification: LiveData<Int> get() = _textViewCommunityListLikeCntNotification

    // 커뮤니티 글 리스트 공지 탭 댓글 수
    private val _textViewCommunityListCommentCntNotification = MutableLiveData<Int>()
    val textViewCommunityListCommentCntNotification: LiveData<Int> get() = _textViewCommunityListCommentCntNotification

    // 커뮤니티 글 리스트 공지 탭 날짜
    private val _textViewCommunityListDateNotification = MutableLiveData<String>()
    val textViewCommunityListDateNotification: LiveData<String> get() = _textViewCommunityListDateNotification

    fun setLabel(name: String){
        _textViewCommunityListLabelNotification.value = name
    }
}
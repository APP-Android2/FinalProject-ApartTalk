package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityAddViewModel: ViewModel() {

    // 커뮤니티 글 작성 종류
    private val _communityPostAddType = MutableLiveData<String>()
    val communityPostAddType: LiveData<String> get() = _communityPostAddType

    // 커뮤니티 글 작성 제목
    private val _textViewCommunityAddSubject = MutableLiveData<String>()
    val textViewCommunityAddSubject: LiveData<String> get() = _textViewCommunityAddSubject

    // 커뮤니티 글 작성 내용
    private val _textViewCommunityAddContent = MutableLiveData<String>()
    val textViewCommunityAddContent: LiveData<String> get() = _textViewCommunityAddContent
}
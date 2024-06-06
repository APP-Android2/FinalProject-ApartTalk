package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class CommunityAddViewModel: ViewModel() {

    private val communityPostRepository = CommunityPostRepository()

    // 커뮤니티 글 작성 종류
    private val _communityPostAddType = MutableLiveData<String>()
    val communityPostAddType: LiveData<String> get() = _communityPostAddType

    // 커뮤니티 글 작성 제목
    private val _textViewCommunityAddSubject = MutableLiveData<String>()
    val textViewCommunityAddSubject: LiveData<String> get() = _textViewCommunityAddSubject

    // 커뮤니티 글 작성 내용
    private val _textViewCommunityAddContent = MutableLiveData<String>()
    val textViewCommunityAddContent: LiveData<String> get() = _textViewCommunityAddContent

    // 글 종류 초기화
    fun initializeType(){
        _communityPostAddType.value = " "
    }

    // 글 종류 데이터 삽입
    fun settingType(type: String){
        _communityPostAddType.value = type
    }

    // 글 제목 초기화
    fun initializeSubject(){
        _textViewCommunityAddSubject.value = " "
    }

    // 글 제목 데이터 삽입
    fun settingSubject(subject: String){
        _textViewCommunityAddSubject.value = subject
    }

    // 글 내용 초기화
    fun initializeContent(){
        _textViewCommunityAddContent.value = " "
    }

    // 글 내용 데이터 삽입
    fun settingContent(content: String){
        _textViewCommunityAddContent.value = content
    }

    // 이미지 업로드
    suspend fun uploadImage(context: Context, postIdx:Int, uriList:MutableList<Uri>) : MutableList<String> {
        return communityPostRepository.uploadImage(context, postIdx, uriList)
    }

    // 게시글 번호 시퀀스값을 가져오기
    suspend fun getCommunityPostSequence():Int{
        return communityPostRepository.getCommunityPostSequence()
    }

    // 게시글 시퀀스 값을 업데이트 한다.
    suspend fun updateCommunityPostSequence(communityPostSequence:Int){
        return communityPostRepository.updateCommunityPostSequence(communityPostSequence)
    }

    // 게시글 정보를 저장한다.
    suspend fun insertCommunityPostData(postData: PostData){
        return communityPostRepository.insertCommunityPostData(postData)
    }

}
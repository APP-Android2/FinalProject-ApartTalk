package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository
import kr.co.lion.application.finalproject_aparttalk.util.PostState

class CommunityModifyViewModel: ViewModel() {
    private val communityPostRepository = CommunityPostRepository()

    // 커뮤니티 글 수정 종류
    private val _communityPostModifyType = MutableLiveData<String>()
    val communityPostModifyType: LiveData<String> get() = _communityPostModifyType

    // 커뮤니티 글 수정 제목
    private val _textViewCommunityModifySubject = MutableLiveData<String>()
    val textViewCommunityModifySubject: LiveData<String> get() = _textViewCommunityModifySubject

    // 커뮤니티 글 수정 내용
    private val _textViewCommunityModifyContent = MutableLiveData<String>()
    val textViewCommunityModifyContent: LiveData<String> get() = _textViewCommunityModifyContent

    // 글 제목 초기화
    fun initializeSubject(){
        _textViewCommunityModifySubject.value = " "
    }

    // 글 내용 초기화
    fun initializeContent(){
        _textViewCommunityModifyContent.value = " "
    }

    // 글 번호를 이용해 글 데이터를 가져와 반환한다.
    suspend fun selectCommunityPostData(postApartId: String, postId: String): PostData?{
        return communityPostRepository.selectCommunityPostData(postApartId, postId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 글 데이터를 수정하는 메서드
    suspend fun updateCommunityPostData(postData: PostData) {
        return communityPostRepository.updateCommunityPostData(postData)
    }

    // 글의 상태를 변경하는 메서드
    suspend fun updateCommunityPostState(postApartId: String, postIdx: Int, newState: PostState) {
        return communityPostRepository.updateCommunityPostState(postApartId, postIdx, newState)
    }
}
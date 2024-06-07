package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class MyLikeViewModel(private val repository: CommunityPostRepository) : ViewModel() {

    private val _myLikeList = MutableLiveData<List<PostData>>()
    val myLikeList: LiveData<List<PostData>> get() = _myLikeList

    fun loadMyLikedPosts() {
        viewModelScope.launch {
            val authUser = App.authRepository.getCurrentUser() ?: return@launch
            val apartment = App.apartmentRepository.getApartment(authUser.uid) ?: return@launch
            val posts = repository.gettingCommunityPostList(apartment.uid)
            // 여기서 userId와 매칭되는 좋아요 누른 글만 필터링
            _myLikeList.value = posts.filter { it.postUserId == authUser.uid && it.postState == 1 } // postState가 1인 경우를 좋아요한 글로 가정
        }
    }
}
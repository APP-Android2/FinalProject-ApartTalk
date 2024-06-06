package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.CommunityPostRepository

class MyLikeViewModel(private val repository: CommunityPostRepository) : ViewModel() {

    private val _myLikeList = MutableLiveData<List<PostData>>()
    val myLikeList: LiveData<List<PostData>> get() = _myLikeList

    fun loadMyLikedPosts(userId: Int) {
        viewModelScope.launch {
            val posts = repository.gettingCommunityPostList()
            // 여기서 userId와 매칭되는 좋아요 누른 글만 필터링
            _myLikeList.value = posts.filter { it.postUserIdx == userId && it.postState == 1 } // postState가 1인 경우를 좋아요한 글로 가정
        }
    }
}
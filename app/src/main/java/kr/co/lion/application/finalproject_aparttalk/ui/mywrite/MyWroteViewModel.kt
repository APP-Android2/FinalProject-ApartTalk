package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.CommunityPostRepository

class MyWroteViewModel(private val repository: CommunityPostRepository) : ViewModel() {

    private val _myWroteList = MutableLiveData<List<PostData>>()
    val myWroteList: LiveData<List<PostData>> get() = _myWroteList

    fun loadMyWrotePosts(userId: Int) {
        viewModelScope.launch {
            val posts = repository.gettingCommunityPostList()
            _myWroteList.value = posts.filter { it.postUserIdx == userId }
        }
    }
}
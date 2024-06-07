package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class MyWroteViewModel(private val repository: CommunityPostRepository) : ViewModel() {

    private val _myWroteList = MutableLiveData<List<PostData>>()
    val myWroteList: LiveData<List<PostData>> get() = _myWroteList

    fun loadMyWrotePosts() {
        viewModelScope.launch {
            val authUser = App.authRepository.getCurrentUser() ?: return@launch
            val apartment = App.apartmentRepository.getApartment(authUser.uid) ?: return@launch
            val posts = repository.gettingCommunityPostList(apartment.uid)
            _myWroteList.value = posts.filter { it.postUserId == authUser.uid }
        }
    }
}
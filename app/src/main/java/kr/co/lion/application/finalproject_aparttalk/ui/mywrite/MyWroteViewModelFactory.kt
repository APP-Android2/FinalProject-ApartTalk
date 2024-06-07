package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class MyWroteViewModelFactory(private val repository: CommunityPostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyWroteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyWroteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
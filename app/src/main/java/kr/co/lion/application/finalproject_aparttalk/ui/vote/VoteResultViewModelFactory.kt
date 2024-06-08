package kr.co.lion.application.finalproject_aparttalk.ui.vote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.VoteResultRepository

class VoteResultViewModelFactory(private val repository: VoteResultRepository, private val documentId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoteResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VoteResultViewModel(repository, documentId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

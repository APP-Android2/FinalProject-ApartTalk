package kr.co.lion.application.finalproject_aparttalk.ui.vote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.repository.VoteRepository

class VoteViewModelFactory(
    private val voteRepository: VoteRepository,
    private val userRepository: UserRepository,
    private val voteDocumentId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VoteViewModel(voteRepository, userRepository, voteDocumentId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

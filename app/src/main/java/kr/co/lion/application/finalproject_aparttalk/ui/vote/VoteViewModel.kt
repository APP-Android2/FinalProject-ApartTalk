package kr.co.lion.application.finalproject_aparttalk.ui.vote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.repository.VoteRepository

class VoteViewModel(
    private val voteRepository: VoteRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userVoteStatus = MutableLiveData<Boolean>()
    val userVoteStatus: LiveData<Boolean> get() = _userVoteStatus

    private val _voteItems = MutableLiveData<List<VoteResultModel>>()
    val voteItems: LiveData<List<VoteResultModel>> get() = _voteItems

    private val _hasOngoingVote = MutableLiveData<Boolean>()
    val hasOngoingVote: LiveData<Boolean> get() = _hasOngoingVote

    private val _user = MutableLiveData<UserModel?>()
    val user: LiveData<UserModel?> get() = _user

    init {
        fetchVoteItems()
        checkOngoingVote()
    }

    fun getUserVoteStatus(userIdx: String) {
        viewModelScope.launch {
            val hasVoted = voteRepository.getUserVoteStatus(userIdx)
            _userVoteStatus.postValue(hasVoted)
        }
    }

    fun updateUserVoteStatus(userIdx: String, hasVoted: Boolean) {
        viewModelScope.launch {
            val success = voteRepository.updateUserVoteStatus(userIdx, hasVoted)
            if (success) {
                _userVoteStatus.postValue(hasVoted)
            }
        }
    }

    fun getUser(uid: String) {
        viewModelScope.launch {
            val user = userRepository.getUser(uid)
            _user.postValue(user)
        }
    }

    private fun fetchVoteItems() {
        viewModelScope.launch {
            val items = voteRepository.getVoteItems()
            _voteItems.postValue(items)
        }
    }

    private fun checkOngoingVote() {
        viewModelScope.launch {
            val voteData = voteRepository.getVoteData()
            _hasOngoingVote.postValue(voteData != null && voteData.electionName.isNotEmpty())
        }
    }
}

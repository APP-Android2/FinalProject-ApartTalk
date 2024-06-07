package kr.co.lion.application.finalproject_aparttalk.ui.vote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel
import kr.co.lion.application.finalproject_aparttalk.repository.VoteResultRepository

class VoteResultViewModel(private val repository: VoteResultRepository, private val documentId: String) : ViewModel() {

    private val _voteResultData = MutableLiveData<VoteResultModel>()
    val voteResultData: LiveData<VoteResultModel> get() = _voteResultData

    init {
        fetchVoteResult()
    }

    private fun fetchVoteResult() {
        viewModelScope.launch {
            val result = repository.getVoteResult(documentId)
            result?.let {
                _voteResultData.postValue(it)
            }
        }
    }
}

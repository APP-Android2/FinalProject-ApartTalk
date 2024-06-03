package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.FireCheckModel
import kr.co.lion.application.finalproject_aparttalk.repository.FireCheckRepository

class FireCheckViewModel(private val repository: FireCheckRepository) : ViewModel() {

    private val _fireCheckData = MutableLiveData<List<FireCheckModel>>()
    val fireCheckData: LiveData<List<FireCheckModel>> get() = _fireCheckData

    fun fetchFireCheckData() {
        viewModelScope.launch {
            val data = repository.getFireCheckData()
            _fireCheckData.value = data
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.repository.ServiceRepository

class ServiceViewModel : ViewModel() {

    private val repository = ServiceRepository()

    fun addService(service: ServiceModel) {
        viewModelScope.launch {
            val result = repository.addService(service)
            if (result.isSuccess) {
                // 성공 처리
            } else {
                // 실패 처리
            }
        }
    }

    fun getServices(): LiveData<List<ServiceModel>> {
        return repository.getServices()
    }
}
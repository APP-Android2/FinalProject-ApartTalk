package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.repository.FireCheckRepository

class FireCheckViewModelFactory(private val repository: FireCheckRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FireCheckViewModel::class.java)){
            return FireCheckViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
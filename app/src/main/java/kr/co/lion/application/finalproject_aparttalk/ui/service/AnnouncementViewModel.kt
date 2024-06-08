package kr.co.lion.application.finalproject_aparttalk.ui.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.AnnouncementModel
import kr.co.lion.application.finalproject_aparttalk.repository.AnnouncementRepository

class AnnouncementViewModel : ViewModel() {

    private val repository = AnnouncementRepository()

    fun addAnnouncement(announcement: AnnouncementModel) {
        viewModelScope.launch {
            val result = repository.addAnnouncement(announcement)
            if (result.isSuccess) {
                // 성공 처리
            } else {
                // 실패 처리
            }
        }
    }
}
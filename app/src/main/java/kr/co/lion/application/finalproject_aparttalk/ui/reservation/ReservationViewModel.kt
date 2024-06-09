package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class ReservationViewModel : ViewModel() {

    private val _reservations = MutableLiveData<List<FacilityResModel>>()
    val reservations: LiveData<List<FacilityResModel>> get() = _reservations

    private val _selectedReservation = MutableLiveData<FacilityResModel>()
    val selectedReservation: LiveData<FacilityResModel> get() = _selectedReservation

    init {
        _reservations.value = listOf(
            FacilityResModel("uid1", "수영장 예약", "15:00 ~ 17:00", "imageUrl1", "40,000", true, "2024.03.01")
        )
    }

    fun addReservation(reservation: FacilityResModel) {
        val updatedList = _reservations.value.orEmpty().toMutableList()
        updatedList.add(reservation)
        _reservations.value = updatedList
    }

    fun setReservations(reservations: List<FacilityResModel>) {
        _reservations.value = reservations
    }

    fun setSelectedReservation(reservation: FacilityResModel) {
        _selectedReservation.value = reservation
    }

    fun removeAllReservations() {
        _reservations.value = emptyList()
    }
}
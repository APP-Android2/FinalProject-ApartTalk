package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel

class ReservationViewModel : ViewModel() {

    private val _reservations = MutableLiveData<List<FacilityResModel>>()
    val reservations: LiveData<List<FacilityResModel>> get() = _reservations

    private val _selectedReservation = MutableLiveData<FacilityResModel>()
    val selectedReservation: LiveData<FacilityResModel> get() = _selectedReservation

    private val _isReservationCompleted = MutableLiveData<Boolean>()
    val isReservationCompleted: LiveData<Boolean> get() = _isReservationCompleted

    private val firestore = FirebaseFirestore.getInstance()

    init {
        _isReservationCompleted.value = false
        loadReservationsFromFirestore()
    }

    fun loadReservationsFromFirestore() {
        firestore.collection("reservations")
            .get()
            .addOnSuccessListener { result ->
                val reservationsList = result.mapNotNull { document ->
                    document.toObject(FacilityResModel::class.java)
                }
                _reservations.value = reservationsList
            }
            .addOnFailureListener { exception ->
                // 실패 처리 로직
            }
    }

    fun removeReservation(reservation: FacilityResModel) {
        firestore.collection("reservations").document(reservation.userUid)
            .delete()
            .addOnSuccessListener {
                loadReservationsFromFirestore()  // 데이터 갱신
            }
            .addOnFailureListener { exception ->
                // 실패 처리 로직
            }
    }

    fun setSelectedReservation(reservation: FacilityResModel) {
        _selectedReservation.value = reservation
    }

    fun resetReservationCompleted() {
        _isReservationCompleted.value = false
    }
}
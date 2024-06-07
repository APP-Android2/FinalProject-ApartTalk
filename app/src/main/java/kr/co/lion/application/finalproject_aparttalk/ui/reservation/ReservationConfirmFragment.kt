package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationConfirmBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.ui.info.UserViewModel
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName


class ReservationConfirmFragment : Fragment() {

    private lateinit var fragmentReservationConfirmBinding: FragmentReservationConfirmBinding
    private val reservationViewModel: ReservationViewModel by activityViewModels()
    private lateinit var reserveActivity: ReserveActivity
    private lateinit var userModel: UserModel
    private val userViewModel: ReservationUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationConfirmBinding = FragmentReservationConfirmBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        userViewModel.loadUser()

        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                fragmentReservationConfirmBinding.reservationConfirmTextViewName.text = it.name
                fragmentReservationConfirmBinding.reservationConfirmTextViewPhoneNumber.text = it.phoneNumber
            }
        })

        settingToolbar()
        settingButton()
        observeSelectedReservation()

        return fragmentReservationConfirmBinding.root
    }

    private fun settingToolbar() {
        fragmentReservationConfirmBinding.apply {
            reservationConfirmToolbar.apply {
                textViewReservationConfirmToolbarTitle.text = "예약내역"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
                }
            }
        }
    }

    private fun settingButton() {
        fragmentReservationConfirmBinding.apply {
            reservationConfirmButton.setOnClickListener {
                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
            }
        }
    }

    private fun observeSelectedReservation() {
        reservationViewModel.selectedReservation.observe(viewLifecycleOwner, Observer { reservation ->
            if (reservation != null) {
                bindReservationData(reservation)
            }
        })
    }

    private fun bindReservationData(reservation: FacilityResModel) {
        userModel = getUserModelByUid(reservation.userUid) // 유저 정보를 가져오는 함수
        fragmentReservationConfirmBinding.apply {
            reservationConfirmTextViewDate.text = reservation.reservationDate
            reservationConfirmTextViewFacility.text = reservation.titleText
            reservationConfirmTextViewReservedDate.text = reservation.reservationDate
            reservationConfirmTextViewTime.text = reservation.useTime
            reservationConfirmTextViewPrice.text = reservation.usePrice
        }
    }

    private fun getUserModelByUid(uid: String): UserModel {
        // 예제 데이터를 반환 (실제 구현은 데이터베이스 또는 다른 소스에서 가져와야 함)
        return UserModel(uid)
    }
}
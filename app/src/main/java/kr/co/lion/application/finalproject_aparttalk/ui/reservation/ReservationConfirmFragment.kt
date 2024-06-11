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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationConfirmBinding = FragmentReservationConfirmBinding.inflate(inflater, container, false)
        reserveActivity = activity as ReserveActivity

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
        fragmentReservationConfirmBinding.apply {
            reservationConfirmTextViewName.text = reservation.userName
            reservationConfirmTextViewPhoneNumber.text = reservation.userNumber
            reservationConfirmTextViewDate.text = reservation.reservationDate
            reservationConfirmTextViewFacility.text = reservation.titleText
            reservationConfirmTextViewReservedDate.text = reservation.reservationDate
            reservationConfirmTextViewTime.text = reservation.useTime
            reservationConfirmTextViewPrice.text = reservation.usePrice
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCancelCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName


class ReservationCancelCompleteFragment() : Fragment() {

    lateinit var fragmentReservationCancelCompleteBinding: FragmentReservationCancelCompleteBinding
    lateinit var reserveActivity: ReserveActivity
    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCancelCompleteBinding = FragmentReservationCancelCompleteBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingToolbar()
        settingButton()
        settingData()

        return fragmentReservationCancelCompleteBinding.root
    }

    fun settingToolbar() {
        fragmentReservationCancelCompleteBinding.apply {
            reservationCancelToolbar.apply {
                textViewReservationCancelToolbarTitle.text = "예약취소"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
                }
            }
        }
    }

    fun settingData() {
        fragmentReservationCancelCompleteBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_reserve)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_reserve, typeArray)
            textViewReserveCancelAddType.setAdapter(typeArrayAdapter)
        }
    }

    fun settingButton() {
        fragmentReservationCancelCompleteBinding.apply {
            reservationCancelButton.setOnClickListener {
                reservationViewModel.removeAllReservations()
                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
            }
        }
    }
}
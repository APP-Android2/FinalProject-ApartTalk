package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationConfirmBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName


class ReservationConfirmFragment : Fragment() {


    lateinit var fragmentReservationConfirmBinding: FragmentReservationConfirmBinding
    lateinit var reserveActivity: ReserveActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentReservationConfirmBinding = FragmentReservationConfirmBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingToolbar()
        settingButton()

        return fragmentReservationConfirmBinding.root
    }

    fun settingToolbar(){
        fragmentReservationConfirmBinding.apply {
            reservationConfirmToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    reserveActivity.removeFragment(ReserveFragmentName.RESERVATION_FRAGMENT)
                }

            }
        }
    }
    fun settingButton(){
        fragmentReservationConfirmBinding.apply{
            reservationConfirmButton.apply {
                reserveActivity.replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, true, true, null)
            }
        }
    }
}
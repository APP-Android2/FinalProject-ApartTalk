package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentParkingCheckBinding
import kr.co.lion.application.finalproject_aparttalk.util.ParkingFragmentName

class ParkingCheckFragment : Fragment() {

    lateinit var binding:FragmentParkingCheckBinding

    lateinit var parkingActivity: ParkingActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingCheckBinding.inflate(layoutInflater)
        parkingActivity = activity as ParkingActivity
        settingToolbar()
        settingEvent()
        return binding.root
    }

    //툴바 설정
    private fun settingToolbar(){
        binding.apply {
            toolbarParking.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    requireActivity().finish()
                }
            }
        }
    }

    private fun settingEvent(){
        binding.apply {
            floatButtonReserveParking.setOnClickListener {
                parkingActivity.replaceFragment(ParkingFragmentName.PARKING_RESERVE_FRAGMENT, true, null)

            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentParkingCheckBinding
import kr.co.lion.application.finalproject_aparttalk.ui.parking.adapter.ParkingCheckAdapter
import kr.co.lion.application.finalproject_aparttalk.util.ParkingFragmentName

class ParkingCheckFragment : Fragment() {

    lateinit var binding:FragmentParkingCheckBinding

    lateinit var parkingActivity: ParkingActivity

    val parkingAdapter : ParkingCheckAdapter by lazy {
        val adapter = ParkingCheckAdapter()
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingCheckBinding.inflate(layoutInflater)
        parkingActivity = activity as ParkingActivity
        settingToolbar()
        settingEvent()
        connectAdapter()
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

    //어댑터 연결
    private fun connectAdapter(){
        binding.apply {
            parkingRecyclerview.apply {
                adapter = parkingAdapter
                layoutManager = LinearLayoutManager(requireActivity())
                val deco = MaterialDividerItemDecoration(requireActivity(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    private fun settingEvent(){
        binding.apply {
            parkingAdd.setOnClickListener {
                parkingActivity.replaceFragment(ParkingFragmentName.PARKING_RESERVE_FRAGMENT, true, null)

            }
        }
    }
}
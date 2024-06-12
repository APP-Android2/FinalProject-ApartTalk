package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentParkingCheckBinding
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel
import kr.co.lion.application.finalproject_aparttalk.ui.parking.adapter.ParkingCheckAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.parking.viewmodel.ParkingViewModel
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.ParkingFragmentName

class ParkingCheckFragment : Fragment() {

    lateinit var binding:FragmentParkingCheckBinding

    lateinit var parkingActivity: ParkingActivity


    val parkingAdapter : ParkingCheckAdapter by lazy {
        val adapter = ParkingCheckAdapter()
        adapter
    }

    val viewModel : ParkingViewModel by viewModels()

    private var valueSize: Int = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingCheckBinding.inflate(layoutInflater)
        parkingActivity = activity as ParkingActivity
        settingToolbar()
        connectAdapter()
        getParkingData()
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

    //데이터 받아오기
    private fun getParkingData(){

        viewLifecycleOwner.lifecycleScope.launch {
            val authUser = App.authRepository.getCurrentUser()
            if (authUser != null){
                val user = App.userRepository.getUser(authUser.uid)
                if (user != null){

                    viewModel.getParkingResData(user.uid)

                }
            }
        }
        viewModel.parkingList.observe(requireActivity()){ value ->
            //Log.d("test1234", "${value.size}")
            parkingAdapter.submitList(value)

            //예약 횟수 제한
            binding.parkingReserveCount.text = "이번달 남은 예약 횟수 : ${10 - value.size}회"
            valueSize -= value.size
        }

        //예약 횟수 확인
        if (valueSize > 0){
            binding.parkingAdd.setOnClickListener {
                parkingActivity.replaceFragment(ParkingFragmentName.PARKING_RESERVE_FRAGMENT, true, null)

            }
        }else{
            binding.parkingAdd.setOnClickListener {
                val dialog = DialogConfirm(
                    "예약 횟수 초과", "월 예약 횟수를 초과했습니다", parkingActivity
                )
                dialog.setDialogButtonClickListener(object : DialogConfirm.OnButtonClickListener{
                    override fun okButtonClick() {
                        dialog.dismiss()
                    }

                })
                dialog.show(parkingActivity.supportFragmentManager, "DialogConfirm")
            }
        }
    }
}
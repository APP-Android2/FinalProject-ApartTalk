package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kakao.sdk.common.KakaoSdk
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFacilityBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel
import kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter.FacilityAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel.FacilityViewmodel

class FacilityFragment : Fragment() {

    lateinit var binding:FragmentFacilityBinding

    val viewModel:FacilityViewmodel by viewModels()

    val facilityAdapter : FacilityAdapter by lazy {
        val adapter = FacilityAdapter()
        adapter.setGridRecyclerviewClick(object : FacilityAdapter.FacilityItemClickListener{
            override fun gridRecyclerClickListener() {
                startActivity(Intent(requireActivity(), DetailFacilityActivity::class.java))
            }

        })
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFacilityBinding.inflate(layoutInflater)
        settingGridRecyclerview()
        getFacilityData()
        return binding.root
    }

    //gridRecyclerview 설정
    private fun settingGridRecyclerview(){
        binding.apply {
            recyclerviewGridFacility.apply {
                // GridLayoutManager 설정
                val gridLayoutManager = GridLayoutManager(requireContext(), 2) // 2는 열의 수
                recyclerviewGridFacility.layoutManager = gridLayoutManager
                // 어댑터 설정
                adapter = facilityAdapter


            }
        }
    }

    //데이터 받아오기
    private fun getFacilityData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.facilityInfo.observe(requireActivity()){ value ->
                binding.progressBarFacility.visibility = View.GONE
                facilityAdapter.submitList(value)
                Log.d("seonguk1234", "${value}")
            }
        }
    }

}
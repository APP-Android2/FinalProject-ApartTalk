package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFacilityBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityData
import kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter.FacilityAdapter

class FacilityFragment : Fragment() {

    lateinit var binding:FragmentFacilityBinding

    private val itemList: MutableList<FacilityData> = mutableListOf()

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
        return binding.root
    }

    //gridRecyclerview 설정
    private fun settingGridRecyclerview(){
        binding.apply {
            recyclerviewGridFacility.apply {
                // GridLayoutManager 설정
                val gridLayoutManager = GridLayoutManager(requireContext(), 2) // 2는 열의 수
                recyclerviewGridFacility.layoutManager = gridLayoutManager

                // 데이터 준비
                val images = listOf(
                    R.drawable.test_facility,
                    R.drawable.test_facility,
                    R.drawable.test_facility,
                    R.drawable.test_facility
                )
                for (i in images.indices) {
                    itemList.add(FacilityData(null, images[i], "Item ${i + 1}"))
                }

                // 어댑터 설정
                adapter = facilityAdapter

                facilityAdapter.submitList(itemList)

            }
        }
    }
}
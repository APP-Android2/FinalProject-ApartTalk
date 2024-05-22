package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAllLocationBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationAllData
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.AllAdapter

class AllLocationFragment : Fragment() {

    lateinit var binding:FragmentAllLocationBinding

    val allAdapter = AllAdapter()

    val locationAllData = mutableListOf<LocationAllData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllLocationBinding.inflate(layoutInflater)
        settingRecyclerview()
        return binding.root
    }

    private fun settingRecyclerview(){
        binding.apply {
            recyclerviewAll.apply {
                adapter = allAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)


                //임의 설정
                val info = LocationAllData(title = "아파트톡 약국", address = "서울 종로구 종로3길17", "약국")
                locationAllData.add(info)

                allAdapter.submitList(locationAllData)
            }
        }
    }
}
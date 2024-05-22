package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFoodLocationBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationExtraData
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.ExtraAdapter

class FoodLocationFragment : Fragment() {

    lateinit var binding: FragmentFoodLocationBinding

    val extraAdapter = ExtraAdapter()

    val locationExtraData = mutableListOf<LocationExtraData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodLocationBinding.inflate(layoutInflater)
        settingRecyclerview()
        return binding.root
    }

    private fun settingRecyclerview(){
        binding.apply {
            recyclerviewFood.apply {
                adapter = extraAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)


                //임의 설정
                val info = LocationExtraData(title = "아파트톡 삽겹살", address = "서울 종로구 종로3길17")
                locationExtraData.add(info)

                extraAdapter.submitList(locationExtraData)
            }
        }
    }
}
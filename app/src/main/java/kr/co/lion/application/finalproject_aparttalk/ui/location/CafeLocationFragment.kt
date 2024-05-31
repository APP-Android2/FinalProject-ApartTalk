package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCafeLocationBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationAllData
import kr.co.lion.application.finalproject_aparttalk.model.LocationExtraData
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.ExtraAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.location.viewmodel.EachLocationViewModel

class CafeLocationFragment : Fragment() {

    lateinit var binding: FragmentCafeLocationBinding

    val viewModel : EachLocationViewModel by viewModels()

    val extraAdapter : ExtraAdapter by lazy {
        val adapter = ExtraAdapter()
        adapter.setExtraRecyclerviewClick(object : ExtraAdapter.ExtraItemOnClickListener{
            override fun extraRecyclerviewClickListener(name:String, category:String, address:String, number:String, distance:String, x:String, y:String) {
                val newIntent = Intent(requireActivity(), LocationShowActivity::class.java)
                newIntent.putExtra("name", name)
                newIntent.putExtra("category", category)
                newIntent.putExtra("address", address)
                newIntent.putExtra("number", number)
                newIntent.putExtra("distance", distance)
                newIntent.putExtra("x", x)
                newIntent.putExtra("y", y)
                startActivity(newIntent)
            }

        })
        adapter
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCafeLocationBinding.inflate(layoutInflater)
        settingRecyclerview()
        gettingData()
        return binding.root
    }

    private fun settingRecyclerview(){
        binding.apply {
            recyclerviewCafe.apply {
                adapter = extraAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    //데이터 받아오기
    private fun gettingData(){
        viewModel.eachLocationList.observe(viewLifecycleOwner, Observer { places ->
            extraAdapter.submitList(places)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchEachLocationPlace("CE7", "127.05897078335246", "37.506051888130386", 2000)
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAllLocationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.AllAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.location.viewmodel.LocationViewModel

class AllLocationFragment : Fragment() {

    lateinit var binding: FragmentAllLocationBinding

    val viewModel: LocationViewModel by viewModels()

    val allAdapter: AllAdapter by lazy {
        AllAdapter().apply {
            setRecyclerviewClick(object : AllAdapter.ItemOnClickListener {
                override fun recyclerviewClickListener(name:String, category:String, address:String, number:String, distance:String, x:String, y:String) {
                    // 클릭 리스너 처리
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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllLocationBinding.inflate(inflater, container, false)
        settingRecyclerview()
        gettingData()
        return binding.root
    }
    

    private fun settingRecyclerview() {
        binding.apply {
            recyclerviewAll.apply {
                adapter = allAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)

            }
        }
    }

    //데이터 받아오기
    private fun gettingData(){
        viewModel.locationList.observe(viewLifecycleOwner, Observer { places ->
            allAdapter.submitList(places)
        })

        //viewModel.clearCollectedPlaces()


        val y = App.prefs.getLatitude("")
        val x = App.prefs.getLongitude("")

        Log.d("seonguk1234", "$x, $y")

        viewLifecycleOwner.lifecycleScope.launch {
            //화면이 생성될 때 즉시 데이터 받아오기
            viewModel.searchLocationPlace(x, y, 2000)
            viewModel.searchLocationPlace(x, y, 2000)
            viewModel.searchLocationPlace(x, y, 2000)
            viewModel.searchLocationPlace(x, y, 2000)
        }
    }
}

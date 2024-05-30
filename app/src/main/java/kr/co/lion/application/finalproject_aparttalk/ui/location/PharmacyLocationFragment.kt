package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentPharmacyLocationBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationExtraData
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.ExtraAdapter

class PharmacyLocationFragment : Fragment() {

    lateinit var binding: FragmentPharmacyLocationBinding

    val extraAdapter : ExtraAdapter by lazy {
        val adapter = ExtraAdapter()
        adapter.setExtraRecyclerviewClick(object : ExtraAdapter.ExtraItemOnClickListener{
            override fun extraRecyclerviewClickListener() {
                startActivity(Intent(requireActivity(), LocationShowActivity::class.java))
            }

        })
        adapter

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPharmacyLocationBinding.inflate(layoutInflater)
        settingRecyclerview()
        return binding.root
    }

    private fun settingRecyclerview(){
        binding.apply {
            recyclerviewPharmacy.apply {
                adapter = extraAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)

            }
        }
    }
}
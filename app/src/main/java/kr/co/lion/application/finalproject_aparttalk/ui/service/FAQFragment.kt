package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFAQBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.FAQRecyclerViewAdapter

class FAQFragment : Fragment() {

    lateinit var fragmentFAQBinding: FragmentFAQBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentFAQBinding = FragmentFAQBinding.inflate(inflater)
        serviceActivity = activity as ServiceActivity

        settingRecyclerview()

        return fragmentFAQBinding.root

    }

    fun settingRecyclerview(){
        fragmentFAQBinding.apply{
            recyclerViewFAQ.apply{
                adapter = FAQRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(serviceActivity)
                val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
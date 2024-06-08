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
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
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

    private fun settingRecyclerview() {
        // 더미 데이터 생성
        val dummyData = listOf(
            ServiceModel(serviceTitle = "질문 제목 1", serviceDate = "2024.03.01"),
            ServiceModel(serviceTitle = "질문 제목 2", serviceDate = "2024.03.02"),
            ServiceModel(serviceTitle = "질문 제목 3", serviceDate = "2024.03.03")
        )

        fragmentFAQBinding.apply {
            recyclerViewFAQ.apply {
                // 어댑터에 더미 데이터 전달
                adapter = FAQRecyclerViewAdapter(requireContext(), dummyData)
                layoutManager = LinearLayoutManager(serviceActivity)
                val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
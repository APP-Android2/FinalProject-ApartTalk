package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.model.AnnouncementModel
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter


class AnnouncementFragment : Fragment() {
    lateinit var fragmentAnnouncementBinding: FragmentAnnouncementBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAnnouncementBinding = FragmentAnnouncementBinding.inflate(inflater)
        serviceActivity = activity as ServiceActivity

        settingRecyclerview()
        return fragmentAnnouncementBinding.root
    }

    private fun settingRecyclerview() {
        // 더미 데이터 생성
        val dummyData = listOf(
            AnnouncementModel(
                AnnouncementTitle = "첫 번째 공지",
                AnnouncementIdx = 1,
                AnnouncementDate = "2024.06.01",
                AnnouncementContent = "첫 번째 공지 내용입니다."
            ),
            AnnouncementModel(
                AnnouncementTitle = "두 번째 공지",
                AnnouncementIdx = 2,
                AnnouncementDate = "2024.06.02",
                AnnouncementContent = "두 번째 공지 내용입니다."
            ),
            AnnouncementModel(
                AnnouncementTitle = "세 번째 공지",
                AnnouncementIdx = 3,
                AnnouncementDate = "2024.06.03",
                AnnouncementContent = "세 번째 공지 내용입니다."
            )
        )

        fragmentAnnouncementBinding.apply {
            recyclerViewAnnouncement.apply {
                // 어댑터에 더미 데이터 전달
                adapter = AnnouncementRecyclerViewAdapter(requireContext(), dummyData)
                layoutManager = LinearLayoutManager(serviceActivity)
                val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
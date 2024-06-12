package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentViewAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.model.AnnouncementModel
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class ViewAnnouncementFragment : Fragment() {

    lateinit var fragmentViewAnnouncementBinding: FragmentViewAnnouncementBinding
    lateinit var serviceActivity: ServiceActivity

    private var announcement: AnnouncementModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewAnnouncementBinding = FragmentViewAnnouncementBinding.inflate(inflater, container, false)
        serviceActivity = activity as ServiceActivity

        // arguments에서 데이터 추출
        arguments?.let {
            announcement = it.getParcelable("announcement")
        }

        settingToolbar()
        settingButton()
        displayAnnouncement()

        return fragmentViewAnnouncementBinding.root
    }

    private fun settingToolbar() {
        fragmentViewAnnouncementBinding.apply {
            viewAnnouncementToolbar.apply {
                textViewViewAnnouncementToolbarTitle.text = "공지사항"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기
                    serviceActivity.removeFragment(ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT)
                }
            }
        }
    }

    fun settingButton() {
        fragmentViewAnnouncementBinding.apply {
            viewMyAnnouncementButton.setOnClickListener {
                serviceActivity.removeFragment(ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT)
            }
        }
    }

    private fun displayAnnouncement() {
        fragmentViewAnnouncementBinding.apply {
            announcement?.let {
                viewAnnouncementTitle.setText(it.AnnouncementTitle)
                viewAnnouncementTextViewAnsContent.setText(it.AnnouncementContent)
            }
        }
    }
    companion object {
        // Factory method to create a new instance of this fragment with data
        fun newInstance(announcement: AnnouncementModel): ViewAnnouncementFragment {
            val fragment = ViewAnnouncementFragment()
            val args = Bundle().apply {
                putParcelable("announcement", announcement)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
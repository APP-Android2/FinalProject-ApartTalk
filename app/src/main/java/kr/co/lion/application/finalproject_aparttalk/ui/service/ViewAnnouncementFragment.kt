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

    private var announcementTitle: String? = null
    private var announcementDate: String? = null
    private var announcementContent: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewAnnouncementBinding = FragmentViewAnnouncementBinding.inflate(inflater, container, false)
        serviceActivity = activity as ServiceActivity

        // arguments에서 데이터 추출
        arguments?.let {
            announcementTitle = it.getString("title")
            announcementDate = it.getString("date")
            announcementContent = it.getString("content")
        }

        settingToolbar()
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
                    serviceActivity.replaceFragment(
                        ServiceFragmentName.SERVICE_FRAGMENT,
                        true,
                        true,
                        null
                    )
                }
            }
        }
    }

    private fun displayAnnouncement() {
        fragmentViewAnnouncementBinding.apply {
            viewAnnouncementTextViewTitle.text = announcementTitle
            viewAnnouncementTextViewAnsContent.setText(announcementContent)
        }
    }

    companion object {
        // Factory method to create a new instance of this fragment with data
        fun newInstance(announcement: AnnouncementModel): ViewAnnouncementFragment {
            val fragment = ViewAnnouncementFragment()
            val args = Bundle().apply {
                putString("title", announcement.AnnouncementTitle)
                putString("date", announcement.AnnouncementDate)
                putString("content", announcement.AnnouncementContent)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
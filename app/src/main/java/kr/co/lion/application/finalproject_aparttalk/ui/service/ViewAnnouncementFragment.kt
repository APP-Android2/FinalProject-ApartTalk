package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentViewAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class ViewAnnouncementFragment : Fragment() {

    lateinit var fragmentViewAnnouncementBinding: FragmentViewAnnouncementBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this

        fragmentViewAnnouncementBinding = FragmentViewAnnouncementBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        settingToolbar()
        return fragmentViewAnnouncementBinding.root
    }

    fun settingToolbar(){
        fragmentViewAnnouncementBinding.apply {
            viewAnnouncementToolbar.apply{
                textViewViewAnnouncementToolbarTitle.text = "공지사항"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    serviceActivity.replaceFragment(ServiceFragmentName.SERVICE_FRAGMENT, true, true, null)
                }
            }
        }
    }
}
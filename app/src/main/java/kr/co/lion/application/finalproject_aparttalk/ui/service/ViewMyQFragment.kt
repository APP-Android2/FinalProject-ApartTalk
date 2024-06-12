package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyQBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentServiceBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentViewMyQBinding
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName


class ViewMyQFragment : Fragment() {

    lateinit var fragmentViewMyQBinding: FragmentViewMyQBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentViewMyQBinding = FragmentViewMyQBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        settingToolbar()
        settingButton()

        displayServiceDetails() // 서비스 상세 내용 표시 함수 호출

        return fragmentViewMyQBinding.root
    }

    fun settingToolbar() {
        fragmentViewMyQBinding.apply {
            viewMyQToolbar.apply {
                textViewViewMyQToolbarTitle.text = "내 문의"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    serviceActivity.removeFragment(ServiceFragmentName.VIEW_FAQ_FRAGMENT)
                }
            }
        }
    }

    fun settingButton() {
        fragmentViewMyQBinding.apply {
            viewMyQButton.setOnClickListener {
                serviceActivity.removeFragment(ServiceFragmentName.VIEW_FAQ_FRAGMENT)
            }
        }
    }

    // 서비스 상세 내용을 표시하는 함수
    private fun displayServiceDetails() {
        val serviceContent = arguments?.getString("serviceContent") ?: "질문한 내용입니다."
        val serviceAnsContent = arguments?.getString("serviceAnsContent") ?: "답변한 내용입니다."

        fragmentViewMyQBinding.apply {
            viewMyQTextViewContent.setText(serviceContent)
            viewMyQTextViewAnsContent.setText(serviceAnsContent)
        }
    }
}
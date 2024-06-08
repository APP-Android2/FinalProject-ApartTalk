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

        displayServiceDetails() // 서비스 상세 내용 표시 함수 호출

        return fragmentViewMyQBinding.root
    }

    fun settingToolbar() {
        fragmentViewMyQBinding.apply {
            viewMyQToolbar.apply {
                textViewViewMyQToolbarTitle.text = "내 문의"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    serviceActivity.replaceFragment(ServiceFragmentName.SERVICE_FRAGMENT, true, true, null)
                }
            }
        }
    }

    // 서비스 상세 내용을 표시하는 함수
    private fun displayServiceDetails() {
        val serviceTitle = arguments?.getString("serviceTitle") ?: ""
        val serviceContent = arguments?.getString("serviceContent") ?: ""
        val serviceAnsContent = arguments?.getString("serviceAnsContent") ?: ""

        fragmentViewMyQBinding.apply {
            viewMyQTextViewTitle.setText(serviceTitle) // EditText 사용 시 setText() 메서드 사용
            viewMyQTextViewContent.setText(serviceContent) // EditText 사용 시 setText() 메서드 사용
            viewMyQTextViewAnswer.setText(serviceAnsContent) // EditText 사용 시 setText() 메서드 사용
        }
    }
}
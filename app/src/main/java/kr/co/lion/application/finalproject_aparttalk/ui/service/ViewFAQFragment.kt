package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentViewFAQBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentViewMyQBinding


class ViewFAQFragment : Fragment() {

    lateinit var fragmentViewFAQBinding: FragmentViewFAQBinding
    lateinit var serviceActivity: ServiceActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentViewFAQBinding = FragmentViewFAQBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        // Inflate the layout for this fragment
        return fragmentViewFAQBinding.root
    }

    fun settingToolbar(){
        fragmentViewFAQBinding.apply {
            viewFAQToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    serviceActivity.finish()
                }
            }
        }
    }
}
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


class ViewMyQFragment : Fragment() {

    lateinit var fragmentViewMyQBinding: FragmentViewMyQBinding
    lateinit var serviceActivity: ServiceActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentViewMyQBinding = FragmentViewMyQBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        // Inflate the layout for this fragment
        return fragmentViewMyQBinding.root
    }

    fun settingToolbar(){
        fragmentViewMyQBinding.apply {
            viewMyQToolbar.apply {
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
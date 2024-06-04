package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentServiceBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSingleServiceBinding


class SingleServiceFragment : Fragment() {

    lateinit var fragmentSingleServiceBinding : FragmentSingleServiceBinding
    lateinit var serviceActivity: ServiceActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSingleServiceBinding = FragmentSingleServiceBinding.inflate(layoutInflater)
        serviceActivity = activity as ServiceActivity

        settingToolbar()

        return fragmentSingleServiceBinding.root
    }


        fun settingToolbar() {
            fragmentSingleServiceBinding.apply {
                singleServiceToolbar.apply {
                    textViewSingleServiceToolbarTitle.text = "고객센터"
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
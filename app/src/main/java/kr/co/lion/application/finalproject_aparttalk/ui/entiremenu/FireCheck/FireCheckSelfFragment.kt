package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireCheckSelfBinding
import kr.co.lion.application.finalproject_aparttalk.util.FireCheckFragmentName

class FireCheckSelfFragment : Fragment() {

    lateinit var fragmentFireCheckSelfBinding: FragmentFireCheckSelfBinding
    lateinit var fireCheckActivity: FireCheckActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentFireCheckSelfBinding = FragmentFireCheckSelfBinding.inflate(layoutInflater)
        fireCheckActivity = activity as FireCheckActivity

        setToolbar()
        setButton()

        return fragmentFireCheckSelfBinding.root
    }

    // 툴바 구성
    fun setToolbar(){
        fragmentFireCheckSelfBinding.apply {
            toolbarFireCheck2.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    fireCheckActivity.removeFragment(FireCheckFragmentName.FIRE_CHECK_SELF_FRAGMENT)
                }
            }
        }
    }

    fun setButton(){
        fragmentFireCheckSelfBinding.apply {
            // 소화기 cardViewFireCheck1
              // buttonFireExtinguisher1
            buttonFireExtinguisher1.setOnClickListener {
                setBottomSheet()
            }
              // buttonFireExtinguisher2
            buttonFireExtinguisher2.setOnClickListener {
                setBottomSheet()
            }
              // buttonFireExtinguisher3
            buttonFireExtinguisher3.setOnClickListener {
                setBottomSheet()
            }
              // buttonFireExtinguisher4
            buttonFireExtinguisher4.setOnClickListener {
                setBottomSheet()
            }
            // 경보설비 cardViewFireCheck2
              // buttonFireWarning1
            buttonFireWarning1.setOnClickListener {
                setBottomSheet()
            }
              // buttonFireWarning2
            buttonFireWarning2.setOnClickListener {
                setBottomSheet()
            }
            // 피난설비 cardViewFireCheck3
              // buttonRunFacility1
            buttonRunFacility1.setOnClickListener {
                setBottomSheet()
            }
              // buttonRunFacility2
            buttonRunFacility2.setOnClickListener {
                setBottomSheet()
            }
            // 기타설비 cardViewFireCheck4
              // buttonEtcFacility1
            buttonEtcFacility1.setOnClickListener {
                setBottomSheet()
            }
              // buttonEtcFacility2
            buttonEtcFacility2.setOnClickListener {
                setBottomSheet()
            }
        }
    }

    fun setBottomSheet(){
        // 바텀시트 화면이 나타나게 한다.
        val fireProcessBottomSheetFragment = FireProcessBottomSheetFragment()
        fireProcessBottomSheetFragment.show(childFragmentManager, fireProcessBottomSheetFragment.tag)
    }
}
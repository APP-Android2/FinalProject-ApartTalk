package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireCheckSelfBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
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
                // 바텀시트 화면이 나타나게 한다.
                val fireExtinguisher1BottomSheetFragment = FireExtinguisher1BottomSheetFragment()
                fireExtinguisher1BottomSheetFragment.show(childFragmentManager, fireExtinguisher1BottomSheetFragment.tag)
            }
              // buttonFireExtinguisher2
            buttonFireExtinguisher2.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireExtinguisher2BottomSheetFragment = FireExtinguisher2BottomSheetFragment()
                fireExtinguisher2BottomSheetFragment.show(childFragmentManager, fireExtinguisher2BottomSheetFragment.tag)
            }
              // buttonFireExtinguisher3
            buttonFireExtinguisher3.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireExtinguisher3BottomSheetFragment = FireExtinguisher3BottomSheetFragment()
                fireExtinguisher3BottomSheetFragment.show(childFragmentManager, fireExtinguisher3BottomSheetFragment.tag)
            }
              // buttonFireExtinguisher4
            buttonFireExtinguisher4.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireExtinguisher4BottomSheetFragment = FireExtinguisher4BottomSheetFragment()
                fireExtinguisher4BottomSheetFragment.show(childFragmentManager, fireExtinguisher4BottomSheetFragment.tag)
            }
            // 경보설비 cardViewFireCheck2
              // buttonFireWarning1
            buttonFireAlarmFacility1.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireAlarmFacility1BottomSheetFragment = FireAlarmFacility1BottomSheetFragment()
                fireAlarmFacility1BottomSheetFragment.show(childFragmentManager, fireAlarmFacility1BottomSheetFragment.tag)
            }
              // buttonFireWarning2
            buttonFireAlarmFacility2.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireAlarmFacility2BottomSheetFragment = FireAlarmFacility2BottomSheetFragment()
                fireAlarmFacility2BottomSheetFragment.show(childFragmentManager, fireAlarmFacility2BottomSheetFragment.tag)
            }
            // 피난설비 cardViewFireCheck3
              // buttonRunFacility1
            buttonRunFacility1.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireRunFacility1BottomSheetFragment = FireRunFacility1BottomSheetFragment()
                fireRunFacility1BottomSheetFragment.show(childFragmentManager, fireRunFacility1BottomSheetFragment.tag)
            }
              // buttonRunFacility2
            buttonRunFacility2.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireRunFacility2BottomSheetFragment = FireRunFacility2BottomSheetFragment()
                fireRunFacility2BottomSheetFragment.show(childFragmentManager, fireRunFacility2BottomSheetFragment.tag)
            }
            // 기타설비 cardViewFireCheck4
              // buttonEtcFacility1
            buttonEtcFacility1.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireEtcFacility1BottomSheetFragment = FireEtcFacility1BottomSheetFragment()
                fireEtcFacility1BottomSheetFragment.show(childFragmentManager, fireEtcFacility1BottomSheetFragment.tag)
            }
              // buttonEtcFacility2
            buttonEtcFacility2.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireEtcFacility2BottomSheetFragment = FireEtcFacility2BottomSheetFragment()
                fireEtcFacility2BottomSheetFragment.show(childFragmentManager, fireEtcFacility2BottomSheetFragment.tag)
            }
        }
    }
}
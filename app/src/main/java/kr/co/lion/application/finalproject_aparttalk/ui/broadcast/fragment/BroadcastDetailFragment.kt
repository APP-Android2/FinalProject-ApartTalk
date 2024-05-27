package kr.co.lion.application.finalproject_aparttalk.ui.broadcast.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBroadcastDetailBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class BroadcastDetailFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentBroadcastDetailBinding: FragmentBroadcastDetailBinding
    lateinit var broadcastActivity: BroadcastActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBroadcastDetailBinding = FragmentBroadcastDetailBinding.inflate(inflater)
        broadcastActivity = activity as BroadcastActivity

        settingToolbar()
        settingData()
        settingEvent()

        return fragmentBroadcastDetailBinding.root
    }

    // 툴바
    private fun settingToolbar() {
        fragmentBroadcastDetailBinding.apply {
            toolbarBroadcastDetail.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    broadcastActivity.removeFragment(BroadcastFragmentName.BROADCAST_DETIAL_FRAGMENT)
                }
            }
        }
    }

    // 데이터 세팅
    private fun settingData() {
        fragmentBroadcastDetailBinding.apply {
            // 안내 방송 제목
            textViewBroadcastDetailSubject.text = "엘리베이터 수리"
            // 안내 방송 내용
            textViewBroadcastDetailContent.text = "우리 000 아파트는 05/31일부터 엘레베이터 보수공사가 진행될 예정입니다.\n" +
                    "\n05/31 ~ 06/13일 까지는 101동, 102동, 103동 엘리베이터가 공사 예정이고\n" +
                    "\n" +
                    "06/14 ~ 06/30일 까지는 104동, 105동, 106동 엘레베이터가 공사 예정입니다\n" +
                    "\n" +
                    "이미 확인하시어 불편한 일을 겪지 않도록 해주시길 바랍니다\n" +
                    "감사합니다.\n" +
                    "\n" +
                    "-관리 사무소-"
        }
    }

    // 확인 버튼
    private fun settingEvent() {
        fragmentBroadcastDetailBinding.apply {
            buttonBroadcastDetailConfirm.setOnClickListener {
                broadcastActivity.removeFragment(BroadcastFragmentName.BROADCAST_DETIAL_FRAGMENT)
            }
        }
    }
}
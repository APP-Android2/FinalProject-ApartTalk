package kr.co.lion.application.finalproject_aparttalk.ui.broadcast.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBroadcastDetailBinding
import kr.co.lion.application.finalproject_aparttalk.model.BroadcastModel
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class BroadcastDetailFragment() : Fragment() {
    lateinit var fragmentBroadcastDetailBinding: FragmentBroadcastDetailBinding
    lateinit var broadcastActivity: BroadcastActivity
    private var broadcastTitle: String? = null
    private var broadcastContent: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBroadcastDetailBinding = FragmentBroadcastDetailBinding.inflate(inflater)
        broadcastActivity = activity as BroadcastActivity

        broadcastTitle = arguments?.getString("broadcastTitle")
        broadcastContent = arguments?.getString("broadcastContent")

        settingToolbar()
        settingData()
        settingEvent()

        return fragmentBroadcastDetailBinding.root
    }

    // 툴바 설정
    private fun settingToolbar() {
        fragmentBroadcastDetailBinding.apply {
            toolbarBroadcastDetail.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    broadcastActivity.removeFragment(BroadcastFragmentName.BROADCAST_DETAIL_FRAGMENT)
                }
            }
        }
    }

    // 데이터 설정
    private fun settingData() {
        fragmentBroadcastDetailBinding.apply {
            textViewBroadcastDetailSubject.text = broadcastTitle
            textViewBroadcastDetailContent.text = broadcastContent
        }
    }

    // 확인 버튼 이벤트 설정
    private fun settingEvent() {
        fragmentBroadcastDetailBinding.apply {
            buttonBroadcastDetailConfirm.setOnClickListener {
                broadcastActivity.removeFragment(BroadcastFragmentName.BROADCAST_DETAIL_FRAGMENT)
            }
        }
    }
}
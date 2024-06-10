package kr.co.lion.application.finalproject_aparttalk.ui.broadcast.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBroadcastBinding
import kr.co.lion.application.finalproject_aparttalk.model.BroadcastModel
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.adapter.BroadcastRecyclerViewAdapter

class BroadcastFragment(val data: Bundle?) : Fragment() {
    lateinit var fragmentBroadcastBinding: FragmentBroadcastBinding
    lateinit var broadcastActivity: BroadcastActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBroadcastBinding = FragmentBroadcastBinding.inflate(inflater)
        broadcastActivity = activity as BroadcastActivity

        settingToolbar()
        settingRecyclerViewBroadcast()

        return fragmentBroadcastBinding.root
    }

    // 툴바 설정
    private fun settingToolbar() {
        fragmentBroadcastBinding.apply {
            toolbarBroadcast.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    broadcastActivity.finish()
                }
            }
        }
    }

    // 안내 방송 리사이클러뷰 설정
    private fun settingRecyclerViewBroadcast() {
        fragmentBroadcastBinding.apply {
            recyclerViewBroadcast.apply {
                adapter = BroadcastRecyclerViewAdapter(requireContext(), broadcastActivity)
                layoutManager = LinearLayoutManager(broadcastActivity)
                val deco = MaterialDividerItemDecoration(broadcastActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
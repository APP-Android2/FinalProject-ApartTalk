package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityTabNotificationRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabNotificationFragment : Fragment() {
    lateinit var fragmentTabNotificationBinding: FragmentTabNotificationBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabNotificationBinding = FragmentTabNotificationBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewTabNotification()
        settingFabTabNotificationAdd()
        settingFloatingButton()

        return fragmentTabNotificationBinding.root
    }

    // 커뮤니티 공지 탭 플로팅 버튼
    private fun settingFabTabNotificationAdd() {
        fragmentTabNotificationBinding.apply {
            fabTabNotificationAdd.setOnClickListener {
                val intent = Intent(mainActivity, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_ADD_FRAGMENT)
                startActivity(intent)
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabNotificationBinding.apply {
            recyclerViewTabNotification.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabNotificationAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabNotificationAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }

    // 커뮤니티 공지 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabNotification() {
        fragmentTabNotificationBinding.apply {
            recyclerViewTabNotification.apply {
                adapter = CommunityTabNotificationRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabNotificationBinding

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
                adapter = CommunityTabNotificationRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 커뮤니티 공지 탭 리사이클러뷰 어댑터
    inner class CommunityTabNotificationRecyclerViewAdapter : RecyclerView.Adapter<CommunityTabNotificationRecyclerViewAdapter.CommunityTabNotificationViewHolder>() {
        inner class CommunityTabNotificationViewHolder(rowCommunityTabNotificationBinding: RowCommunityTabNotificationBinding) : RecyclerView.ViewHolder(rowCommunityTabNotificationBinding.root) {
            val rowCommunityTabNotificationBinding : RowCommunityTabNotificationBinding

            init {
                this.rowCommunityTabNotificationBinding = rowCommunityTabNotificationBinding

                this.rowCommunityTabNotificationBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabNotificationViewHolder {
            val rowCommunityTabNotificationBinding = RowCommunityTabNotificationBinding.inflate(layoutInflater)
            val communityTabNotificationViewHolder = CommunityTabNotificationViewHolder(rowCommunityTabNotificationBinding)

            return communityTabNotificationViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunityTabNotificationViewHolder, position: Int) {
            holder.rowCommunityTabNotificationBinding.apply {
                textViewCommunityListLabelNotification.text = "공지"
                textViewCommunityListTitleNotification.text = "글 제목입니다 $position"
                textViewCommunityListContentNotification.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                        "글 내용입니다 글 내용입니다 글 내용입니다 "
                textViewCommunityListLikeCntNotification.text = "999"
                textViewCommunityListCommentCntNotification.text = "999"
                textViewCommunityListDateNotification.text = "2024.05.17"
            }
        }
    }
}
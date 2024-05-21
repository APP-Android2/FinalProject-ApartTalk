package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabEtcFragment : Fragment() {
    lateinit var fragmentTabEtcBinding: FragmentTabEtcBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabEtcBinding = FragmentTabEtcBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewTabTrade()
        settingFabTabEtcAdd()
        settingFloatingButton()

        return fragmentTabEtcBinding.root
    }

    // 커뮤니티 거래 탭 플로팅 버튼
    private fun settingFabTabEtcAdd() {
        fragmentTabEtcBinding.apply {
            fabTabEtcAdd.setOnClickListener {

            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabEtcBinding.apply {
            recyclerViewTabEtc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabEtcAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabEtcAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }

    // 커뮤니티 기타 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabTrade() {
        fragmentTabEtcBinding.apply {
            recyclerViewTabEtc.apply {
                adapter = CommunityTabEtcRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 커뮤니티 기타 탭 리사이클러뷰 어댑터
    inner class CommunityTabEtcRecyclerViewAdapter : RecyclerView.Adapter<CommunityTabEtcRecyclerViewAdapter.CommunityTabEtcViewHolder>() {
        inner class CommunityTabEtcViewHolder(rowCommunityTabEtcBinding: RowCommunityTabEtcBinding) : RecyclerView.ViewHolder(rowCommunityTabEtcBinding.root) {
            val rowCommunityTabEtcBinding : RowCommunityTabEtcBinding

            init {
                this.rowCommunityTabEtcBinding = rowCommunityTabEtcBinding

                this.rowCommunityTabEtcBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabEtcViewHolder {
            val rowCommunityTabEtcBinding = RowCommunityTabEtcBinding.inflate(layoutInflater)
            val communityTabEtcViewHolder = CommunityTabEtcViewHolder(rowCommunityTabEtcBinding)

            return communityTabEtcViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunityTabEtcViewHolder, position: Int) {
            holder.rowCommunityTabEtcBinding.apply {
                textViewCommunityListLabelEtc.text = "기타"
                textViewCommunityListTitleEtc.text = "글 제목입니다 $position"
                textViewCommunityListContentEtc.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                        "글 내용입니다 글 내용입니다 글 내용입니다 "
                textViewCommunityListLikeCntEtc.text = "999"
                textViewCommunityListCommentCntEtc.text = "999"
                textViewCommunityListDateEtc.text = "2024.05.17"

                linearLayoutCommunityListEtc.setOnClickListener {
                    val intent = Intent(mainActivity, CommunityActivity::class.java)
                    intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                    // 게시글 번호도 주기
                    // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                    startActivity(intent)
                }
            }
        }
    }
}
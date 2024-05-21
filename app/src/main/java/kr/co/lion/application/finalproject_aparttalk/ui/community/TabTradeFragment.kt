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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabTradeFragment : Fragment() {
    lateinit var fragmentTabTradeBinding: FragmentTabTradeBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabTradeBinding = FragmentTabTradeBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewTabTrade()
        settingFabTabTradeAdd()
        settingFloatingButton()

        return fragmentTabTradeBinding.root
    }

    // 커뮤니티 거래 탭 플로팅 버튼
    private fun settingFabTabTradeAdd() {
        fragmentTabTradeBinding.apply {
            fabTabTradeAdd.setOnClickListener {

            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabTradeBinding.apply {
            recyclerViewTabTrade.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabTradeAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabTradeAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }


    // 커뮤니티 거래 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabTrade() {
        fragmentTabTradeBinding.apply {
            recyclerViewTabTrade.apply {
                adapter = CommunityTabTradeRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 커뮤니티 거래 탭 리사이클러뷰 어댑터
    inner class CommunityTabTradeRecyclerViewAdapter : RecyclerView.Adapter<CommunityTabTradeRecyclerViewAdapter.CommunityTabTradeViewHolder>() {
        inner class CommunityTabTradeViewHolder(rowCommunityTabTradeBinding: RowCommunityTabTradeBinding) : RecyclerView.ViewHolder(rowCommunityTabTradeBinding.root) {
            val rowCommunityTabTradeBinding : RowCommunityTabTradeBinding

            init {
                this.rowCommunityTabTradeBinding = rowCommunityTabTradeBinding

                this.rowCommunityTabTradeBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabTradeViewHolder {
            val rowCommunityTabTradeBinding = RowCommunityTabTradeBinding.inflate(layoutInflater)
            val communityTabTradeViewHolder = CommunityTabTradeViewHolder(rowCommunityTabTradeBinding)

            return communityTabTradeViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunityTabTradeViewHolder, position: Int) {
            holder.rowCommunityTabTradeBinding.apply {
                textViewCommunityListLabelTrade.text = "거래"
                textViewCommunityListTitleTrade.text = "글 제목입니다 $position"
                textViewCommunityListContentTrade.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                        "글 내용입니다 글 내용입니다 글 내용입니다 "
                textViewCommunityListLikeCntTrade.text = "999"
                textViewCommunityListCommentCntTrade.text = "999"
                textViewCommunityListDateTrade.text = "2024.05.17"

                linearLayoutCommunityListTrade.setOnClickListener {
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
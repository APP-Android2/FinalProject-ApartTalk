package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding

class CommunitySearchFragment : Fragment() {
    lateinit var fragmentCommunitySearchBinding: FragmentCommunitySearchBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunitySearchBinding = FragmentCommunitySearchBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingEvent()
        settingRecyclerViewSearch()

        return fragmentCommunitySearchBinding.root
    }

    // 뒤로 가기
    private fun settingEvent() {
        fragmentCommunitySearchBinding.apply {
            textInputLayoutCommunitySearch.apply {
                setStartIconOnClickListener {
                    communityActivity.finish()
                }
            }
        }
    }

    // 커뮤니티 검색 리사이클러뷰 설정
    private fun settingRecyclerViewSearch() {
        fragmentCommunitySearchBinding.apply {
            recyclerViewCommunitySearch.apply {
                adapter = CommunitySearchRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(communityActivity)
                val deco = MaterialDividerItemDecoration(communityActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 커뮤니티 검색 리사이클러뷰 어댑터
    inner class CommunitySearchRecyclerViewAdapter : RecyclerView.Adapter<CommunitySearchRecyclerViewAdapter.CommunitySearchViewHolder>() {
        inner class CommunitySearchViewHolder(rowCommunitySearchBinding: RowCommunitySearchBinding) : RecyclerView.ViewHolder(rowCommunitySearchBinding.root) {
            val rowCommunitySearchBinding : RowCommunitySearchBinding

            init {
                this.rowCommunitySearchBinding = rowCommunitySearchBinding

                this.rowCommunitySearchBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunitySearchViewHolder {
            val rowCommunitySearchBinding = RowCommunitySearchBinding.inflate(layoutInflater)
            val communitySearchViewHolder = CommunitySearchViewHolder(rowCommunitySearchBinding)

            return communitySearchViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunitySearchViewHolder, position: Int) {
            holder.rowCommunitySearchBinding.apply {
                textViewCommunityListLabelSearch.text = "기타"
                textViewCommunityListTitleSearch.text = "글 제목입니다 $position"
                textViewCommunityListContentSearch.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                        "글 내용입니다 글 내용입니다 글 내용입니다 "
                textViewCommunityListLikeCntSearch.text = "999"
                textViewCommunityListCommentCntSearch.text = "999"
                textViewCommunityListDateSearch.text = "2024.05.17"
            }
        }
    }
}
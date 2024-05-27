package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunitySearchFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunitySearchRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<CommunitySearchRecyclerViewAdapter.CommunitySearchViewHolder>() {
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
        val rowCommunitySearchBinding = RowCommunitySearchBinding.inflate(LayoutInflater.from(parent.context))
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
            textViewCommunityListLikeCntSearch.text = "999"
            textViewCommunityListCommentCntSearch.text = "999"
            textViewCommunityListDateSearch.text = "2024.05.17"

            linearLayoutCommunityListSearch.setOnClickListener {
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, false, true, null)
            }
        }
    }
}
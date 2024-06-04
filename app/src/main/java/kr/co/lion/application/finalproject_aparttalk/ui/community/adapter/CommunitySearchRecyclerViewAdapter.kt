package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunitySearchFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunitySearchRecyclerViewAdapter(val context: Context, var searchList: MutableList<PostData>) : RecyclerView.Adapter<CommunitySearchRecyclerViewAdapter.CommunitySearchViewHolder>() {
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
        return searchList.size
    }

    override fun onBindViewHolder(holder: CommunitySearchViewHolder, position: Int) {
        holder.rowCommunitySearchBinding.apply {
            textViewCommunityListLabelSearch.text = searchList[position].postType
            textViewCommunityListTitleSearch.text = searchList[position].postTitle
            textViewCommunityListLikeCntSearch.text = searchList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntSearch.text = searchList[position].postCommentCnt.toString()
            textViewCommunityListDateSearch.text = searchList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (searchList[position].postImages != null) {
                    // 어떻게 해야 하나...
                } else {
                    imageViewCommunityListSearch.setImageResource(R.color.white)
                }
            }

            linearLayoutCommunityListSearch.setOnClickListener {
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, false, true, null)
            }
        }
    }
}
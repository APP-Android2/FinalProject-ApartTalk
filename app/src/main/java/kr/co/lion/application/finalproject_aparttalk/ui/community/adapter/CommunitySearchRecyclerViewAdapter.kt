package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunitySearchViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunitySearchRecyclerViewAdapter(val context: Context, var viewModel: CommunitySearchViewModel) : RecyclerView.Adapter<CommunitySearchRecyclerViewAdapter.CommunitySearchViewHolder>() {
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
        return viewModel.searchList.size
    }

    override fun onBindViewHolder(holder: CommunitySearchViewHolder, position: Int) {
        holder.rowCommunitySearchBinding.apply {
            textViewCommunityListLabelSearch.text = viewModel.searchList[position].postType
            textViewCommunityListTitleSearch.text = viewModel.searchList[position].postTitle
            textViewCommunityListLikeCntSearch.text = viewModel.searchList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntSearch.text = viewModel.searchList[position].postCommentCnt.toString()
            textViewCommunityListDateSearch.text = viewModel.searchList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.searchList[position].postImages != null) {
                    viewModel.gettingCommunityPostImage(context, viewModel.searchList[position].postImages!![0], imageViewCommunityListSearch)
                } else {
                    imageViewCommunityListSearch.setImageResource(R.color.white)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.searchList[position].postApartId, viewModel.searchList[position].postId)
                textViewCommunityListCommentCntSearch.text = viewModel.commentList.size.toString()
            }

            linearLayoutCommunityListSearch.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("postIdx", viewModel.searchList[position].postIdx)
                bundle.putString("postId", viewModel.searchList[position].postId)
                bundle.putString("postApartId", viewModel.searchList[position].postApartId)
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, false, true, bundle)
            }
        }
    }
}
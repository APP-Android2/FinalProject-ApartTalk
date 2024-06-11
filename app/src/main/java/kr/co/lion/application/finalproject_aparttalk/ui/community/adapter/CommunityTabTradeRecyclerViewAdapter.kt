package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityQuestionViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityTradeViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabTradeRecyclerViewAdapter(val context: Context, var viewModel: CommunityTradeViewModel) : RecyclerView.Adapter<CommunityTabTradeRecyclerViewAdapter.CommunityTabTradeViewHolder>() {
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
        val rowCommunityTabTradeBinding = RowCommunityTabTradeBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabTradeViewHolder = CommunityTabTradeViewHolder(rowCommunityTabTradeBinding)

        return communityTabTradeViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.tradeList.size
    }

    override fun onBindViewHolder(holder: CommunityTabTradeViewHolder, position: Int) {
        holder.rowCommunityTabTradeBinding.apply {
            textViewCommunityListLabelTrade.text = viewModel.tradeList[position].postType
            textViewCommunityListTitleTrade.text = viewModel.tradeList[position].postTitle
            textViewCommunityListLikeCntTrade.text = viewModel.tradeList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntTrade.text = viewModel.tradeList[position].postCommentCnt.toString()
            textViewCommunityListDateTrade.text = viewModel.tradeList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.tradeList[position].postImages != null) {
                    viewModel.gettingCommunityPostImage(context, viewModel.tradeList[position].postImages!![0], imageViewCommunityListTrade)
                } else {
                    imageViewCommunityListTrade.setImageResource(R.color.white)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.tradeList[position].postApartId, viewModel.tradeList[position].postId)
                textViewCommunityListCommentCntTrade.text = viewModel.commentList.size.toString()
            }

            linearLayoutCommunityListTrade.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", viewModel.tradeList[position].postIdx)
                intent.putExtra("postId", viewModel.tradeList[position].postId)
                intent.putExtra("postApartId", viewModel.tradeList[position].postApartId)
                context.startActivity(intent)
            }
        }
    }
}
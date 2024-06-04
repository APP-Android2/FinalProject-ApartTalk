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
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabTradeRecyclerViewAdapter(val context: Context, var tradeList: MutableList<PostData>) : RecyclerView.Adapter<CommunityTabTradeRecyclerViewAdapter.CommunityTabTradeViewHolder>() {
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
        return tradeList.size
    }

    override fun onBindViewHolder(holder: CommunityTabTradeViewHolder, position: Int) {
        holder.rowCommunityTabTradeBinding.apply {
            textViewCommunityListLabelTrade.text = tradeList[position].postType
            textViewCommunityListTitleTrade.text = tradeList[position].postTitle
            textViewCommunityListLikeCntTrade.text = tradeList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntTrade.text = tradeList[position].postCommentCnt.toString()
            textViewCommunityListDateTrade.text = tradeList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (tradeList[position].postImages != null) {
                    // 어떻게 해야 하나...
                } else {
                    imageViewCommunityListTrade.setImageResource(R.color.white)
                }
            }

            linearLayoutCommunityListTrade.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", tradeList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
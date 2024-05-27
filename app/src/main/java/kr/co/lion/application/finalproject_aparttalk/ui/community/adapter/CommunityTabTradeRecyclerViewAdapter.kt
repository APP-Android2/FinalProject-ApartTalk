package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabTradeRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<CommunityTabTradeRecyclerViewAdapter.CommunityTabTradeViewHolder>() {
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
        return 10
    }

    override fun onBindViewHolder(holder: CommunityTabTradeViewHolder, position: Int) {
        holder.rowCommunityTabTradeBinding.apply {
            textViewCommunityListLabelTrade.text = "거래"
            textViewCommunityListTitleTrade.text = "글 제목입니다 $position"
            textViewCommunityListLikeCntTrade.text = "999"
            textViewCommunityListCommentCntTrade.text = "999"
            textViewCommunityListDateTrade.text = "2024.05.17"

            linearLayoutCommunityListTrade.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                // 게시글 번호도 주기
                // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
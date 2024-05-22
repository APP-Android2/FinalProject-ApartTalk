package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailCommentBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment

class CommunityDetailCommentRecyclerViewAdapter(val context : Context) : RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.CommunityDetailViewHolder>(){
    inner class CommunityDetailViewHolder(rowCommunityDetailCommentBinding: RowCommunityDetailCommentBinding) : RecyclerView.ViewHolder(rowCommunityDetailCommentBinding.root) {
        val rowCommunityDetailCommentBinding: RowCommunityDetailCommentBinding

        init {
            this.rowCommunityDetailCommentBinding = rowCommunityDetailCommentBinding

            this.rowCommunityDetailCommentBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityDetailViewHolder {
        val rowCommunityDetailCommentBinding = RowCommunityDetailCommentBinding.inflate(LayoutInflater.from(parent.context))
        val communityDetailViewHolder = CommunityDetailViewHolder(rowCommunityDetailCommentBinding)

        return communityDetailViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CommunityDetailViewHolder, position: Int) {
        holder.rowCommunityDetailCommentBinding.apply {
            textViewRowCommunityDetailCommentWriter.text = "댓글 작성자"
            textViewRowCommunityDetailCommentContent.text = "댓글입니다 댓글입니다 댓글입니다 댓글입니다 (100자 제한)"
        }
    }
}
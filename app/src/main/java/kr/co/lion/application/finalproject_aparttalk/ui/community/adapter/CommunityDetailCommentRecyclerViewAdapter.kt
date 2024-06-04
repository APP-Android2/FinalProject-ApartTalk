package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailCommentBinding
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment
import kr.co.lion.application.finalproject_aparttalk.util.Tools

class CommunityDetailCommentRecyclerViewAdapter(val context : Context, var commentList: MutableList<CommentData>, var fragment: CommunityDetailFragment) : RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.CommunityDetailViewHolder>(){
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
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommunityDetailViewHolder, position: Int) {
        holder.rowCommunityDetailCommentBinding.apply {
            textViewRowCommunityDetailCommentWriter.text = "김길동"
            textViewRowCommunityDetailCommentContent.text = commentList[position].commentContent

            // 수정 기능
            imageViewRowCommunityDetailCommentModify.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    fragment.fragmentCommunityDetailBinding.textInputCommunityDetailSendComment.setText(commentList[position].commentContent)
                    Tools.showSoftInput(context, fragment.fragmentCommunityDetailBinding.textInputLayoutCommunityDetailSendComment)
                    fragment.fragmentCommunityDetailBinding.imageViewCommunityDetailSendComment.setOnClickListener {
                        fragment.commentModifyProcess(position, commentList[position].commentIdx)
                    }
                }
            }
        }
    }
}
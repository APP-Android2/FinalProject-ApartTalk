package kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMylikeTabLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMywroteTabWroteBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyLikeRecyclerViewAdapter(
    val context: Context,
    private val itemClickListener: OnItemClickListener
) : ListAdapter<PostData, MyLikeRecyclerViewAdapter.MyLikeViewHolder>(PostDiffCallback()) {

    inner class MyLikeViewHolder(val binding: RowMywroteTabWroteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikeViewHolder {
        val binding = RowMywroteTabWroteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLikeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyLikeViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.apply {
            textViewMyWroteListTitle.text = post.postTitle
            textViewMyWroteListDate.text = post.postAddDate
            textViewMyWroteListCommentCnt.text = post.postCommentCnt.toString()
            textViewMyWroteListLikeCnt.text = post.postLikeCnt.toString()
            textViewMyWroteListContent.text = post.postContent

            rowMyWroteLayout.setOnClickListener {
                itemClickListener.onItemClick(post)
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PostData>() {
        override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem.postIdx == newItem.postIdx
        }

        override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(post: PostData)
}
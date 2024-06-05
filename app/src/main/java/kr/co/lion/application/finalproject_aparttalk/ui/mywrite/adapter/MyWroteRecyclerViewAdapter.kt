package kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMywroteTabWroteBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class MyWroteRecyclerViewAdapter(val context: Context) : ListAdapter<PostData, MyWroteRecyclerViewAdapter.MyWroteViewHolder>(PostDiffCallback()) {

    inner class MyWroteViewHolder(val binding: RowMywroteTabWroteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWroteViewHolder {
        val binding = RowMywroteTabWroteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyWroteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWroteViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.apply {
            textViewMyWroteListTitle.text = post.postTitle
            textViewMyWroteListDate.text = post.postAddDate
            textViewMyWroteListCommentCnt.text = post.postCommentCnt.toString()
            textViewMyWroteListLikeCnt.text = post.postLikeCnt.toString()
            textViewMyWroteListContent.text = post.postContent

            rowMyWroteLayout.setOnClickListener {
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, true, true, null)
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

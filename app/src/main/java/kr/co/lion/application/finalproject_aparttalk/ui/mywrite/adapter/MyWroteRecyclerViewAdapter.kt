package kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMywroteTabWroteBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWroteViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class MyWroteRecyclerViewAdapter(val context: Context, val viewModel: MyWroteViewModel) : ListAdapter<PostData, MyWroteRecyclerViewAdapter.MyWroteViewHolder>(PostDiffCallback()) {

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

            CoroutineScope(Dispatchers.Main).launch {
                if (post.postImages != null) {
                    viewModel.gettingCommunityPostImage(context, post.postImages!![0], imageViewMyWroteListTrade)
                } else {
                    imageViewMyWroteListTrade.setImageResource(R.color.white)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(post.postApartId, post.postId)
                textViewMyWroteListCommentCnt.text = viewModel.commentList.size.toString()
            }

            rowMyWroteLayout.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", post.postIdx)
                intent.putExtra("postId", post.postId)
                intent.putExtra("postApartId", post.postApartId)
                context.startActivity(intent)
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

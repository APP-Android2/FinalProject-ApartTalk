package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityNotificationViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabNotificationRecyclerViewAdapter(val context: Context, var viewModel: CommunityNotificationViewModel) : RecyclerView.Adapter<CommunityTabNotificationRecyclerViewAdapter.CommunityTabNotificationViewHolder>() {
    inner class CommunityTabNotificationViewHolder(rowCommunityTabNotificationBinding: RowCommunityTabNotificationBinding) : RecyclerView.ViewHolder(rowCommunityTabNotificationBinding.root) {
        val rowCommunityTabNotificationBinding : RowCommunityTabNotificationBinding

        init {
            this.rowCommunityTabNotificationBinding = rowCommunityTabNotificationBinding

            this.rowCommunityTabNotificationBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabNotificationViewHolder {
        val rowCommunityTabNotificationBinding = RowCommunityTabNotificationBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabNotificationViewHolder = CommunityTabNotificationViewHolder(rowCommunityTabNotificationBinding)

        return communityTabNotificationViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.notificationList.size
    }

    override fun onBindViewHolder(holder: CommunityTabNotificationViewHolder, position: Int) {

        holder.rowCommunityTabNotificationBinding.apply {
            textViewCommunityListLabelNotification.text = viewModel.notificationList[position].postType
            textViewCommunityListTitleNotification.text = viewModel.notificationList[position].postTitle
            textViewCommunityListLikeCntNotification.text = viewModel.notificationList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntNotification.text = viewModel.notificationList[position].postCommentCnt.toString()
            textViewCommunityListDateNotification.text = viewModel.notificationList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.notificationList[position].postImages != null) {
                    viewModel.gettingCommunityPostImage(context, viewModel.notificationList[position].postImages!![0], imageViewCommunityListNotification)
                } else {
                    imageViewCommunityListNotification.setImageResource(R.color.white)
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.notificationList[position].postApartId, viewModel.notificationList[position].postId)
                textViewCommunityListCommentCntNotification.text = viewModel.commentList.size.toString()
            }

            linearLayoutCommunityListNotification.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", viewModel.notificationList[position].postIdx)
                intent.putExtra("postId", viewModel.notificationList[position].postId)
                intent.putExtra("postApartId", viewModel.notificationList[position].postApartId)
                context.startActivity(intent)
            }
        }
    }
}
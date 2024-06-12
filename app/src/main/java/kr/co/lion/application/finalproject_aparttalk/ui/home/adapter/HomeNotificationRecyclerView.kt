package kr.co.lion.application.finalproject_aparttalk.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowHomeNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityNotificationViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class HomeNotificationRecyclerView(val context: Context, val viewModel: CommunityNotificationViewModel)  : RecyclerView.Adapter<HomeNotificationRecyclerView.HomeNotificationViewHolder>() {
    inner class HomeNotificationViewHolder(rowHomeNotificationBinding: RowHomeNotificationBinding) : RecyclerView.ViewHolder(rowHomeNotificationBinding.root) {
        val rowHomeNotificationBinding: RowHomeNotificationBinding

        init {
            this.rowHomeNotificationBinding = rowHomeNotificationBinding

            this.rowHomeNotificationBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNotificationViewHolder {
        val rowHomeNotificationBinding = RowHomeNotificationBinding.inflate(LayoutInflater.from(parent.context))
        val homeNotificationViewHolder = HomeNotificationViewHolder(rowHomeNotificationBinding)

        return homeNotificationViewHolder
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: HomeNotificationViewHolder, position: Int) {
        if (viewModel.notificationList.isNotEmpty()) {
            holder.rowHomeNotificationBinding.apply {
                textViewHomeListLabelNotification.text = viewModel.notificationList[0].postType
                textViewHomeListTitleNotification.text = viewModel.notificationList[0].postTitle
                textViewCommunityListLikeCntNotification.text = viewModel.notificationList[0].postLikeCnt.toString()
                textViewHomeListCommentCntNotification.text = viewModel.notificationList[0].postCommentCnt.toString()
                textViewHomeListDateNotification.text = viewModel.notificationList[0].postAddDate

                CoroutineScope(Dispatchers.Main).launch {
                    if (viewModel.notificationList[0].postImages != null) {
                        viewModel.gettingCommunityPostImage(context, viewModel.notificationList[0].postImages!![0], imageViewHomeListNotification)
                    } else {
                        imageViewHomeListNotification.setImageResource(R.color.white)
                    }
                }
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.notificationList[0].postApartId, viewModel.notificationList[0].postId)
                    textViewHomeListCommentCntNotification.text = viewModel.commentList.size.toString()
                }

                linearLayoutHomeListNotification.setOnClickListener {
                    val intent = Intent(context, CommunityActivity::class.java)
                    intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                    intent.putExtra("postIdx", viewModel.notificationList[0].postIdx)
                    intent.putExtra("postId", viewModel.notificationList[0].postId)
                    intent.putExtra("postApartId", viewModel.notificationList[0].postApartId)
                    context.startActivity(intent)
                }
            }
        } else {
            // 빈 리스트일 경우 처리
            holder.rowHomeNotificationBinding.textViewHomeListLabelNotification.text = "No notifications available"
        }
    }
}
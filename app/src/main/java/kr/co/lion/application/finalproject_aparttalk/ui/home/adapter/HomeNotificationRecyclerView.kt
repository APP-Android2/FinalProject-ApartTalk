package kr.co.lion.application.finalproject_aparttalk.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowHomeNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class HomeNotificationRecyclerView(val context: Context)  : RecyclerView.Adapter<HomeNotificationRecyclerView.HomeNotificationViewHolder>() {
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
        holder.rowHomeNotificationBinding.apply {
            textViewHomeLabelNotification.text = "공지"
            textViewHomeTitleNotification.text = "글 제목입니다 $position"
            textViewHomeContentNotification.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                    "글 내용입니다 글 내용입니다 글 내용입니다 "
            textViewHomeLikeCntNotification.text = "999"
            textViewHomeCommentCntNotification.text = "999"
            textViewHomeDateNotification.text = "2024.05.17"

            linearLayoutHomeNotification.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                // 게시글 번호도 주기
                // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
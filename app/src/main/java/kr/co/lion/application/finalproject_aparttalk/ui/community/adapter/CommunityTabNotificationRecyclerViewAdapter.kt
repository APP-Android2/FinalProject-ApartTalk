package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabNotificationRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<CommunityTabNotificationRecyclerViewAdapter.CommunityTabNotificationViewHolder>() {
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
        return 10
    }

    override fun onBindViewHolder(holder: CommunityTabNotificationViewHolder, position: Int) {
        holder.rowCommunityTabNotificationBinding.apply {
            textViewCommunityListLabelNotification.text = "공지"
            textViewCommunityListTitleNotification.text = "글 제목입니다 $position"
            textViewCommunityListLikeCntNotification.text = "999"
            textViewCommunityListCommentCntNotification.text = "999"
            textViewCommunityListDateNotification.text = "2024.05.17"

            linearLayoutCommunityListNotification.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                // 게시글 번호도 주기
                // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
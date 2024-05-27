package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabQuestionRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<CommunityTabQuestionRecyclerViewAdapter.CommunityTabQuestionViewHolder>() {
    inner class CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding: RowCommunityTabQuestionBinding) : RecyclerView.ViewHolder(rowCommunityTabQuestionBinding.root) {
        val rowCommunityTabQuestionBinding : RowCommunityTabQuestionBinding

        init {
            this.rowCommunityTabQuestionBinding = rowCommunityTabQuestionBinding

            this.rowCommunityTabQuestionBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabQuestionViewHolder {
        val rowCommunityTabQuestionBinding = RowCommunityTabQuestionBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabQuestionViewHolder = CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding)

        return communityTabQuestionViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CommunityTabQuestionViewHolder, position: Int) {
        holder.rowCommunityTabQuestionBinding.apply {
            textViewCommunityListLabelQuestion.text = "질문"
            textViewCommunityListTitleQuestion.text = "글 제목입니다 $position"
            textViewCommunityListLikeCntQuestion.text = "999"
            textViewCommunityListCommentCntQuestion.text = "999"
            textViewCommunityListDateQuestion.text = "2024.05.17"

            linearLayoutCommunityListQuestion.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                // 게시글 번호도 주기
                // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
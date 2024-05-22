package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.TabEtcFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabEtcRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<CommunityTabEtcRecyclerViewAdapter.CommunityTabEtcViewHolder>() {
    inner class CommunityTabEtcViewHolder(rowCommunityTabEtcBinding: RowCommunityTabEtcBinding) : RecyclerView.ViewHolder(rowCommunityTabEtcBinding.root) {
        val rowCommunityTabEtcBinding : RowCommunityTabEtcBinding

        init {
            this.rowCommunityTabEtcBinding = rowCommunityTabEtcBinding

            this.rowCommunityTabEtcBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabEtcViewHolder {
        val rowCommunityTabEtcBinding = RowCommunityTabEtcBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabEtcViewHolder = CommunityTabEtcViewHolder(rowCommunityTabEtcBinding)

        return communityTabEtcViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CommunityTabEtcViewHolder, position: Int) {
        holder.rowCommunityTabEtcBinding.apply {
            textViewCommunityListLabelEtc.text = "기타"
            textViewCommunityListTitleEtc.text = "글 제목입니다 $position"
            textViewCommunityListContentEtc.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                    "글 내용입니다 글 내용입니다 글 내용입니다 "
            textViewCommunityListLikeCntEtc.text = "999"
            textViewCommunityListCommentCntEtc.text = "999"
            textViewCommunityListDateEtc.text = "2024.05.17"

            linearLayoutCommunityListEtc.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                // 게시글 번호도 주기
                // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
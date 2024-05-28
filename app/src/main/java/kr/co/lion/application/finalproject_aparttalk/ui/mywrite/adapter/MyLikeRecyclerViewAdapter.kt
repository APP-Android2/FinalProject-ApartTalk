package kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMylikeTabLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMywroteTabWroteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyLikeRecyclerViewAdapter (val context: Context) : RecyclerView.Adapter<MyLikeRecyclerViewAdapter.MyLikeViewHolder>() {

    inner class MyLikeViewHolder(rowMyLikeTabLikeBinding: RowMylikeTabLikeBinding) : RecyclerView.ViewHolder(rowMyLikeTabLikeBinding.root) {
        val rowMyLikeTabLikeBinding: RowMylikeTabLikeBinding

        init {
            this.rowMyLikeTabLikeBinding = rowMyLikeTabLikeBinding

            this.rowMyLikeTabLikeBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyLikeRecyclerViewAdapter.MyLikeViewHolder {
        val rowMyLikeTabLikeBinding = RowMylikeTabLikeBinding.inflate(LayoutInflater.from(parent.context))
        val myLikeViewHolder = MyLikeViewHolder(rowMyLikeTabLikeBinding)

        return myLikeViewHolder
    }

    override fun onBindViewHolder(
        holder: MyLikeRecyclerViewAdapter.MyLikeViewHolder,
        position: Int
    ) {
        holder.rowMyLikeTabLikeBinding.apply{
            textViewMyLikeListTitle.text = "글 제목"
            textViewMyLikeListDate.text = "2024.03.01"
            textViewMyLikeListContent.text = "글 내용입니다."
            textViewMyLikeListCommentCnt.text = "999"
            textViewMyLikeListLikeCnt.text = "999"

            rowMyLikeLayout.setOnClickListener {
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

}
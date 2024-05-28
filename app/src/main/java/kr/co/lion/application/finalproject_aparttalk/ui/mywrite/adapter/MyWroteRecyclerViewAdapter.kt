package kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowAnnouncementItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMywroteTabWroteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyWroteRecyclerViewAdapter (val context: Context) : RecyclerView.Adapter<MyWroteRecyclerViewAdapter.MyWroteViewHolder>() {

    inner class MyWroteViewHolder(rowMyWroteTabWroteBinding: RowMywroteTabWroteBinding) : RecyclerView.ViewHolder(rowMyWroteTabWroteBinding.root) {
        val rowMyWroteTabWroteBinding: RowMywroteTabWroteBinding

        init {
            this.rowMyWroteTabWroteBinding = rowMyWroteTabWroteBinding

            this.rowMyWroteTabWroteBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyWroteRecyclerViewAdapter.MyWroteViewHolder {
        val rowMyWroteTabWroteBinding = RowMywroteTabWroteBinding.inflate(LayoutInflater.from(parent.context))
        val myWroteViewHolder = MyWroteViewHolder(rowMyWroteTabWroteBinding)

        return myWroteViewHolder
    }

    override fun onBindViewHolder(
        holder: MyWroteRecyclerViewAdapter.MyWroteViewHolder,
        position: Int
    ) {
        holder.rowMyWroteTabWroteBinding.apply{
            textViewMyWroteListTitle.text = "글 제목"
            textViewMyWroteListDate.text = "2024.03.01"
            textViewMyWroteListCommentCnt.text = "999"
            textViewMyWroteListLikeCnt.text ="999"
            textViewMyWroteListContent.text ="글 내용입니다."

            rowMyWroteLayout.setOnClickListener {
                (context as CommunityActivity).replaceFragment(CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

}


package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowAnnouncementItemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName


class AnnouncementRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<AnnouncementRecyclerViewAdapter.AnnouncementViewHolder>() {

    inner class AnnouncementViewHolder(rowAnnouncementItemBinding: RowAnnouncementItemBinding) : RecyclerView.ViewHolder(rowAnnouncementItemBinding.root) {
        val rowAnnouncementItemBinding: RowAnnouncementItemBinding

        init {
            this.rowAnnouncementItemBinding = rowAnnouncementItemBinding

            this.rowAnnouncementItemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementRecyclerViewAdapter.AnnouncementViewHolder {
        val rowAnnouncementItemBinding = RowAnnouncementItemBinding.inflate(LayoutInflater.from(parent.context))
        val announcementViewHolder = AnnouncementViewHolder(rowAnnouncementItemBinding)

        return announcementViewHolder
    }

    override fun onBindViewHolder(
        holder: AnnouncementRecyclerViewAdapter.AnnouncementViewHolder,
        position: Int
    ) {
        holder.rowAnnouncementItemBinding.apply{
            rowAnnouncementTextViewTitle.text = "[공지]공지 제목"
            rowAnnouncementTextViewDate.text = "2024.03.01"

            rowAnnouncementLayout.setOnClickListener {
                (context as ServiceActivity).replaceFragment(ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}
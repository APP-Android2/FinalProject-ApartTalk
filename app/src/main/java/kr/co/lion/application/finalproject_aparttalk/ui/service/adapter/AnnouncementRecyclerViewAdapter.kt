package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowAnnouncementItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.AnnouncementModel
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.ViewAnnouncementFragment
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName


class AnnouncementRecyclerViewAdapter(
    private val context: Context,
    private val announcementList: List<AnnouncementModel>
) : RecyclerView.Adapter<AnnouncementRecyclerViewAdapter.AnnouncementViewHolder>() {

    inner class AnnouncementViewHolder(val binding: RowAnnouncementItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementViewHolder {
        val binding = RowAnnouncementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnnouncementViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AnnouncementViewHolder,
        position: Int
    ) {
        val announcement = announcementList[position]
        holder.binding.apply {
            rowAnnouncementTextViewTitle.text = "[공지]${announcement.AnnouncementTitle}"
            rowAnnouncementTextViewDate.text = announcement.AnnouncementDate

            rowAnnouncementLayout.setOnClickListener {
                // Create a new instance of ViewAnnouncementFragment with the selected announcement data
                val fragment = ViewAnnouncementFragment.newInstance(announcement)
                (context as ServiceActivity).replaceFragment(fragment, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return announcementList.size
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowInquiryCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import java.text.SimpleDateFormat
import java.util.*

class InquiryCompleteAdapter(
    private var inquiries: List<InquiryModel>,
    private val onItemClick: (InquiryModel) -> Unit
) : RecyclerView.Adapter<InquiryCompleteAdapter.InquiryCompleteViewHolder>() {

    inner class InquiryCompleteViewHolder(val binding: RowInquiryCompleteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiryCompleteViewHolder {
        val binding = RowInquiryCompleteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InquiryCompleteViewHolder(binding)
    }

    override fun getItemCount(): Int = inquiries.size

    override fun onBindViewHolder(holder: InquiryCompleteViewHolder, position: Int) {
        val inquiry = inquiries[position]
        holder.binding.apply {
            inquiryCompleteTextViewLabel.text = if (inquiry.inquiryPrivate) "비공개" else "공개"
            inquiryCompleteTextViewLabel1.text = if (inquiry.inquiryState) "답변완료" else "답변중"
            inquiryCompleteTitle.text = if (inquiry.inquiryPrivate) "작성인의 요청으로 내용이 비공개 입니다" else inquiry.inquiryTitle

            val labelColor = if (inquiry.inquiryPrivate) R.color.sixth else R.color.third
            val labelBackground = if (inquiry.inquiryPrivate) R.drawable.community_label_border else R.drawable.dropdown_box_border
            inquiryCompleteTextViewLabel.setTextColor(ContextCompat.getColor(holder.itemView.context, labelColor))
            inquiryCompleteTextViewLabel.setBackgroundResource(labelBackground)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(inquiry.timestamp)
            inquiryCompleteDate.text = dateFormat.format(date)
            inquiryCompleteTime.text = timeFormat.format(date)

            root.setOnClickListener {
                onItemClick(inquiry)
            }
        }
    }

    fun updateList(newInquiries: List<InquiryModel>) {
        inquiries = newInquiries
        notifyDataSetChanged()
    }
}

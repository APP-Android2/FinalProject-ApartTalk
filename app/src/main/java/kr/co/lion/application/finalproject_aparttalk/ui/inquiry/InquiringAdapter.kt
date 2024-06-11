package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowInquiringBinding
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import java.text.SimpleDateFormat
import java.util.*

class InquiringAdapter(
    private var inquiries: List<InquiryModel>,
    private val currentUserIdx: String, // 현재 유저의 Idx
    private val onItemClick: (InquiryModel) -> Unit
) : RecyclerView.Adapter<InquiringAdapter.InquiringViewHolder>() {

    inner class InquiringViewHolder(val binding: RowInquiringBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiringViewHolder {
        val binding = RowInquiringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InquiringViewHolder(binding)
    }

    override fun getItemCount(): Int = inquiries.size

    override fun onBindViewHolder(holder: InquiringViewHolder, position: Int) {
        val inquiry = inquiries[position]
        holder.binding.apply {
            rowinquiringTextViewLabel.text = if (inquiry.inquiryPrivate) "비공개" else "공개"
            rowinquiringTextViewLabel1.text = if (inquiry.inquiryState) "답변완료" else "답변중"
            rowinquiringTextViewTitle.text = if (inquiry.inquiryPrivate) "작성인의 요청으로 내용이 비공개 입니다" else inquiry.inquiryTitle

            val labelColor = if (inquiry.inquiryPrivate) R.color.sixth else R.color.third
            val labelBackground = if (inquiry.inquiryPrivate) R.drawable.community_label_border else R.drawable.dropdown_box_border
            rowinquiringTextViewLabel.setTextColor(ContextCompat.getColor(holder.itemView.context, labelColor))
            rowinquiringTextViewLabel.setBackgroundResource(labelBackground)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(inquiry.timestamp)
            rowinquiringTextViewDate.text = dateFormat.format(date)
            rowinquiringTextViewTime.text = timeFormat.format(date)

            root.setOnClickListener {
                if (inquiry.inquiryPrivate && inquiry.userIdx != currentUserIdx) {
                    showPrivateDocumentDialog(holder.itemView.context)
                } else {
                    onItemClick(inquiry)
                }
            }
        }
    }

    fun updateList(newInquiries: List<InquiryModel>) {
        inquiries = newInquiries
        notifyDataSetChanged()
    }

    private fun showPrivateDocumentDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("비공개 문서")
            .setMessage("해당 문서는 비공개 문서입니다.")
            .setPositiveButton("확인", null)
            .show()
    }
}

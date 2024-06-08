package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowAnnouncementItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowFaqItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class FAQRecyclerViewAdapter(
    private val context: Context,
    private val faqList: List<ServiceModel>
) : RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQViewHolder>() {

    inner class FAQViewHolder(val binding: RowFaqItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
    ): FAQViewHolder {
        val binding = RowFaqItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FAQViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FAQViewHolder,
        position: Int
    ) {
        val faq = faqList[position]
        holder.binding.apply {
            rowFAQTextViewTitle.text = faq.serviceTitle

            rowFAQLayout.setOnClickListener {
                (context as ServiceActivity).replaceFragment(ServiceFragmentName.VIEW_FAQ_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return faqList.size
    }
}
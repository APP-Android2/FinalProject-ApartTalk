package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowAnnouncementItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowFaqItemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class FAQRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<FAQRecyclerViewAdapter.FAQViewHolder>() {

    inner class FAQViewHolder(rowFAQItemBinding: RowFaqItemBinding) : RecyclerView.ViewHolder(rowFAQItemBinding.root) {
        val rowFAQItemBinding: RowFaqItemBinding

        init {
            this.rowFAQItemBinding = rowFAQItemBinding

            this.rowFAQItemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FAQRecyclerViewAdapter.FAQViewHolder {
        val rowFAQItemBinding = RowFaqItemBinding.inflate(LayoutInflater.from(parent.context))
        val FAQViewHolder = FAQViewHolder(rowFAQItemBinding)

        return FAQViewHolder
    }

    override fun onBindViewHolder(
        holder: FAQRecyclerViewAdapter.FAQViewHolder,
        position: Int
    ) {
        holder.rowFAQItemBinding.apply{
            rowFAQTextViewTitle.text = "질문입니다"


            rowFAQLayout.setOnClickListener {
                (context as ServiceActivity).replaceFragment(ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

}
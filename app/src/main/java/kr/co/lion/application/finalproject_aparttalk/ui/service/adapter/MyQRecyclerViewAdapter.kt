package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowFaqItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMyqItemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyQRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<MyQRecyclerViewAdapter.MyQViewHolder>() {

    inner class MyQViewHolder(rowMyQItemBinding: RowMyqItemBinding) : RecyclerView.ViewHolder(rowMyQItemBinding.root) {
        val rowMyQItemBinding: RowMyqItemBinding

        init {
            this.rowMyQItemBinding = rowMyQItemBinding

            this.rowMyQItemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyQRecyclerViewAdapter.MyQViewHolder {
        val rowMyQItemBinding = RowMyqItemBinding.inflate(LayoutInflater.from(parent.context))
        val MyQViewHolder = MyQViewHolder(rowMyQItemBinding)

        return MyQViewHolder
    }

    override fun onBindViewHolder(
        holder: MyQRecyclerViewAdapter.MyQViewHolder,
        position: Int
    ) {
        holder.rowMyQItemBinding.apply{
            rowMyQTextViewTitle.text = "문의 제목"
            rowMyQTextViewContent.text = "글 내용입니다."
            myQCustomTextView.text = "답변 완료"


            rowMyQLayout.setOnClickListener {
                (context as ServiceActivity).replaceFragment(ServiceFragmentName.VIEW_MY_Q_FRAGMENT, true, true, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

}
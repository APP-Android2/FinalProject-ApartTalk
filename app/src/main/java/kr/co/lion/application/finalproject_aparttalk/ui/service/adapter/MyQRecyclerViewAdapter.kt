package kr.co.lion.application.finalproject_aparttalk.ui.service.adapter

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowFaqItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMyqItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyQRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<MyQRecyclerViewAdapter.MyQViewHolder>() {

    private var serviceList: List<ServiceModel> = emptyList()

    inner class MyQViewHolder(val rowMyQItemBinding: RowMyqItemBinding) : RecyclerView.ViewHolder(rowMyQItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQViewHolder {
        val rowMyQItemBinding = RowMyqItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyQViewHolder(rowMyQItemBinding)
    }

    override fun onBindViewHolder(holder: MyQViewHolder, position: Int) {
        val service = serviceList[position]
        holder.rowMyQItemBinding.apply {
            rowMyQTextViewTitle.text = service.serviceTitle
            rowMyQTextViewContent.text = service.serviceContent
            myQCustomTextView.text = if (service.serviceState) "답변 완료" else "답변 대기 중"

            rowMyQLayout.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("serviceTitle", service.serviceTitle)
                    putString("serviceContent", service.serviceContent)
                    putString("serviceAnsContent", service.serviceAnsContent)
                }
                (context as ServiceActivity).replaceFragment(ServiceFragmentName.VIEW_MY_Q_FRAGMENT, true, true, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    fun submitList(services: List<ServiceModel>) {
        this.serviceList = services
        notifyDataSetChanged()
    }
}
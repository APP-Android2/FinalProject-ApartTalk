package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationInfoShowBinding
import kr.co.lion.application.finalproject_aparttalk.model.OperationInfoModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment

class OperationSecondRecyclerView(
    private val fragmentManager: FragmentManager,
    private var operationInfoList: MutableList<OperationInfoModel>
) : RecyclerView.Adapter<OperationSecondRecyclerView.ViewHolder>(){

    inner class ViewHolder(rowOperationInfoShowBinding: RowOperationInfoShowBinding) : RecyclerView.ViewHolder(rowOperationInfoShowBinding.root) {
        val rowOperationInfoShowBinding : RowOperationInfoShowBinding

        init {
            this.rowOperationInfoShowBinding = rowOperationInfoShowBinding
            this.rowOperationInfoShowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rowOperationInfoShowBinding = RowOperationInfoShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ViewHolder(rowOperationInfoShowBinding)

        return  viewHolder
    }

    override fun getItemCount(): Int {
        return operationInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val operationInfo = operationInfoList[position]

        holder.rowOperationInfoShowBinding.textViewOperationInfoManager.text = operationInfo.OperationInfoWriter
        holder.rowOperationInfoShowBinding.textViewOperationInfoType.text = operationInfo.OperationInfoType
        holder.rowOperationInfoShowBinding.textViewOperationInfoSubject.text = operationInfo.OperationInfoSubject
        holder.rowOperationInfoShowBinding.textViewOperationInfoDate.text = operationInfo.OperationInfoDate

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationInfoShowBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", operationInfo.OperationInfoWriter)
                    putString("type", operationInfo.OperationInfoType)
                    putString("subject", operationInfo.OperationInfoSubject)
                    putString("date", operationInfo.OperationInfoDate)
                    putString("content", operationInfo.OperationInfoContent)
                    putInt("idx", operationInfo.OperationInfoIdx)
                }
            }
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }

    fun updateList(newItems: List<OperationInfoModel>) {
        Log.d("OperationSecondRecyclerView", "Updating list with ${newItems.size} items")
        operationInfoList.clear()
        operationInfoList.addAll(newItems)
        notifyDataSetChanged()
    }
}
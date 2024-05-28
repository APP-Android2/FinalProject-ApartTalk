package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationInfoShowBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.fragment.OperationInfoShowBottomSheetFragment

class OperationSecondRecyclerView(private val fragmentManager: FragmentManager) : RecyclerView.Adapter<OperationSecondRecyclerView.ViewHolder>(){

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
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rowOperationInfoShowBinding.textViewOperationInfoManager.text = "관리자 ${position}"
        holder.rowOperationInfoShowBinding.textViewOperationInfoType.text = "계약서정보 ${position}"
        holder.rowOperationInfoShowBinding.textViewOperationInfoSubject.text = "힐스테이트 계약서 정보 (2024.05.19 계정)"
        holder.rowOperationInfoShowBinding.textViewOperationInfoDate.text = "2024-05-25"

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationInfoShowBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment()
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationSafetyManagementBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.SafetyManagementViewModel

class SafetyManagementRecyclerView(val context: Context, var viewModel: SafetyManagementViewModel) : RecyclerView.Adapter<SafetyManagementRecyclerView.SafetyManagementViewHolder>() {
    inner class SafetyManagementViewHolder(rowOperationSafetyManagementBinding: RowOperationSafetyManagementBinding) : RecyclerView.ViewHolder(rowOperationSafetyManagementBinding.root) {
        val rowOperationSafetyManagementBinding : RowOperationSafetyManagementBinding

        init {
            this.rowOperationSafetyManagementBinding = rowOperationSafetyManagementBinding

            this.rowOperationSafetyManagementBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SafetyManagementViewHolder {
        val rowOperationSafetyManagementBinding = RowOperationSafetyManagementBinding.inflate(
            LayoutInflater.from(parent.context))
        val safetyManagementViewHolder = SafetyManagementViewHolder(rowOperationSafetyManagementBinding)

        return safetyManagementViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.safetyManagementList.size
    }

    override fun onBindViewHolder(holder: SafetyManagementViewHolder, position: Int) {
        holder.rowOperationSafetyManagementBinding.apply {
            textViewOperationSafetyManagementManager.text = viewModel.safetyManagementList[position].OperationInfoWriter
            textViewOperationSafetyManagementType.text = viewModel.safetyManagementList[position].OperationInfoType
            textViewOperationSafetyManagementSubject.text = viewModel.safetyManagementList[position].OperationInfoSubject
            textViewOperationSafetyManagementDate.text = viewModel.safetyManagementList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationSafetyManagementBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.safetyManagementList[position].OperationInfoWriter)
                    putString("type", viewModel.safetyManagementList[position].OperationInfoType)
                    putString("subject", viewModel.safetyManagementList[position].OperationInfoSubject)
                    putString("date", viewModel.safetyManagementList[position].OperationInfoDate)
                    putString("content", viewModel.safetyManagementList[position].OperationInfoContent)
                    putInt("idx", viewModel.safetyManagementList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationManagementRegulationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.ManagementRegulationViewModel

class ManagementRegulationRecyclerView(val context: Context, var viewModel: ManagementRegulationViewModel) : RecyclerView.Adapter<ManagementRegulationRecyclerView.ManagementRegulationViewHolder>() {
    inner class ManagementRegulationViewHolder(rowOperationManagementRegulationBinding: RowOperationManagementRegulationBinding) : RecyclerView.ViewHolder(rowOperationManagementRegulationBinding.root) {
        val rowOperationManagementRegulationBinding : RowOperationManagementRegulationBinding

        init {
            this.rowOperationManagementRegulationBinding = rowOperationManagementRegulationBinding

            this.rowOperationManagementRegulationBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagementRegulationViewHolder {
        val rowOperationManagementRegulationBinding = RowOperationManagementRegulationBinding.inflate(
            LayoutInflater.from(parent.context))
        val managementRegulationViewHolder = ManagementRegulationViewHolder(rowOperationManagementRegulationBinding)

        return managementRegulationViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.managementRegulationList.size
    }

    override fun onBindViewHolder(holder: ManagementRegulationViewHolder, position: Int) {
        holder.rowOperationManagementRegulationBinding.apply {
            textViewManagementRegulationManager.text = viewModel.managementRegulationList[position].OperationInfoWriter
            textViewManagementRegulationType.text = viewModel.managementRegulationList[position].OperationInfoType
            textViewManagementRegulationSubject.text = viewModel.managementRegulationList[position].OperationInfoSubject
            textViewManagementRegulationDate.text = viewModel.managementRegulationList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationManagementRegulationBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.managementRegulationList[position].OperationInfoWriter)
                    putString("type", viewModel.managementRegulationList[position].OperationInfoType)
                    putString("subject", viewModel.managementRegulationList[position].OperationInfoSubject)
                    putString("date", viewModel.managementRegulationList[position].OperationInfoDate)
                    putString("content", viewModel.managementRegulationList[position].OperationInfoContent)
                    putInt("idx", viewModel.managementRegulationList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
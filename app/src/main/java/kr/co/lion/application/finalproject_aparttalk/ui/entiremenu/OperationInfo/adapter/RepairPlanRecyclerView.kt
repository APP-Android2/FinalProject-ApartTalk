package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationRepairPlanBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.RepairPlanViewModel

class RepairPlanRecyclerView(val context: Context, var viewModel: RepairPlanViewModel) : RecyclerView.Adapter<RepairPlanRecyclerView.RepairPlanViewHolder>() {
    inner class RepairPlanViewHolder(rowOperationRepairPlanBinding: RowOperationRepairPlanBinding) : RecyclerView.ViewHolder(rowOperationRepairPlanBinding.root) {
        val rowOperationRepairPlanBinding : RowOperationRepairPlanBinding

        init {
            this.rowOperationRepairPlanBinding = rowOperationRepairPlanBinding

            this.rowOperationRepairPlanBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepairPlanViewHolder {
        val rowOperationRepairPlanBinding = RowOperationRepairPlanBinding.inflate(
            LayoutInflater.from(parent.context))
        val repairPlanViewHolder = RepairPlanViewHolder(rowOperationRepairPlanBinding)

        return repairPlanViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.repairPlanList.size
    }

    override fun onBindViewHolder(holder: RepairPlanViewHolder, position: Int) {
        holder.rowOperationRepairPlanBinding.apply {
            textViewOperationRepairPlanManager.text = viewModel.repairPlanList[position].OperationInfoWriter
            textViewOperationRepairPlanType.text = viewModel.repairPlanList[position].OperationInfoType
            textViewOperationRepairPlanSubject.text = viewModel.repairPlanList[position].OperationInfoSubject
            textViewOperationRepairPlanDate.text = viewModel.repairPlanList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationRepairPlanBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.repairPlanList[position].OperationInfoWriter)
                    putString("type", viewModel.repairPlanList[position].OperationInfoType)
                    putString("subject", viewModel.repairPlanList[position].OperationInfoSubject)
                    putString("date", viewModel.repairPlanList[position].OperationInfoDate)
                    putString("content", viewModel.repairPlanList[position].OperationInfoContent)
                    putInt("idx", viewModel.repairPlanList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
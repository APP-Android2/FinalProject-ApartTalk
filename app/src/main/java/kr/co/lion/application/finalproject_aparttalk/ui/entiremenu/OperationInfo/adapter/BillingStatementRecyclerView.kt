package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationBillingStatementBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.BillingStatementViewModel

class BillingStatementRecyclerView(val context: Context, var viewModel: BillingStatementViewModel)  : RecyclerView.Adapter<BillingStatementRecyclerView.BillingStatementViewHolder>() {
    inner class BillingStatementViewHolder(rowOperationBillingStatementBinding: RowOperationBillingStatementBinding) : RecyclerView.ViewHolder(rowOperationBillingStatementBinding.root){
        val rowOperationBillingStatementBinding : RowOperationBillingStatementBinding

        init {
            this.rowOperationBillingStatementBinding = rowOperationBillingStatementBinding

            this.rowOperationBillingStatementBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingStatementViewHolder {
        val rowOperationBillingStatementBinding = RowOperationBillingStatementBinding.inflate(LayoutInflater.from(parent.context))
        val billingStatementViewHolder = BillingStatementViewHolder(rowOperationBillingStatementBinding)

        return billingStatementViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.billingStatementList.size
    }

    override fun onBindViewHolder(holder: BillingStatementViewHolder, position: Int) {
        holder.rowOperationBillingStatementBinding.apply {
            textViewBillingStatementManager.text = viewModel.billingStatementList[position].OperationInfoWriter
            textViewBillingStatementType.text = viewModel.billingStatementList[position].OperationInfoType
            textViewBillingStatementSubject.text = viewModel.billingStatementList[position].OperationInfoSubject
            textViewBillingStatementDate.text = viewModel.billingStatementList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationBillingStatementBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.billingStatementList[position].OperationInfoWriter)
                    putString("type", viewModel.billingStatementList[position].OperationInfoType)
                    putString("subject", viewModel.billingStatementList[position].OperationInfoSubject)
                    putString("date", viewModel.billingStatementList[position].OperationInfoDate)
                    putString("content", viewModel.billingStatementList[position].OperationInfoContent)
                    putInt("idx", viewModel.billingStatementList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
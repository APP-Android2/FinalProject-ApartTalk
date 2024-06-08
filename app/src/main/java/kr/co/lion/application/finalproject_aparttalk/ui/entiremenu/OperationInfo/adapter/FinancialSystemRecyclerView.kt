package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationFinancialSystemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.FinancialSystemViewModel

class FinancialSystemRecyclerView(val context: Context, var viewModel: FinancialSystemViewModel) : RecyclerView.Adapter<FinancialSystemRecyclerView.FinancialSystemViewHolder>() {

    inner class FinancialSystemViewHolder(rowOperationFinancialSystemBinding: RowOperationFinancialSystemBinding) : RecyclerView.ViewHolder(rowOperationFinancialSystemBinding.root) {
        val rowOperationFinancialSystemBinding : RowOperationFinancialSystemBinding

        init {
            this.rowOperationFinancialSystemBinding = rowOperationFinancialSystemBinding

            this.rowOperationFinancialSystemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinancialSystemViewHolder {
        val rowOperationFinancialSystemBinding = RowOperationFinancialSystemBinding.inflate(LayoutInflater.from(parent.context))
        val financialSystemViewHolder = FinancialSystemViewHolder(rowOperationFinancialSystemBinding)

        return financialSystemViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.financialSystemList.size
    }

    override fun onBindViewHolder(holder: FinancialSystemViewHolder, position: Int) {
        holder.rowOperationFinancialSystemBinding.apply {
            textViewFinancialSystemManager.text = viewModel.financialSystemList[position].OperationInfoWriter
            textViewFinancialSystemType.text = viewModel.financialSystemList[position].OperationInfoType
            textViewFinancialSystemSubject.text = viewModel.financialSystemList[position].OperationInfoSubject
            textViewFinancialSystemDate.text = viewModel.financialSystemList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationFinancialSystemBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.financialSystemList[position].OperationInfoWriter)
                    putString("type", viewModel.financialSystemList[position].OperationInfoType)
                    putString("subject", viewModel.financialSystemList[position].OperationInfoSubject)
                    putString("date", viewModel.financialSystemList[position].OperationInfoDate)
                    putString("content", viewModel.financialSystemList[position].OperationInfoContent)
                    putInt("idx", viewModel.financialSystemList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
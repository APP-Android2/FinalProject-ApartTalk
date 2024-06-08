package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationContractInfoBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.ContractInfoViewModel

class ContractInfoRecyclerView(val context: Context, var viewModel: ContractInfoViewModel) : RecyclerView.Adapter<ContractInfoRecyclerView.ContractInfoViewHolder>() {
    inner class ContractInfoViewHolder(rowOperationContractInfoBinding: RowOperationContractInfoBinding) : RecyclerView.ViewHolder(rowOperationContractInfoBinding.root) {
        val rowOperationContractInfoBinding : RowOperationContractInfoBinding

        init {
            this.rowOperationContractInfoBinding = rowOperationContractInfoBinding

            this.rowOperationContractInfoBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractInfoViewHolder {
        val rowOperationContractInfoBinding = RowOperationContractInfoBinding.inflate(
            LayoutInflater.from(parent.context))
        val contractInfoViewHolder = ContractInfoViewHolder(rowOperationContractInfoBinding)

        return contractInfoViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.contractInfoList.size
    }

    override fun onBindViewHolder(holder: ContractInfoViewHolder, position: Int) {
        holder.rowOperationContractInfoBinding.apply {
            textViewContractInfoManager.text = viewModel.contractInfoList[position].OperationInfoWriter
            textViewContractInfoType.text = viewModel.contractInfoList[position].OperationInfoType
            textViewContractInfoSubject.text = viewModel.contractInfoList[position].OperationInfoSubject
            textViewContractInfoDate.text = viewModel.contractInfoList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationContractInfoBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.contractInfoList[position].OperationInfoWriter)
                    putString("type", viewModel.contractInfoList[position].OperationInfoType)
                    putString("subject", viewModel.contractInfoList[position].OperationInfoSubject)
                    putString("date", viewModel.contractInfoList[position].OperationInfoDate)
                    putString("content", viewModel.contractInfoList[position].OperationInfoContent)
                    putInt("idx", viewModel.contractInfoList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowOperationBiddingNoticeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.OperationInfoShowBottomSheetFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.BiddingNoticeViewModel

class BiddingNoticeRecyclerView(val context: Context, var viewModel: BiddingNoticeViewModel) : RecyclerView.Adapter<BiddingNoticeRecyclerView.BiddingNoticeViewHolder>() {
    inner class BiddingNoticeViewHolder(rowOperationBiddingNoticeBinding: RowOperationBiddingNoticeBinding) : RecyclerView.ViewHolder(rowOperationBiddingNoticeBinding.root) {
        val rowOperationBiddingNoticeBinding : RowOperationBiddingNoticeBinding

        init {
            this.rowOperationBiddingNoticeBinding = rowOperationBiddingNoticeBinding

            this.rowOperationBiddingNoticeBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiddingNoticeViewHolder {
        val rowOperationBiddingNoticeBinding = RowOperationBiddingNoticeBinding.inflate(LayoutInflater.from(parent.context))
        val biddingNoticeViewHolder = BiddingNoticeViewHolder(rowOperationBiddingNoticeBinding)

        return biddingNoticeViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.biddingNoticeList.size
    }

    override fun onBindViewHolder(holder: BiddingNoticeViewHolder, position: Int) {
        holder.rowOperationBiddingNoticeBinding.apply {
            textViewBiddingNoticeManager.text = viewModel.biddingNoticeList[position].OperationInfoWriter
            textViewBiddingNoticeType.text = viewModel.biddingNoticeList[position].OperationInfoType
            textViewBiddingNoticeSubject.text = viewModel.biddingNoticeList[position].OperationInfoSubject
            textViewBiddingNoticeDate.text = viewModel.biddingNoticeList[position].OperationInfoDate
        }

        // 항목을 누르면 동작하는 리스너
        holder.rowOperationBiddingNoticeBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = OperationInfoShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("writer", viewModel.biddingNoticeList[position].OperationInfoWriter)
                    putString("type", viewModel.biddingNoticeList[position].OperationInfoType)
                    putString("subject", viewModel.biddingNoticeList[position].OperationInfoSubject)
                    putString("date", viewModel.biddingNoticeList[position].OperationInfoDate)
                    putString("content", viewModel.biddingNoticeList[position].OperationInfoContent)
                    putInt("idx", viewModel.biddingNoticeList[position].OperationInfoIdx)
                }
            }
            (context as? FragmentActivity)?.supportFragmentManager?.let { fragmentManager ->
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }
}
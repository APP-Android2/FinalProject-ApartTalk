package kr.co.lion.application.finalproject_aparttalk.ui.broadcast.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowBroadcastBinding
import kr.co.lion.application.finalproject_aparttalk.model.BroadcastModel
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class BroadcastRecyclerViewAdapter(val context: Context, val broadcastActivity: BroadcastActivity) : RecyclerView.Adapter<BroadcastRecyclerViewAdapter.BroadcastViewHolder>() {

    private val broadcastList: List<BroadcastModel> = listOf(
        BroadcastModel(
            broadcastTitle = "안내방송의 제목입니다.",
            broadcastDate = "2024.05.17",
            broadcastContent = "안내방송의 내용입니다.",
            broadcastIdx = 1
        )
    )

    inner class BroadcastViewHolder(rowBroadcastBinding: RowBroadcastBinding) : RecyclerView.ViewHolder(rowBroadcastBinding.root) {
        val rowBroadcastBinding: RowBroadcastBinding

        init {
            this.rowBroadcastBinding = rowBroadcastBinding

            this.rowBroadcastBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BroadcastViewHolder {
        val rowBroadcastBinding = RowBroadcastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BroadcastViewHolder(rowBroadcastBinding)
    }

    override fun getItemCount(): Int {
        return broadcastList.size
    }

    override fun onBindViewHolder(holder: BroadcastViewHolder, position: Int) {
        val broadcast = broadcastList[position]
        holder.rowBroadcastBinding.apply {
            textViewRowBroadcastSubject.text = broadcast.broadcastTitle
            textViewRowBroadcastDate.text = broadcast.broadcastDate

            rowBroadcastLayout.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("broadcastTitle", broadcast.broadcastTitle)
                    putString("broadcastContent", broadcast.broadcastContent)
                }
                broadcastActivity.replaceFragment(BroadcastFragmentName.BROADCAST_DETAIL_FRAGMENT, true, true, bundle)
            }
        }
    }
}
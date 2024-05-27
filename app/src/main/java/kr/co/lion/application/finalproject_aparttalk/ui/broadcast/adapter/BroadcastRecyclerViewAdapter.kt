package kr.co.lion.application.finalproject_aparttalk.ui.broadcast.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowBroadcastBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class BroadcastRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<BroadcastRecyclerViewAdapter.BroadcastViewHolder>() {
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
        val rowBroadcastBinding = RowBroadcastBinding.inflate(LayoutInflater.from(parent.context))
        val broadcastViewHolder = BroadcastViewHolder(rowBroadcastBinding)

        return broadcastViewHolder
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: BroadcastViewHolder, position: Int) {
        holder.rowBroadcastBinding.apply {
            textViewRowBroadcastSubject.text = "안내 방송 제목입니다 $position"
            textViewRowBroadcastDate.text = "2024.05.17"

            rowBroadcastLayout.setOnClickListener {
                (context as BroadcastActivity).replaceFragment(BroadcastFragmentName.BROADCAST_DETIAL_FRAGMENT, true, true, null)
            }
        }
    }
}
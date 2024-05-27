package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailImageBinding

class CommunityDetailImageViewPager2Adapter(val context : Context) : RecyclerView.Adapter<CommunityDetailImageViewPager2Adapter.CommunityDetailImageViewHolder>(){
    inner class CommunityDetailImageViewHolder(rowCommunityDetailImageBinding: RowCommunityDetailImageBinding) : RecyclerView.ViewHolder(rowCommunityDetailImageBinding.root) {
        val rowCommunityDetailImageBinding: RowCommunityDetailImageBinding

        init {
            this.rowCommunityDetailImageBinding = rowCommunityDetailImageBinding

            this.rowCommunityDetailImageBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityDetailImageViewHolder {
        val rowCommunityDetailImageBinding = RowCommunityDetailImageBinding.inflate(LayoutInflater.from(parent.context))
        val communityDetailImageViewHolder = CommunityDetailImageViewHolder(rowCommunityDetailImageBinding)

        return communityDetailImageViewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: CommunityDetailImageViewHolder, position: Int) {
        holder.rowCommunityDetailImageBinding.imageViewRowCommunityDetail.setImageResource(R.drawable.ic_launcher_foreground)
    }
}
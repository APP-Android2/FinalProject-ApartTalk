package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityModifyImageBinding

class CommunityModifyImageViewPager2Adapter(val context : Context) : RecyclerView.Adapter<CommunityModifyImageViewPager2Adapter.CommunityModifyImageViewHolder>(){
    inner class CommunityModifyImageViewHolder(rowCommunityModifyImageBinding: RowCommunityModifyImageBinding) : RecyclerView.ViewHolder(rowCommunityModifyImageBinding.root) {
        val rowCommunityModifyImageBinding: RowCommunityModifyImageBinding

        init {
            this.rowCommunityModifyImageBinding = rowCommunityModifyImageBinding

            this.rowCommunityModifyImageBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityModifyImageViewHolder {
        val rowCommunityModifyImageBinding = RowCommunityModifyImageBinding.inflate(LayoutInflater.from(parent.context))
        val communityModifyImageViewHolder = CommunityModifyImageViewHolder(rowCommunityModifyImageBinding)

        return communityModifyImageViewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: CommunityModifyImageViewHolder, position: Int) {
        holder.rowCommunityModifyImageBinding.imageViewRowCommunityModify.setImageResource(R.drawable.ic_launcher_foreground)
    }
}
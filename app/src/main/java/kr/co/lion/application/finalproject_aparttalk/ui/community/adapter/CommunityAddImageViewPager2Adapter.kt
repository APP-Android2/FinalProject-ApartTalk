package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityAddImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment

class CommunityAddImageViewPager2Adapter(val context : Context) : RecyclerView.Adapter<CommunityAddImageViewPager2Adapter.CommunityAddImageViewHolder>(){
    inner class CommunityAddImageViewHolder(rowCommunityAddImageBinding: RowCommunityAddImageBinding) : RecyclerView.ViewHolder(rowCommunityAddImageBinding.root) {
        val rowCommunityAddImageBinding: RowCommunityAddImageBinding

        init {
            this.rowCommunityAddImageBinding = rowCommunityAddImageBinding

            this.rowCommunityAddImageBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityAddImageViewHolder {
        val rowCommunityAddImageBinding = RowCommunityAddImageBinding.inflate(LayoutInflater.from(parent.context))
        val communityAddImageViewHolder = CommunityAddImageViewHolder(rowCommunityAddImageBinding)

        return communityAddImageViewHolder
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: CommunityAddImageViewPager2Adapter.CommunityAddImageViewHolder, position: Int) {
        holder.rowCommunityAddImageBinding.imageViewRowCommunityAdd.setImageResource(R.drawable.ic_launcher_foreground)
    }
}
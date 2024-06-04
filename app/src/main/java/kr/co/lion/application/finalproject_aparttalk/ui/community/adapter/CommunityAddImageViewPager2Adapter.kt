package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityAddImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment

class CommunityAddImageViewPager2Adapter(val context : Context, var imageCommunityAddBitmapList: MutableList<Bitmap>, var fragment: CommunityAddFragment) : RecyclerView.Adapter<CommunityAddImageViewPager2Adapter.CommunityAddImageViewHolder>(){
    
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
        return imageCommunityAddBitmapList.size
    }

    override fun onBindViewHolder(holder: CommunityAddImageViewPager2Adapter.CommunityAddImageViewHolder, position: Int) {
        holder.rowCommunityAddImageBinding.imageViewRowCommunityAdd.setImageBitmap(imageCommunityAddBitmapList[position])
        holder.rowCommunityAddImageBinding.imageButtonCommunityAddDelete.setOnClickListener {
            if (position != imageCommunityAddBitmapList.size-1) {
                imageCommunityAddBitmapList.removeAt(position)
                fragment.updateViewPager2CommunityAddImage()
            } else {
                imageCommunityAddBitmapList[position] = fragment.getBitmapFromDrawable()
                fragment.updateViewPager2CommunityAddImage()
            }
        }
        // 마지막 아이템인 경우
        holder.rowCommunityAddImageBinding.root.setOnClickListener {
            if (position == imageCommunityAddBitmapList.size-1) {
                fragment.startAlbumLauncher()
            }
        }
    }

}
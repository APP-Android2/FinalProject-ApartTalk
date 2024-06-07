package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityDetailViewModel

class CommunityDetailImageViewPager2Adapter(val context : Context, var fragment: CommunityDetailFragment, var viewModel: CommunityDetailViewModel) : RecyclerView.Adapter<CommunityDetailImageViewPager2Adapter.CommunityDetailImageViewHolder>(){
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
        return fragment.imageCommunityDetailList.size
    }

    override fun onBindViewHolder(holder: CommunityDetailImageViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
          viewModel.gettingCommunityPostImage(context, fragment.imageCommunityDetailList[position], holder.rowCommunityDetailImageBinding.imageViewRowCommunityDetail)
        }
    }
}
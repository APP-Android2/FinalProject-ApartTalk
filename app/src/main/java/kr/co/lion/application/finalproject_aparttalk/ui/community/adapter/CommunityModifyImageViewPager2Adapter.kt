package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityModifyImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityModifyFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityModifyViewModel

class CommunityModifyImageViewPager2Adapter(val context : Context, var fragment: CommunityModifyFragment, var viewModel: CommunityModifyViewModel) : RecyclerView.Adapter<CommunityModifyImageViewPager2Adapter.CommunityModifyImageViewHolder>(){
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
        return fragment.imageCommunityModifyList.size
    }

    override fun onBindViewHolder(holder: CommunityModifyImageViewHolder, position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunityPostImage(context, fragment.imageCommunityModifyList[position], holder.rowCommunityModifyImageBinding.imageViewRowCommunityModify)
        }
    }
}
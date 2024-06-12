package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.TabEtcFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityEtcViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabEtcRecyclerViewAdapter(val context: Context, var viewModel: CommunityEtcViewModel) : RecyclerView.Adapter<CommunityTabEtcRecyclerViewAdapter.CommunityTabEtcViewHolder>() {
    inner class CommunityTabEtcViewHolder(rowCommunityTabEtcBinding: RowCommunityTabEtcBinding) : RecyclerView.ViewHolder(rowCommunityTabEtcBinding.root) {
        val rowCommunityTabEtcBinding : RowCommunityTabEtcBinding

        init {
            this.rowCommunityTabEtcBinding = rowCommunityTabEtcBinding

            this.rowCommunityTabEtcBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabEtcViewHolder {
        val rowCommunityTabEtcBinding = RowCommunityTabEtcBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabEtcViewHolder = CommunityTabEtcViewHolder(rowCommunityTabEtcBinding)

        return communityTabEtcViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.etcList.size
    }

    override fun onBindViewHolder(holder: CommunityTabEtcViewHolder, position: Int) {
        holder.rowCommunityTabEtcBinding.apply {
            textViewCommunityListLabelEtc.text = viewModel.etcList[position].postType
            textViewCommunityListTitleEtc.text = viewModel.etcList[position].postTitle
            textViewCommunityListLikeCntEtc.text = viewModel.etcList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntEtc.text = viewModel.etcList[position].postCommentCnt.toString()
            textViewCommunityListDateEtc.text = viewModel.etcList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.etcList[position].postImages != null) {
                    viewModel.gettingCommunityPostImage(context, viewModel.etcList[position].postImages!![0], imageViewCommunityListEtc)
                } else {
                    imageViewCommunityListEtc.setImageResource(R.color.white)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.etcList[position].postApartId, viewModel.etcList[position].postId)
                textViewCommunityListCommentCntEtc.text = viewModel.commentList.size.toString()
            }

            linearLayoutCommunityListEtc.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", viewModel.etcList[position].postIdx)
                intent.putExtra("postId", viewModel.etcList[position].postId)
                intent.putExtra("postApartId", viewModel.etcList[position].postApartId)
                context.startActivity(intent)
            }
        }
    }
}
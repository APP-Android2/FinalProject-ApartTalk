package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityQuestionViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabQuestionRecyclerViewAdapter(val context: Context, var viewModel: CommunityQuestionViewModel) : RecyclerView.Adapter<CommunityTabQuestionRecyclerViewAdapter.CommunityTabQuestionViewHolder>() {
    inner class CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding: RowCommunityTabQuestionBinding) : RecyclerView.ViewHolder(rowCommunityTabQuestionBinding.root) {
        val rowCommunityTabQuestionBinding : RowCommunityTabQuestionBinding

        init {
            this.rowCommunityTabQuestionBinding = rowCommunityTabQuestionBinding

            this.rowCommunityTabQuestionBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabQuestionViewHolder {
        val rowCommunityTabQuestionBinding = RowCommunityTabQuestionBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabQuestionViewHolder = CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding)

        return communityTabQuestionViewHolder
    }

    override fun getItemCount(): Int {
        return viewModel.questionList.size
    }

    override fun onBindViewHolder(holder: CommunityTabQuestionViewHolder, position: Int) {
        holder.rowCommunityTabQuestionBinding.apply {
            textViewCommunityListLabelQuestion.text = viewModel.questionList[position].postType
            textViewCommunityListTitleQuestion.text = viewModel.questionList[position].postTitle
            textViewCommunityListLikeCntQuestion.text = viewModel.questionList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntQuestion.text = viewModel.questionList[position].postCommentCnt.toString()
            textViewCommunityListDateQuestion.text = viewModel.questionList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (viewModel.questionList[position].postImages != null) {
                    viewModel.gettingCommunityPostImage(context, viewModel.questionList[position].postImages!![0], imageViewCommunityListQuestion)
                } else {
                    imageViewCommunityListQuestion.setImageResource(R.color.white)
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.commentList = viewModel.gettingCommunityCommentList(viewModel.questionList[position].postApartId, viewModel.questionList[position].postId)
                textViewCommunityListCommentCntQuestion.text = viewModel.commentList.size.toString()
            }

            linearLayoutCommunityListQuestion.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", viewModel.questionList[position].postIdx)
                intent.putExtra("postId", viewModel.questionList[position].postId)
                intent.putExtra("postApartId", viewModel.questionList[position].postApartId)
                context.startActivity(intent)
            }
        }
    }
}
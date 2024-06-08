package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailCommentBinding
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityDetailViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommentState
import kr.co.lion.application.finalproject_aparttalk.util.Tools

class CommunityDetailCommentRecyclerViewAdapter(val context : Context, var commentList: MutableList<CommentData>, var userList: List<UserModel?>, var fragment: CommunityDetailFragment, var viewModel: CommunityDetailViewModel, var postApartId: String) : RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.CommunityDetailViewHolder>(){
    inner class CommunityDetailViewHolder(rowCommunityDetailCommentBinding: RowCommunityDetailCommentBinding) : RecyclerView.ViewHolder(rowCommunityDetailCommentBinding.root) {
        val rowCommunityDetailCommentBinding: RowCommunityDetailCommentBinding

        init {
            this.rowCommunityDetailCommentBinding = rowCommunityDetailCommentBinding

            this.rowCommunityDetailCommentBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityDetailViewHolder {
        val rowCommunityDetailCommentBinding = RowCommunityDetailCommentBinding.inflate(LayoutInflater.from(parent.context))
        val communityDetailViewHolder = CommunityDetailViewHolder(rowCommunityDetailCommentBinding)

        return communityDetailViewHolder
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommunityDetailViewHolder, position: Int) {
        holder.rowCommunityDetailCommentBinding.apply {

            userList.forEach {
                if (it!!.uid == commentList[position].commentUserId) {
                    textViewRowCommunityDetailCommentWriter.text = it.name
                }
            }
            textViewRowCommunityDetailCommentContent.text = commentList[position].commentContent

            CoroutineScope(Dispatchers.Main).launch {
                val user = gettingLoginUserData()

                if (user!!.uid == commentList[position].commentUserId) {
                    // 수정 기능
                    imageViewRowCommunityDetailCommentModify.setOnClickListener {
                        fragment.fragmentCommunityDetailBinding.textInputCommunityDetailSendComment.setText(commentList[position].commentContent)
                        Tools.showSoftInput(context, fragment.fragmentCommunityDetailBinding.textInputLayoutCommunityDetailSendComment)
                        fragment.fragmentCommunityDetailBinding.imageViewCommunityDetailSendComment.setOnClickListener {
                            CoroutineScope(Dispatchers.Main).launch {
                                fragment.commentModifyProcess(position, commentList[position])
                                viewModel.updateCommunityCommentState(postApartId, commentList[position], CommentState.COMMENT_STATE_MODIFY)
                            }
                        }
                    }

                    // 삭제 기능
                    imageViewRowCommunityDetailCommentDelete.setOnClickListener {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.updateCommunityCommentState(postApartId, commentList[position], CommentState.COMMENT_STATE_REMOVE)
                            fragment.removingCommentData(position)
                        }
                    }
                }
            }
        }
    }

    // 로그인한 사용자 정보 가져오기
    suspend fun gettingLoginUserData(): UserModel? {
        var user: UserModel? = null
        val authUser = App.authRepository.getCurrentUser()
        if (authUser != null) {
            user = App.userRepository.getUser(authUser.uid)
        }
        return  user
    }
}
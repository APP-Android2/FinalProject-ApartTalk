package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityDetailBinding
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.LikeData
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.util.SwipeHelperCallback
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityDetailCommentRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityDetailImageViewPager2Adapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityAddViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityDetailViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunitySearchViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommentState
import kr.co.lion.application.finalproject_aparttalk.util.Tools
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class CommunityDetailFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityDetailBinding: FragmentCommunityDetailBinding
    lateinit var communityActivity: CommunityActivity
    private val viewModel: CommunityDetailViewModel by viewModels()

    // 이미지 저장용 리스트
    var imageCommunityDetailList = mutableListOf<String>()
    var isLiked: Boolean = false
    var likeList = mutableListOf<LikeData>()

    // 현재 글 번호를 담을 변수
    var postIdx: Int? = null
    // 현재 글 번호를 담을 변수
    var postId: String? = null
    // 현재 글이 담긴 아파트 아이디
    var postApartId: String? = null

    // 댓글 모델
    var commentData:CommentData? = null
    // 댓글 정보를 가지고 있는 리스트
    var commentList = mutableListOf<CommentData>()
    // 댓글 유저 리스트
    var userList = listOf<UserModel?>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityDetailBinding = FragmentCommunityDetailBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        // 글 번호를 받는다.
        postIdx = arguments?.getInt("postIdx", 0)
        postId = arguments?.getString("postId")
        postApartId = arguments?.getString("postApartId")

        settingToolbar()
        settingData()
        settingLike()
        settingCommentInputForm()
        commentDoneProcess()
        settingRecyclerViewCommunityDetailComment()

        return fragmentCommunityDetailBinding.root
    }

    // 툴바
    private fun settingToolbar() {
        fragmentCommunityDetailBinding.apply {
            toolbarCommunityDetail.apply {
                textViewCommunityDetailToolbarTitle.text = " "
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    communityActivity.finish()
                }

                inflateMenu(R.menu.menu_community_detail)
                menu.findItem(R.id.menuItemCommunityKebab).isVisible = false

                CoroutineScope(Dispatchers.Main).launch {
                    // 현재 글 번호에 해당하는 글 데이터를 가져온다.
                    val postData = viewModel.selectCommunityPostData(postApartId!!, postId!!)
                    // 사용자 정보를 가져온다.
                    val user = gettingUserData()

                    if (postData!!.postUserId == user.uid) {
                        menu.findItem(R.id.menuItemCommunityKebab).isVisible = true
                    }

                    setOnMenuItemClickListener {
                        when(it.itemId) {
                            // 더보기
                            R.id.menuItemCommunityKebab -> {
                                settingCommunityBottom()
                            }
                        }
                        true
                    }
                }

            }
        }
    }

    // 커뮤니티 바텀시트 띄우기
    private fun settingCommunityBottom() {
        val communityBottomSheetFragment = CommunityBottomSheetFragment(this, viewModel, postIdx!!, postId!!, postApartId!!)
        communityBottomSheetFragment.show(communityActivity.supportFragmentManager, "CommunityBottomSheetFragment")
    }

    // 커뮤니티 글 상세조회 뷰페이저 설정
    private fun settingViewPager2CommunityDetailImage() {
        fragmentCommunityDetailBinding.apply {
            viewPager2CommunityDetailImage.apply {
                adapter = CommunityDetailImageViewPager2Adapter(requireContext(), this@CommunityDetailFragment, viewModel)
            }

            circleIndicatorCommunityDetail.setViewPager(viewPager2CommunityDetailImage)
            viewPager2CommunityDetailImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityDetail.adapterDataObserver
            )
        }
    }

    // 사용자 정보 가져오기
    suspend fun gettingUserData(): UserModel {
        val authUser = App.authRepository.getCurrentUser()
        var user: UserModel? = null
        if (authUser != null) {
            user = App.userRepository.getUser(authUser.uid)
        }
        return user!!
    }

    // 좋아요 누를 시
    private fun settingLike() {
        fragmentCommunityDetailBinding.apply {
            CoroutineScope(Dispatchers.Main).launch {
                val likeData = generatingLikeObject()
                imageViewCommunityDetailLike.setOnClickListener {
                    if (isLiked == false) {
                        isLiked = true
                        imageViewCommunityDetailLike.setImageResource(R.drawable.icon_thumb_liked)
                        CoroutineScope(Dispatchers.Main).launch {
                            val likeSequence = viewModel.getLikeSequence()
                            viewModel.updateLikeSequence(likeSequence)
                            viewModel.insertLikeData(postApartId!!, likeData)
                            likeList = viewModel.gettingLikeList(postApartId!!, postId!!)
                            textViewCommunityDetailLikeCnt.text = likeList.size.toString()
                        }
                    } else {
                        isLiked = false
                        imageViewCommunityDetailLike.setImageResource(R.drawable.icon_thumb)
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.deleteLikeData(postApartId!!, likeData)
                            likeList = viewModel.gettingLikeList(postApartId!!, postId!!)
                            textViewCommunityDetailCommentCnt.text = likeList.size.toString()
                        }
                    }
                }
            }
        }
    }

    // 좋아요 객체 설정
    suspend fun generatingLikeObject(): LikeData {
        var likeData = LikeData()
        val user = gettingUserData()

        val job1 = CoroutineScope(Dispatchers.Main).launch {
            val likeSequence = viewModel.getLikeSequence()
            viewModel.updateLikeSequence(likeSequence + 1)

            likeData.likeId = UUID.randomUUID().toString()
            likeData.likePostId = postId!!
            likeData.likeUserId = user.uid
        }
        job1.join()

        return  likeData

    }

    // 초기 데이터 설정
    private fun settingData() {
        fragmentCommunityDetailBinding.apply {
            textViewCommunityDetailDate.text = " "
            textViewCommunityDetailWriter.text = " "
            textViewCommunityDetailLikeCnt.text = " "
            textViewCommunityDetailCommentCnt.text = " "
            textViewCommunityDetailSubject.text = " "
            textViewCommunityDetailContent.text = " "

            CoroutineScope(Dispatchers.Main).launch {
                // 현재 글 번호에 해당하는 글 데이터를 가져온다.
                val postData = viewModel.selectCommunityPostData(postApartId!!, postId!!)
                // 사용자 정보를 가져온다.
                userList = gettingCommentUserData()

                commentData = generatingCommentObject()

                if (postData?.postImages != null) {
                    imageCommunityDetailList = postData.postImages!!
                } else {
                    viewPager2CommunityDetailImage.visibility = View.GONE
                    circleIndicatorCommunityDetail.visibility = View.GONE
                }

                textViewCommunityDetailDate.text = postData?.postAddDate

                userList.forEach {
                    if (it!!.uid == postData?.postUserId) {
                        textViewCommunityDetailWriter.text = it.name
                    }
                }
                textViewCommunityDetailSubject.text = postData?.postTitle
                textViewCommunityDetailContent.text = postData?.postContent
                textViewCommunityDetailToolbarTitle.text = postData?.postType



                textViewCommunityDetailLikeCnt.text = postData?.postLikeCnt.toString()

                val job1 = CoroutineScope(Dispatchers.IO).launch {
                    textViewCommunityDetailCommentCnt.setText(commentData?.commentCnt.toString())
                }
                job1.join()

                settingViewPager2CommunityDetailImage()
            }

        }
    }

    // 커뮤니티 댓글 설정
    private fun settingRecyclerViewCommunityDetailComment() {
        fragmentCommunityDetailBinding.apply {
            recyclerViewCommunityDetailComment.apply {
                CoroutineScope(Dispatchers.Main).launch {
                    commentList = gettingCommentData()
                    userList = gettingCommentUserData()
                    adapter = CommunityDetailCommentRecyclerViewAdapter(requireContext(), commentList, userList, this@CommunityDetailFragment, viewModel, postApartId!!)
                    layoutManager = LinearLayoutManager(communityActivity)
                }
            }

            // 리사이클러뷰에 스와이프, 드래그 기능 달기
            val swipeHelperCallback = SwipeHelperCallback(CommunityDetailCommentRecyclerViewAdapter(requireContext(), commentList, userList, this@CommunityDetailFragment, viewModel, postApartId!!)).apply {
                // 스와이프한 뒤 고정시킬 위치 지정
                setClamp(resources.displayMetrics.widthPixels.toFloat() * 2 / 7)    // 1080 / 4 = 270
            }
            ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(fragmentCommunityDetailBinding.recyclerViewCommunityDetailComment)

            // 다른 곳 터치 시 기존 선택했던 뷰 닫기
            fragmentCommunityDetailBinding.recyclerViewCommunityDetailComment.setOnTouchListener { _, _ ->
                swipeHelperCallback.removePreviousClamp(fragmentCommunityDetailBinding.recyclerViewCommunityDetailComment)
                false
            }
        }
    }

    // 댓글 입력 요소 설정
    private fun settingCommentInputForm() {
        fragmentCommunityDetailBinding.textInputCommunityDetailSendComment.setText(" ")
    }

    // 댓글 입력 객체 생성
    suspend fun generatingCommentObject() : CommentData {

        val authUser = App.authRepository.getCurrentUser()
        var commentData = CommentData()
        // 사용자 정보를 가져온다.
        val user = gettingUserData()

        if (authUser != null) {
            val user = App.userRepository.getUser(authUser.uid)
            if (user != null) {
                if (user.apartCertification == true) {
                    val job1 = CoroutineScope(Dispatchers.Main).launch {
                        // 댓글 번호를 가져온다.
                        val commentSequence = viewModel.getCommunityCommentSequence()
                        // 댓글 번호를 업데이트한다.
                        viewModel.updateCommunityCommentSequence(commentSequence + 1)
                        // 저장할 데이터를 담는다.
                        commentData.commentId = UUID.randomUUID().toString()
                        commentData.commentPostId = postId!!
                        commentData.commentIdx = commentSequence + 1
                        commentData.commentUserIdx = user.idx
                        commentData.commentUserId = user.uid
                        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
                        commentData.commentAddDate = simpleDateFormat.format(Date())
                        commentData.commentModifyDate = simpleDateFormat.format(Date())
                        commentData.commentPostIdx = postIdx!!
                        commentData.commentContent = fragmentCommunityDetailBinding.textInputCommunityDetailSendComment.text.toString()
                        commentData.commentCnt = commentList.size
                        commentData.commentState = CommentState.COMMENT_STATE_NORMAL.number
                    }
                    job1.join()
                } else {
                    fragmentCommunityDetailBinding.imageViewCommunityDetailSendComment.setOnClickListener {
                        val toast = Toast.makeText(
                            requireContext(),
                            "인증된 입주민만 작성할 수 있습니다.",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
        }

        return commentData
    }

    // 댓글 정보를 가져온다.
    suspend fun gettingCommentData(): MutableList<CommentData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            // 댓글 정보를 가져온다.
            commentList = viewModel.gettingCommunityCommentList(postApartId!!, postId!!)
        }
        job1.join()
        return commentList
    }

    // 유저 정보를 가져온다.
    suspend fun gettingCommentUserData(): List<UserModel?> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            userList = viewModel.getApartmentUserList(postApartId!!)
        }
        job1.join()
        return  userList
    }

    // 댓글 입력 완료 처리
    private fun commentDoneProcess() {
        fragmentCommunityDetailBinding.imageViewCommunityDetailSendComment.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                commentData = generatingCommentObject()
                viewModel.insertCommunityCommentData(postApartId!!, commentData!!)
                settingRecyclerViewCommunityDetailComment()
                Tools.hideSoftInput(requireActivity())
                settingCommentInputForm()
            }
        }
    }

    // 댓글 수정 완료 처리
    fun commentModifyProcess(position: Int, commentData: CommentData) {
        CoroutineScope(Dispatchers.Main).launch {
            var map = mutableMapOf<String, Any>(
                "commentContent" to fragmentCommunityDetailBinding.textInputCommunityDetailSendComment.text!!.toString(),
                "commentModifyDate" to SimpleDateFormat("yyyy.MM.dd").format(Date())
            )
            viewModel.updateCommunityCommentData(postApartId!!, commentData, map)
            settingRecyclerViewCommunityDetailComment()
            Tools.hideSoftInput(requireActivity())
            settingCommentInputForm()
        }
    }

    // 댓글 삭제 후 리사이클러뷰 갱신
    fun removingCommentData(position: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            commentList.removeAt(position)
            fragmentCommunityDetailBinding.recyclerViewCommunityDetailComment.adapter?.notifyDataSetChanged()
        }
    }

}
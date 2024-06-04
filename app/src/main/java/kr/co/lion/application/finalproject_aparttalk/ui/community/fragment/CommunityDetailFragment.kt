package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityDetailBinding
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.util.SwipeHelperCallback
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityDetailCommentRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityDetailImageViewPager2Adapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityAddViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityDetailViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunitySearchViewModel

class CommunityDetailFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityDetailBinding: FragmentCommunityDetailBinding
    lateinit var communityActivity: CommunityActivity
    private val viewModel: CommunityDetailViewModel by viewModels()

    // 이미지 저장용 리스트
    var imageCommunityDetailList = mutableListOf<String>()
    // 뷰페이저용 리스트
    var imageViewPagerList = mutableListOf<String>()

    // 현재 글 번호를 담을 변수
    var postIdx: Int? = null

    // 댓글 모델
    var commentData:CommentData? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityDetailBinding = FragmentCommunityDetailBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        // 글 번호를 받는다.
        postIdx = arguments?.getInt("postIdx", 0)

        settingToolbar()
        settingViewPager2CommunityDetailImage()
        settingData()
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

    // 커뮤니티 바텀시트 띄우기
    private fun settingCommunityBottom() {
        val communityBottomSheetFragment = CommunityBottomSheetFragment(this, 0)
        communityBottomSheetFragment.show(communityActivity.supportFragmentManager, "CommunityBottomSheetFragment")
    }

    // 커뮤니티 글 상세조회 뷰페이저 설정
    private fun settingViewPager2CommunityDetailImage() {
        fragmentCommunityDetailBinding.apply {
            viewPager2CommunityDetailImage.apply {
                adapter = CommunityDetailImageViewPager2Adapter(requireContext())
            }

            circleIndicatorCommunityDetail.setViewPager(viewPager2CommunityDetailImage)
            viewPager2CommunityDetailImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityDetail.adapterDataObserver
            )
        }
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
                val postData = viewModel.selectCommunityPostData(postIdx!!)
//                // 사용자 정보를 가져온다.
//                val userModel = UserDao.gettingUserInfoByUserIdx(communityPostModel!!.postUserIdx)

                if (postData?.postImages != null) {
                    imageCommunityDetailList = postData.postImages!!
                } else {
                    viewPager2CommunityDetailImage.visibility = View.GONE
                    circleIndicatorCommunityDetail.visibility = View.GONE
                }

                textViewCommunityDetailDate.text = postData?.postAddDate
                textViewCommunityDetailWriter.text = "홍길동"
                textViewCommunityDetailSubject.text = postData?.postTitle
                textViewCommunityDetailContent.text = postData?.postContent
                textViewCommunityDetailToolbarTitle.text = postData?.postType
            }

        }
    }

//    // 뷰페이저 리스트
//    private fun gettingViewPagerList() : MutableList<String> {
//        imageViewPagerList = viewModel.gettingCommunityPostImage(requireContext(), imageCommunityDetailList)
//        return imageViewPagerList
//    }

    // 커뮤니티 댓글 설정
    private fun settingRecyclerViewCommunityDetailComment() {
        fragmentCommunityDetailBinding.apply {
            recyclerViewCommunityDetailComment.apply {
                adapter = CommunityDetailCommentRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(communityActivity)
            }

            // 리사이클러뷰에 스와이프, 드래그 기능 달기
            val swipeHelperCallback = SwipeHelperCallback(CommunityDetailCommentRecyclerViewAdapter(requireContext())).apply {
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

}
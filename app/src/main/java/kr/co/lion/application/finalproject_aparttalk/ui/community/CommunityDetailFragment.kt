package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityDetailBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailCommentBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.SwipeHelperCallback

class CommunityDetailFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityDetailBinding: FragmentCommunityDetailBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityDetailBinding = FragmentCommunityDetailBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

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
                textViewCommunityDetailToolbarTitle.text = "질문"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    communityActivity.finish()
                }

                inflateMenu(R.menu.menu_community_detail)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 더보기
                        R.id.menuItemCommunityKebab -> {

                        }
                    }
                    true
                }
            }
        }
    }

    // 커뮤니티 글 상세조회 뷰페이저 설정
    private fun settingViewPager2CommunityDetailImage() {
        fragmentCommunityDetailBinding.apply {
            viewPager2CommunityDetailImage.apply {
                adapter = CommunityDetailImageViewPager2Adapter()
            }

            circleIndicatorCommunityDetail.setViewPager(viewPager2CommunityDetailImage)
            viewPager2CommunityDetailImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityDetail.adapterDataObserver
            )
        }
    }

    // 커뮤니티 글 상세조회 뷰페이저 어댑터
    inner class CommunityDetailImageViewPager2Adapter : RecyclerView.Adapter<CommunityDetailImageViewPager2Adapter.CommunityDetailImageViewHolder>(){
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
            val rowCommunityDetailImageBinding = RowCommunityDetailImageBinding.inflate(layoutInflater)
            val communityDetailImageViewHolder = CommunityDetailImageViewHolder(rowCommunityDetailImageBinding)

            return communityDetailImageViewHolder
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: CommunityDetailImageViewHolder, position: Int) {
            holder.rowCommunityDetailImageBinding.imageViewRowCommunityDetail.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    // 초기 데이터 설정
    private fun settingData() {
        fragmentCommunityDetailBinding.apply {
            textViewCommunityDetailDate.text = "2024.05.17"
            textViewCommunityDetailWriter.text = "글 작성자"
            textViewCommunityDetailLikeCnt.text = "999"
            textViewCommunityDetailCommentCnt.text = "999"
            textViewCommunityDetailSubject.text = "글 제목 (30자 제한)"
            textViewCommunityDetailContent.text = "글 내용입니다 글 내용입니다 글 내용입니다 글 내용입니다\n" +
                    "글 내용입니다 글 내용입니다 글 내용입니다 글 내용입니다\n" +
                    "글 내용입니다 글 내용입니다 글 내용입니다 (2000자 제한)"

        }
    }

    // 커뮤니티 댓글 설정
    private fun settingRecyclerViewCommunityDetailComment() {
        fragmentCommunityDetailBinding.apply {
            recyclerViewCommunityDetailComment.apply {
                adapter = CommunityDetailCommentRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(communityActivity)
            }

            // 리사이클러뷰에 스와이프, 드래그 기능 달기
            val swipeHelperCallback = SwipeHelperCallback(CommunityDetailCommentRecyclerViewAdapter()).apply {
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

    // 커뮤니티 댓글 어댑터
    inner class CommunityDetailCommentRecyclerViewAdapter : RecyclerView.Adapter<CommunityDetailCommentRecyclerViewAdapter.CommunityDetailViewHolder>(){
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
            val rowCommunityDetailCommentBinding = RowCommunityDetailCommentBinding.inflate(layoutInflater)
            val communityDetailViewHolder = CommunityDetailViewHolder(rowCommunityDetailCommentBinding)

            return communityDetailViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunityDetailViewHolder, position: Int) {
            holder.rowCommunityDetailCommentBinding.apply {
                textViewRowCommunityDetailCommentWriter.text = "댓글 작성자"
                textViewRowCommunityDetailCommentContent.text = "댓글입니다 댓글입니다 댓글입니다 댓글입니다 (100자 제한)"
            }
        }
    }

}
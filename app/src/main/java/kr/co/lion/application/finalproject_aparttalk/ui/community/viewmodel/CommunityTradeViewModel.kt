package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityCommentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class CommunityTradeViewModel: ViewModel() {
    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    var allList = mutableListOf<PostData>()
    var tradeList = mutableListOf<PostData>()
    var commentList = mutableListOf<CommentData>()

    // 커뮤니티 글 리스트 거래 탭 라벨
    private val _textViewCommunityListLabelTrade = MutableLiveData<String>()
    val textViewCommunityListLabelTrade: LiveData<String> get() = _textViewCommunityListLabelTrade

    // 커뮤니티 글 리스트 거래 탭 제목
    private val _textViewCommunityListTitleTrade = MutableLiveData<String>()
    val textViewCommunityListTitleTrade: LiveData<String> get() = _textViewCommunityListTitleTrade

    // 커뮤니티 글 리스트 거래 탭 좋아요 수
    private val _textViewCommunityListLikeCntTrade = MutableLiveData<Int>()
    val textViewCommunityListLikeCntTrade: LiveData<Int> get() = _textViewCommunityListLikeCntTrade

    // 커뮤니티 글 리스트 거래 탭 댓글 수
    private val _textViewCommunityListCommentCntTrade = MutableLiveData<Int>()
    val textViewCommunityListCommentCntTrade: LiveData<Int> get() = _textViewCommunityListCommentCntTrade

    // 커뮤니티 글 리스트 거래 탭 날짜
    private val _textViewCommunityListDateTrade = MutableLiveData<String>()
    val textViewCommunityListDateTrade: LiveData<String> get() = _textViewCommunityListDateTrade

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList(postApartId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 거래 리스트 받아오기
    suspend fun gettingCommunityTradeList(postApartId: String) : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList(postApartId)
            tradeList.clear()
            allList.forEach {
                when(it.postType) {
                    "거래" -> tradeList.add(it)
                }
            }
        }
        job1.join()

        return tradeList
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }
}
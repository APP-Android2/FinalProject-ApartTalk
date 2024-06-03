package kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.CommunityPostRepository

class CommunitySearchViewModel: ViewModel() {
    private val communityPostRepository = CommunityPostRepository()
    var allList = mutableListOf<PostData>()
    var searchList = mutableListOf<PostData>()

    // 커뮤니티 글 리스트 검색 탭 라벨
    private val _textViewCommunityListLabelSearch = MutableLiveData<String>()
    val textViewCommunityListLabelSearch: LiveData<String> get() = _textViewCommunityListLabelSearch

    // 커뮤니티 글 리스트 검색 탭 제목
    private val _textViewCommunityListTitleSearch = MutableLiveData<String>()
    val textViewCommunityListTitleSearch: LiveData<String> get() = _textViewCommunityListTitleSearch

    // 커뮤니티 글 리스트 검색 탭 좋아요 수
    private val _textViewCommunityListLikeCntSearch = MutableLiveData<Int>()
    val textViewCommunityListLikeCntSearch: LiveData<Int> get() = _textViewCommunityListLikeCntSearch

    // 커뮤니티 글 리스트 검색 탭 댓글 수
    private val _textViewCommunityListCommentCntSearch = MutableLiveData<Int>()
    val textViewCommunityListCommentCntSearch: LiveData<Int> get() = _textViewCommunityListCommentCntSearch

    // 커뮤니티 글 리스트 검색 탭 날짜
    private val _textViewCommunityListDateSearch = MutableLiveData<String>()
    val textViewCommunityListDateSearch: LiveData<String> get() = _textViewCommunityListDateSearch

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList() : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList()
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 검색 리스트 받아오기
    suspend fun gettingCommunitySearchList() : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList()
            searchList.clear()
            allList.forEach {
                when(it.postType) {
                    "기타" -> searchList.add(it)
                }
            }
        }
        job1.join()

        return searchList
    }
}
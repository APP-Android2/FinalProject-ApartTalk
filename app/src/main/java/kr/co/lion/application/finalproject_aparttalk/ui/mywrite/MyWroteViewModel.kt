package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.model.CommentData
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityCommentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository

class MyWroteViewModel(private val repository: CommunityPostRepository) : ViewModel() {

    private val _myWroteList = MutableLiveData<List<PostData>>()
    val myWroteList: LiveData<List<PostData>> get() = _myWroteList

    fun loadMyWrotePosts() {
        viewModelScope.launch {
            val authUser = App.authRepository.getCurrentUser() ?: return@launch
            val apartment = App.apartmentRepository.getApartment(authUser.uid) ?: return@launch
            val posts = repository.gettingCommunityPostList(apartment.uid)
            _myWroteList.value = posts.filter { it.postUserId == authUser.uid }
        }
    }

    private val communityPostRepository = CommunityPostRepository()
    private val communityCommentRepository = CommunityCommentRepository()
    var allList = mutableListOf<PostData>()
    var etcList = mutableListOf<PostData>()
    var commentList = mutableListOf<CommentData>()

    // 게시글 목록을 가져온다.
    suspend fun gettingCommunityPostList(postApartId: String) : MutableList<PostData> {
        return communityPostRepository.gettingCommunityPostList(postApartId)
    }

    // 이미지 데이터를 받아오는 메서드
    suspend fun gettingCommunityPostImage(context: Context, imageFileName:String, imageView: ImageView) {
        return communityPostRepository.gettingCommunityPostImage(context, imageFileName, imageView)
    }

    // 게시글 기타 리스트 받아오기
    suspend fun gettingCommunityEtcList(postApartId: String) : MutableList<PostData> {
        val job1 = CoroutineScope(Dispatchers.Main).launch {
            allList = gettingCommunityPostList(postApartId)
            etcList.clear()
            allList.forEach {
                when(it.postType) {
                    "기타" -> etcList.add(it)
                }
            }
        }
        job1.join()

        return etcList
    }

    // 댓글 목록을 가져온다.
    suspend fun gettingCommunityCommentList(postApartId: String, postId: String):MutableList<CommentData>{
        return communityCommentRepository.gettingCommunityCommentList(postApartId, postId)
    }
}
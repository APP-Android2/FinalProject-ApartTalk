package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunitySearchRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunitySearchViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityTradeViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunitySearchFragment : Fragment() {
    lateinit var fragmentCommunitySearchBinding: FragmentCommunitySearchBinding
    lateinit var communityActivity: CommunityActivity
    private val viewModel: CommunitySearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunitySearchBinding = FragmentCommunitySearchBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingEvent()
        settingSearch()
        settingRecyclerViewSearch()

        return fragmentCommunitySearchBinding.root
    }

    // 아파트 아이디 가져오기
    suspend fun gettingApartId(): String {
        var apartmentId = ""
        val authUser = App.authRepository.getCurrentUser()
        if (authUser != null) {
            val user = App.userRepository.getUser(authUser.uid)
            if (user != null) {
                val apartment = App.apartmentRepository.getApartment(user.apartmentUid)
                apartmentId = apartment!!.uid
            }
        }
        return  apartmentId
    }

    // 뒤로 가기
    private fun settingEvent() {
        fragmentCommunitySearchBinding.apply {
            textInputLayoutCommunitySearch.apply {
                setStartIconOnClickListener {
                    communityActivity.finish()
                }
            }
        }
    }

    // 검색창 설정
    private fun settingSearch() {
        fragmentCommunitySearchBinding.apply {
            textInputCommunitySearch.apply {
                setOnEditorActionListener { v, actionId, event ->
                    if (event != null && event.action == KeyEvent.ACTION_DOWN) {
                        // 검색 결과 가져오는 메서드
                        gettingSearchData()
                    }
                    true
                }
            }
        }
    }

    // 커뮤니티 검색 리사이클러뷰 설정
    private fun settingRecyclerViewSearch() {
        fragmentCommunitySearchBinding.apply {
            recyclerViewCommunitySearch.apply {
                adapter = CommunitySearchRecyclerViewAdapter(requireContext(), viewModel)
                layoutManager = LinearLayoutManager(communityActivity)
                val deco = MaterialDividerItemDecoration(communityActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 검색 결과의 데이터를 가져와 검색 화면의 리사이클러뷰를 갱신
    private fun gettingSearchData() {
        val keyword = fragmentCommunitySearchBinding.textInputCommunitySearch.text.toString()

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.allList = viewModel.gettingCommunityPostList(gettingApartId())
            viewModel.searchList.clear()
            viewModel.allList.forEach {
                if (it.postTitle.contains(keyword) || it.postContent.contains(keyword)) {
                    viewModel.searchList.add(it)
                }
            }

            fragmentCommunitySearchBinding.recyclerViewCommunitySearch.adapter?.notifyDataSetChanged()
        }
    }
}
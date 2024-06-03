package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.os.Bundle
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

        gettingCommunityPostList()
        settingEvent()
        settingRecyclerViewSearch()

        return fragmentCommunitySearchBinding.root
    }

    // 게시글 리스트 받아오기
    private fun gettingCommunityPostList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunitySearchList()
            fragmentCommunitySearchBinding.recyclerViewCommunitySearch.adapter?.notifyDataSetChanged()
        }
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

    // 커뮤니티 검색 리사이클러뷰 설정
    private fun settingRecyclerViewSearch() {
        fragmentCommunitySearchBinding.apply {
            recyclerViewCommunitySearch.apply {
                adapter = CommunitySearchRecyclerViewAdapter(requireContext(), viewModel.searchList)
                layoutManager = LinearLayoutManager(communityActivity)
                val deco = MaterialDividerItemDecoration(communityActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
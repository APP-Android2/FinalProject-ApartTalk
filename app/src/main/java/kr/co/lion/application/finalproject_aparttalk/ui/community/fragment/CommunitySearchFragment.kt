package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunitySearchBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunitySearchRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunitySearchFragment : Fragment() {
    lateinit var fragmentCommunitySearchBinding: FragmentCommunitySearchBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunitySearchBinding = FragmentCommunitySearchBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingEvent()
        settingRecyclerViewSearch()

        return fragmentCommunitySearchBinding.root
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
                adapter = CommunitySearchRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(communityActivity)
                val deco = MaterialDividerItemDecoration(communityActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
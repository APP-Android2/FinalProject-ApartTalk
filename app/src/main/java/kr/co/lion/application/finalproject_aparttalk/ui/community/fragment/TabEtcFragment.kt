package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.Intent
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
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityTabEtcRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityEtcViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityTradeViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabEtcFragment : Fragment() {
    lateinit var fragmentTabEtcBinding: FragmentTabEtcBinding
    lateinit var mainActivity: MainActivity
    private val viewModel: CommunityEtcViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabEtcBinding = FragmentTabEtcBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        gettingCommunityPostList()
        settingRecyclerViewTabTrade()
        settingFabTabEtcAdd()
        settingFloatingButton()

        return fragmentTabEtcBinding.root
    }

    // 게시글 리스트 받아오기
    private fun gettingCommunityPostList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunityEtcList()
            fragmentTabEtcBinding.recyclerViewTabEtc.adapter?.notifyDataSetChanged()
        }
    }

    // 커뮤니티 거래 탭 플로팅 버튼
    private fun settingFabTabEtcAdd() {
        fragmentTabEtcBinding.apply {
            fabTabEtcAdd.setOnClickListener {
                val intent = Intent(mainActivity, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_ADD_FRAGMENT)
                startActivity(intent)
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabEtcBinding.apply {
            recyclerViewTabEtc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabEtcAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabEtcAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }

    // 커뮤니티 기타 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabTrade() {
        fragmentTabEtcBinding.apply {
            recyclerViewTabEtc.apply {
                adapter = CommunityTabEtcRecyclerViewAdapter(requireContext(), viewModel)
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
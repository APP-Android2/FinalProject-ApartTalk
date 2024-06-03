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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabTradeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityTabTradeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityQuestionViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityTradeViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabTradeFragment : Fragment() {
    lateinit var fragmentTabTradeBinding: FragmentTabTradeBinding
    lateinit var mainActivity: MainActivity
    private val viewModel: CommunityTradeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabTradeBinding = FragmentTabTradeBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        gettingCommunityPostList()
        settingRecyclerViewTabTrade()
        settingFabTabTradeAdd()
        settingFloatingButton()

        return fragmentTabTradeBinding.root
    }

    // 게시글 리스트 받아오기
    private fun gettingCommunityPostList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunityTradeList()
            fragmentTabTradeBinding.recyclerViewTabTrade.adapter?.notifyDataSetChanged()
        }
    }

    // 커뮤니티 거래 탭 플로팅 버튼
    private fun settingFabTabTradeAdd() {
        fragmentTabTradeBinding.apply {
            fabTabTradeAdd.setOnClickListener {
                val intent = Intent(mainActivity, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_ADD_FRAGMENT)
                startActivity(intent)
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabTradeBinding.apply {
            recyclerViewTabTrade.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabTradeAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabTradeAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }


    // 커뮤니티 거래 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabTrade() {
        fragmentTabTradeBinding.apply {
            recyclerViewTabTrade.apply {
                adapter = CommunityTabTradeRecyclerViewAdapter(requireContext(), viewModel.tradeList)
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityTabQuestionRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabQuestionFragment : Fragment() {
    lateinit var fragmentTabQuestionBinding: FragmentTabQuestionBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabQuestionBinding = FragmentTabQuestionBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewTabQuestion()
        settingFabTabQuestionAdd()
        settingFloatingButton()

        return fragmentTabQuestionBinding.root
    }

    // 커뮤니티 질문 탭 플로팅 버튼
    private fun settingFabTabQuestionAdd() {
        fragmentTabQuestionBinding.apply {
            fabTabQuestionAdd.setOnClickListener {
                val intent = Intent(mainActivity, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_ADD_FRAGMENT)
                startActivity(intent)
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabQuestionBinding.apply {
            recyclerViewTabQuestion.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabQuestionAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabQuestionAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }

    // 커뮤니티 질문 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabQuestion() {
        fragmentTabQuestionBinding.apply {
            recyclerViewTabQuestion.apply {
                adapter = CommunityTabQuestionRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
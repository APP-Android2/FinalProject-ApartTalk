package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
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
                adapter = CommunityTabQuestionRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 커뮤니티 질문 탭 리사이클러뷰 어댑터
    inner class CommunityTabQuestionRecyclerViewAdapter : RecyclerView.Adapter<CommunityTabQuestionRecyclerViewAdapter.CommunityTabQuestionViewHolder>() {
        inner class CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding: RowCommunityTabQuestionBinding) : RecyclerView.ViewHolder(rowCommunityTabQuestionBinding.root) {
            val rowCommunityTabQuestionBinding : RowCommunityTabQuestionBinding

            init {
                this.rowCommunityTabQuestionBinding = rowCommunityTabQuestionBinding

                this.rowCommunityTabQuestionBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabQuestionViewHolder {
            val rowCommunityTabQuestionBinding = RowCommunityTabQuestionBinding.inflate(layoutInflater)
            val communityTabQuestionViewHolder = CommunityTabQuestionViewHolder(rowCommunityTabQuestionBinding)

            return communityTabQuestionViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: CommunityTabQuestionViewHolder, position: Int) {
            holder.rowCommunityTabQuestionBinding.apply {
                textViewCommunityListLabelQuestion.text = "질문"
                textViewCommunityListTitleQuestion.text = "글 제목입니다 $position"
                textViewCommunityListContentQuestion.text = "글 내용입니다 글 내용입니다 글 내용입니다\n" +
                        "글 내용입니다 글 내용입니다 글 내용입니다 "
                textViewCommunityListLikeCntQuestion.text = "999"
                textViewCommunityListCommentCntQuestion.text = "999"
                textViewCommunityListDateQuestion.text = "2024.05.17"

                linearLayoutCommunityListQuestion.setOnClickListener {
                    val intent = Intent(mainActivity, CommunityActivity::class.java)
                    intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                    // 게시글 번호도 주기
                    // communityIntent.putExtra("postIdx", searchList[position].postIdx)
                    startActivity(intent)
                }
            }
        }
    }
}
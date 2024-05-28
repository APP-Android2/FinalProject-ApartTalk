package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteHistoryBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName


class VoteHistoryFragment : Fragment() {

    lateinit var fragmentVoteHistoryBinding: FragmentVoteHistoryBinding
    lateinit var voteActivity: VoteActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentVoteHistoryBinding = FragmentVoteHistoryBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        return fragmentVoteHistoryBinding.root
    }

    // 툴바설정
    fun voteHistoryToolbar(){
        fragmentVoteHistoryBinding.apply {
            voteHistoryToolbar.apply {
                // 타이틀
                title = "주민 투표"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false,true,null)
                }
            }
        }

        // 버튼
        fun voteHistoryButton(){
            fragmentVoteHistoryBinding.apply {
                voteHistoryButton.setOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false,true,null)
                }
            }
        }
    }
}
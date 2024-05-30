package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
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

        voteHistoryToolbar()
        voteHistoryButton()

        return fragmentVoteHistoryBinding.root
    }

    // 툴바설정
    fun voteHistoryToolbar() {
        fragmentVoteHistoryBinding.apply {
            voteHistoryToolbar.apply {
                //title
                title = "지난 투표 내역"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(
                        VoteFragmentName.VOTE_TAB_FRAGMENT,
                        false,
                        true,
                        null
                    )
                }
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
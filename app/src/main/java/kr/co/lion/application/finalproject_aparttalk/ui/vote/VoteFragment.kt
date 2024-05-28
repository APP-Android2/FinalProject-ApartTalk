package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName


class VoteFragment : Fragment() {

    lateinit var fragmentVoteBinding: FragmentVoteBinding
    lateinit var voteActivity: VoteActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentVoteBinding = FragmentVoteBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        voteToolbar()
        voteButton()

        return fragmentVoteBinding.root
    }


    // 툴바 설정
    fun voteToolbar(){
        fragmentVoteBinding.apply {
            voteToolbar.apply {
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_LIST_FRAGMENT, false,true,null)
                }
            }
        }
    }

    fun voteButton(){
        fragmentVoteBinding.apply {
            voteButton.apply {
                setOnClickListener {

                    voteActivity.removeFragment(VoteFragmentName.VOTE_FRAGMENT)

                    voteActivity.replaceFragment(VoteFragmentName.VOTE_LIST_FRAGMENT,false,true,null)
                }
            }
        }
    }

    // 체크박스 설정
    private fun setupCheckBoxes() {
        val checkBox1 = fragmentVoteBinding.voteCheckBox1
        val checkBox2 = fragmentVoteBinding.voteCheckBox2
        val checkBox3 = fragmentVoteBinding.voteCheckBox3

        checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            // 다른 체크박스의 체크 해제
            if (isChecked) {
                checkBox2.isChecked = false
                checkBox3.isChecked = false
            }
            // 선택 항목 처리
        }

        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            // 다른 체크박스의 체크 해제
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox3.isChecked = false
            }
            // 선택 항목 처리
        }

        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            // 다른 체크박스의 체크 해제
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox2.isChecked = false
            }
            // 선택 항목 처리
        }
    }
}
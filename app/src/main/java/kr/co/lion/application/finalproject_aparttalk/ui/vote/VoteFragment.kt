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
        setupCheckBoxes()

        return fragmentVoteBinding.root
    }


    // 툴바 설정
    fun voteToolbar() {
        fragmentVoteBinding.apply {
            voteToolbar.apply {
                //title
                title = "주민 투표"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
                }
            }
        }
    }

    fun voteButton(){
        fragmentVoteBinding.apply {
            voteButton.apply {
                setOnClickListener {

                    voteActivity.removeFragment(VoteFragmentName.VOTE_FRAGMENT)

                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT,false,true,null)
                }
            }
        }
    }

    // 체크박스 설정
    // 체크박스 설정
    private fun setupCheckBoxes() {
        val checkBox1 = fragmentVoteBinding.voteCheckBox1
        val checkBox2 = fragmentVoteBinding.voteCheckBox2
        val checkBox3 = fragmentVoteBinding.voteCheckBox3

        var isUpdating = false

        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                // 선택된 체크박스 스타일 변경
                checkBox1.setTextColor(resources.getColor(R.color.black, null))
            } else {
                // 선택 해제된 체크박스 스타일 복원
                checkBox1.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }

        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox3.isChecked = false
                // 선택된 체크박스 스타일 변경
                checkBox2.setTextColor(resources.getColor(R.color.black, null))
            } else {
                // 선택 해제된 체크박스 스타일 복원
                checkBox2.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }

        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                // 선택된 체크박스 스타일 변경
                checkBox3.setTextColor(resources.getColor(R.color.black, null))
            } else {
                // 선택 해제된 체크박스 스타일 복원
                checkBox3.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }
    }
}
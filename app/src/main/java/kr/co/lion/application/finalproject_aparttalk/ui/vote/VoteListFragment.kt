package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteListBinding
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName


class VoteListFragment : Fragment() {

    lateinit var fragmentVoteListBinding: FragmentVoteListBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentVoteListBinding = FragmentVoteListBinding.inflate(inflater)
        mainActivity = activity as MainActivity


        return fragmentVoteListBinding.root
    }


    fun toolbar(){
        fragmentVoteListBinding.apply {
            voteListToolbar.apply {
                // 타이틀
                title = "주민 투표"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainFragmentName.HOME_FRAGMENT, true,null)
                }
            }
        }
    }

}
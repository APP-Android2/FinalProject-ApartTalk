package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInfoBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class InfoFragment : Fragment() {

    lateinit var fragmentInfoBinding: FragmentInfoBinding
    lateinit var infoActivity: InfoActivity
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentInfoBinding = FragmentInfoBinding.inflate(layoutInflater)
        infoActivity = activity as InfoActivity
        mainActivity = activity as MainActivity

        settingToolbar()

        return fragmentInfoBinding.root
    }

    fun settingToolbar() {
        fragmentInfoBinding.apply {
            userInfoToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    mainActivity.removeFragment(MainFragmentName.ENTIRE_MENU_FRAGMENT)
                }
                inflateMenu(R.menu.menu_userinfo)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.userInfoEdit -> {
                            infoActivity.replaceFragment(
                                InfoFragmentName.EDIT_USER_INFO_NUMBER_FRAGMENT,
                                true,
                                true,
                                null
                            )

                        }
                    }
                    true
                }
            }

        }
    }
}


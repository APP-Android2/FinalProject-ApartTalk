package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEditUserInfoBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEditUserInfoNumberBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName


class EditUserInfoFragment : Fragment() {
    lateinit var fragmentEditUserInfoBinding: FragmentEditUserInfoBinding
    lateinit var infoActivity: InfoActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentEditUserInfoBinding = FragmentEditUserInfoBinding.inflate(layoutInflater)
        infoActivity = activity as InfoActivity

        settingToolbar()
        settingButton()
        return fragmentEditUserInfoBinding.root

    }

    fun settingToolbar() {
        fragmentEditUserInfoBinding.apply {
            editUserInfoToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    infoActivity.removeFragment(InfoFragmentName.EDIT_USER_INFO_NUMBER_FRAGMENT)
                }
            }


        }
    }
    fun settingButton() {
        fragmentEditUserInfoBinding.apply {
            userInfoEditButton.apply {
                    setOnClickListener {
                        infoActivity.replaceFragment(InfoFragmentName.INFO_FRAGMENT, true, true, null)

                }
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEditUserInfoNumberBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class EditUserInfoNumberFragment : Fragment() {
    lateinit var fragmentEditUserInfoNumberBinding: FragmentEditUserInfoNumberBinding
    lateinit var infoActivity: InfoActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentEditUserInfoNumberBinding = FragmentEditUserInfoNumberBinding.inflate(layoutInflater)

        settingToolbar()
        settingButton()

        infoActivity = activity as InfoActivity
        return fragmentEditUserInfoNumberBinding.root


    }

    fun settingToolbar(){
        fragmentEditUserInfoNumberBinding.apply {
            editUserInfoNumberToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    infoActivity.removeFragment(InfoFragmentName.INFO_FRAGMENT)
                }
            }
        }
    }

    fun settingButton(){
        fragmentEditUserInfoNumberBinding.apply {
            editUserInfoNumberButton.apply{
                setOnClickListener {
                    infoActivity.replaceFragment(InfoFragmentName.EDIT_USER_INFO_FRAGMENT, true, true, null)
                }
            }
        }
    }
}
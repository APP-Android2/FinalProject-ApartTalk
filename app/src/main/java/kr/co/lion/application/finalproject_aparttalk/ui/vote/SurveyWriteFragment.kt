package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class SurveyWriteFragment : Fragment() {

    lateinit var fragmentSurveyWriteBinding: FragmentSurveyWriteBinding
    lateinit var voteActivity: VoteActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyWriteBinding = FragmentSurveyWriteBinding.inflate(inflater)
        voteActivity = activity as VoteActivity



        return fragmentSurveyWriteBinding.root
    }

    // 툴바 설정
    fun surveyWriteToolbar(){
        fragmentSurveyWriteBinding.apply {
            surveyWriteToolbar.apply {
                // title
                title = "설문조사"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_LIST_FRAGMENT,false,false,null)
                }
            }
        }
    }

    // 버튼
    fun surveyWriteButton(){
        fragmentSurveyWriteBinding.apply {
            surveyWriteButton.setOnClickListener {

                voteActivity.removeFragment(VoteFragmentName.SURVEY_WRITE_FRAGMENT)

                voteActivity.replaceFragment(VoteFragmentName.SURVEY_LIST_FRAGMENT,false,false,null)
            }
        }
    }
}
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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class SurveyFragment : Fragment() {

    lateinit var fragmentSurveyBinding: FragmentSurveyBinding
    lateinit var voteActivity: VoteActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentSurveyBinding = FragmentSurveyBinding.inflate(inflater)
        voteActivity = voteActivity as VoteActivity

        return fragmentSurveyBinding.root
    }

    // 툴바설정
    fun surveyToolbar(){
        fragmentSurveyBinding.apply {
            surveyToolbar.apply {
                //title
                title = "주민 투표"
                // back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_LIST_FRAGMENT,false,true,null)
                }
            }
        }
    }

    // 체크박스 설정
    private fun setupCheckBoxes() {
        val checkBox1 = fragmentSurveyBinding.surveyCheckBox1
        val checkBox2 = fragmentSurveyBinding.surveyCheckBox2
        val checkBox3 = fragmentSurveyBinding.surveyCheckBox3

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
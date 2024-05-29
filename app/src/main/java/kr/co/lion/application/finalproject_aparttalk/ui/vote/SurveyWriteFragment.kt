package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName
import java.text.SimpleDateFormat
import java.util.*

class SurveyWriteFragment : Fragment() {

    lateinit var fragmentSurveyWriteBinding: FragmentSurveyWriteBinding
    lateinit var voteActivity: VoteActivity
    private var isStartDateSelected: Boolean = true // 시작 날짜와 종료 날짜 선택을 구분하기 위한 변수

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyWriteBinding = FragmentSurveyWriteBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        surveyWriteButton()
        surveyWriteToolbar()
        setupCalendarView()

        // 소프트 입력 모드 설정
        voteActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        return fragmentSurveyWriteBinding.root
    }

    // 툴바 설정
    fun surveyWriteToolbar(){
        fragmentSurveyWriteBinding.apply {
            surveyWriteToolbar.apply {
                //title
                title = "설문조사 만들기"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT,false,false,null)
                }
            }
        }
    }

    // 버튼
    fun surveyWriteButton(){
        fragmentSurveyWriteBinding.apply {
            surveyWriteButton.setOnClickListener {
                voteActivity.removeFragment(VoteFragmentName.SURVEY_WRITE_FRAGMENT)
                voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT,false,false,null)
            }
        }
    }

    // CalendarView 설정
    fun setupCalendarView() {
        fragmentSurveyWriteBinding.surveyWriteCalender.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

            if (isStartDateSelected) {
                fragmentSurveyWriteBinding.surveyWriteTextViewStartDate1.text = selectedDate
                isStartDateSelected = false // 다음 선택은 종료 날짜로 변경
            } else {
                fragmentSurveyWriteBinding.surveyWriteTextViewEndDate1.text = selectedDate
                isStartDateSelected = true // 다음 선택은 시작 날짜로 변경
            }
        }

        fragmentSurveyWriteBinding.surveyWriteTextViewStartDate.setOnClickListener {
            isStartDateSelected = true // 시작 날짜를 선택하도록 설정
        }

        fragmentSurveyWriteBinding.surveyWriteTextViewEndDate.setOnClickListener {
            isStartDateSelected = false // 종료 날짜를 선택하도록 설정
        }
    }
}

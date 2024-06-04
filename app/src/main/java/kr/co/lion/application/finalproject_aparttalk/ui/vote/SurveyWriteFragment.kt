package kr.co.lion.application.finalproject_aparttalk.ui.vote

import SurveyViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.model.Survey
import kr.co.lion.application.finalproject_aparttalk.repository.SurveyRepository
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName
import java.text.SimpleDateFormat
import java.util.*

class SurveyWriteFragment : Fragment() {

    lateinit var fragmentSurveyWriteBinding: FragmentSurveyWriteBinding
    lateinit var voteActivity: VoteActivity
    private lateinit var surveyViewModel: SurveyViewModel
    private var isStartDateSelected: Boolean = true // 시작 날짜와 종료 날짜 선택을 구분하기 위한 변수

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyWriteBinding = FragmentSurveyWriteBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        // ViewModel 초기화
        val repository = SurveyRepository()
        val viewModelFactory = SurveyViewModelFactory(repository)
        surveyViewModel = ViewModelProvider(this, viewModelFactory).get(SurveyViewModel::class.java)

        surveyWriteButton()
        surveyWriteToolbar()
        setupCalendarView()
        observeViewModel()

        // 소프트 입력 모드 설정
        voteActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        return fragmentSurveyWriteBinding.root
    }

    private fun observeViewModel() {
        surveyViewModel.saveResult.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                // 데이터 저장 성공 시 SurveyFragment로 데이터 전달
                val surveyFragment = SurveyFragment().apply {
                    arguments = Bundle().apply {
                        putString("surveyTitle", fragmentSurveyWriteBinding.surveyWriteEditTextTitle.text.toString())
                        putString("surveyContent", fragmentSurveyWriteBinding.surveyWriteTextViewContent.text.toString())
                        putString("surveyCheck1Text", fragmentSurveyWriteBinding.surveyWirteEditTextWrite1.text.toString())
                        putString("surveyCheck2Text", fragmentSurveyWriteBinding.surveyWriteEditTextWrite2.text.toString())
                        putString("surveyCheck3Text", fragmentSurveyWriteBinding.surveyWirteEditTextWrite3.text.toString())
                    }
                }
                voteActivity.replaceFragment(VoteFragmentName.SURVEY_FRAGMENT, false, false, surveyFragment.arguments)
            } else {
                // 데이터 저장 실패 시 메시지 표시
                println("SurveyWriteFragment: Data save failed")
            }
        })
    }

    // 툴바 설정
    fun surveyWriteToolbar() {
        fragmentSurveyWriteBinding.apply {
            surveyWriteToolbar.apply {
                // title
                title = "설문조사 만들기"
                // Back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, false, null)
                }
            }
        }
    }

    // 버튼
    fun surveyWriteButton() {
        fragmentSurveyWriteBinding.apply {
            surveyWriteButton.setOnClickListener {
                val surveyTitle = surveyWriteEditTextTitle.text.toString()
                val surveyContent = surveyWriteTextViewContent.text.toString()
                val surveyStartDate = surveyWriteTextViewStartDate1.text.toString()
                val surveyEndDate = surveyWriteTextViewEndDate1.text.toString()
                val surveyCheck1 = surveyWirteEditTextWrite1.text.toString()
                val surveyCheck2 = surveyWriteEditTextWrite2.text.toString()
                val surveyCheck3 = surveyWirteEditTextWrite3.text.toString()

                val survey = Survey(
                    surveyTitle = surveyTitle,
                    surveyContent = surveyContent,
                    surveyDate = "$surveyStartDate - $surveyEndDate",
                    surveyState = true, // 임의의 값 설정
                    surveyCheck1 = surveyCheck1.isNotEmpty(),
                    surveyCheck2 = surveyCheck2.isNotEmpty(),
                    surveyCheck3 = surveyCheck3.isNotEmpty(),
                    surveyCheck1Text = surveyCheck1,
                    surveyCheck2Text = surveyCheck2,
                    surveyCheck3Text = surveyCheck3
                )

                // ViewModel을 통해 Firestore에 데이터 저장
                surveyViewModel.saveSurvey(survey)
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

package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName
import kotlinx.coroutines.*
import kr.co.lion.application.finalproject_aparttalk.R
import java.text.SimpleDateFormat
import java.util.*

class SurveyFragment : Fragment() {

    lateinit var fragmentSurveyBinding: FragmentSurveyBinding
    lateinit var voteActivity: VoteActivity
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // OnBackPressedCallback 설정
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로가기 버튼 눌렀을 때 VoteTabFragment로 이동
                voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyBinding = FragmentSurveyBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        // 툴바 설정
        surveyToolbar()

        // 데이터 수신 및 체크박스 설정
        arguments?.let {
            val surveyTitle = it.getString("surveyTitle")
            val surveyContent = it.getString("surveyContent")
            val surveyCheck1Text = it.getString("surveyCheck1Text")
            val surveyCheck2Text = it.getString("surveyCheck2Text")
            val surveyCheck3Text = it.getString("surveyCheck3Text")
            val surveyEndDate = it.getString("surveyEndDate")

            fragmentSurveyBinding.surveyTitleTextView.text = surveyTitle
            fragmentSurveyBinding.surveyContentTextView.text = surveyContent

            setupCheckBox(fragmentSurveyBinding.surveyCheckBox1, surveyCheck1Text)
            setupCheckBox(fragmentSurveyBinding.surveyCheckBox2, surveyCheck2Text)
            setupCheckBox(fragmentSurveyBinding.surveyCheckBox3, surveyCheck3Text)

            // 종료 날짜가 유효한지 확인하여 버튼 비활성화
            if (!surveyEndDate.isNullOrEmpty()) {
                checkSurveyEndDate(surveyEndDate)
            } else {
                fragmentSurveyBinding.surveyButton.isEnabled = true
            }

            // 사용자 참여 여부 확인
            if (surveyTitle != null) {
                checkUserParticipation(surveyTitle)
            }

            fragmentSurveyBinding.surveyButton.setOnClickListener {
                // Firestore에서 각 항목의 득표 수 가져오기 및 업데이트
                CoroutineScope(Dispatchers.Main).launch {
                    val surveyId = "survey_${surveyTitle.hashCode()}"
                    val userId = auth.currentUser?.uid ?: "anonymous"
                    val surveyDocRef = db.collection("Surveys").document(surveyId)
                    val userSurveyDocRef = db.collection("SurveyVotes").document("$userId$surveyId")

                    val surveyData = surveyDocRef.get().await()

                    if (!surveyData.exists()) {
                        // 문서가 존재하지 않는 경우 아무것도 하지 않음
                        println("Document does not exist")
                        return@launch
                    }

                    var surveyCheck1Count = surveyData.getLong("surveyCheck1Count")?.toInt() ?: 0
                    var surveyCheck2Count = surveyData.getLong("surveyCheck2Count")?.toInt() ?: 0
                    var surveyCheck3Count = surveyData.getLong("surveyCheck3Count")?.toInt() ?: 0

                    if (fragmentSurveyBinding.surveyCheckBox1.isChecked) {
                        surveyCheck1Count++
                    }
                    if (fragmentSurveyBinding.surveyCheckBox2.isChecked) {
                        surveyCheck2Count++
                    }
                    if (fragmentSurveyBinding.surveyCheckBox3.isChecked) {
                        surveyCheck3Count++
                    }

                    surveyDocRef.update(
                        "surveyCheck1Count", surveyCheck1Count,
                        "surveyCheck2Count", surveyCheck2Count,
                        "surveyCheck3Count", surveyCheck3Count
                    ).await()

                    // 사용자 참여 기록 저장
                    userSurveyDocRef.set(mapOf(
                        "userId" to userId,
                        "surveyId" to surveyId,
                        "timestamp" to System.currentTimeMillis()
                    )).await()

                    // 여기서 설문조사 완료 화면으로 이동
                    val surveyCompleteFragment = SurveyCompleteFragment().apply {
                        arguments = Bundle().apply {
                            putString("surveyTitle", surveyTitle)
                            putString("surveyCheck1Text", surveyCheck1Text)
                            putString("surveyCheck2Text", surveyCheck2Text)
                            putString("surveyCheck3Text", surveyCheck3Text)
                            putInt("surveyCheck1Count", surveyCheck1Count)
                            putInt("surveyCheck2Count", surveyCheck2Count)
                            putInt("surveyCheck3Count", surveyCheck3Count)
                        }
                    }
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_COMPLETE_FRAGMENT, false, true, surveyCompleteFragment.arguments)
                }
            }
        }

        return fragmentSurveyBinding.root
    }

    // 툴바 설정
    fun surveyToolbar() {
        fragmentSurveyBinding.apply {
            surveyToolbar.apply {
                setNavigationOnClickListener {
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_LIST_FRAGMENT, false, true, null)
                }
            }
        }
    }

    // 체크박스 설정
    private fun setupCheckBox(checkBox: CheckBox, text: String?) {
        if (text.isNullOrEmpty()) {
            checkBox.visibility = View.GONE
        } else {
            checkBox.visibility = View.VISIBLE
            checkBox.text = text
        }
    }

    // 종료 날짜를 확인하여 버튼을 비활성화하는 함수
    private fun checkSurveyEndDate(surveyEndDate: String) {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val endDate = sdf.parse(surveyEndDate)
        val currentDate = Date()

        if (endDate.before(currentDate)) {
            fragmentSurveyBinding.surveyButton.isEnabled = false
            fragmentSurveyBinding.surveyButton.text = "설문조사 종료"
        }
    }

    // 사용자 참여 여부 확인
    private fun checkUserParticipation(surveyTitle: String) {
        val userId = auth.currentUser?.uid ?: "anonymous"
        val surveyId = "survey_${surveyTitle.hashCode()}"
        val userSurveyDocRef = db.collection("SurveyVotes").document("$userId$surveyId")

        CoroutineScope(Dispatchers.Main).launch {
            val userSurveyData = userSurveyDocRef.get().await()
            if (userSurveyData.exists()) {
                // 이미 참여한 경우 버튼 비활성화
                fragmentSurveyBinding.surveyButton.isEnabled = false
                fragmentSurveyBinding.surveyButton.text = "이미 참여한 설문조사입니다"
            }
        }
    }
}

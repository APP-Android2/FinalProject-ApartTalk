package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class SurveyCompleteFragment : Fragment() {

    lateinit var fragmentSurveyCompleteBinding: FragmentSurveyCompleteBinding
    lateinit var voteActivity: VoteActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyCompleteBinding = FragmentSurveyCompleteBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        // 툴바 설정
        setupToolbar()

        // 데이터 수신 및 결과 표시
        arguments?.let {
            val surveyTitle = it.getString("surveyTitle")
            val surveyCheck1Text = it.getString("surveyCheck1Text")
            val surveyCheck2Text = it.getString("surveyCheck2Text")
            val surveyCheck3Text = it.getString("surveyCheck3Text")
            val surveyCheck1Count = it.getInt("surveyCheck1Count")
            val surveyCheck2Count = it.getInt("surveyCheck2Count")
            val surveyCheck3Count = it.getInt("surveyCheck3Count")
            val totalVotes = surveyCheck1Count + surveyCheck2Count + surveyCheck3Count

            fragmentSurveyCompleteBinding.surveyCompleteTitle.text = surveyTitle
            setupResult(
                fragmentSurveyCompleteBinding.surveyCompleteTextView1,
                fragmentSurveyCompleteBinding.surveyCompleteProgressBar,
                fragmentSurveyCompleteBinding.surveyCompleteCount1,
                surveyCheck1Text,
                surveyCheck1Count,
                totalVotes
            )
            setupResult(
                fragmentSurveyCompleteBinding.surveyCompleteTextView2,
                fragmentSurveyCompleteBinding.surveyCompleteProgressBar2,
                fragmentSurveyCompleteBinding.surveyCompleteCount2,
                surveyCheck2Text,
                surveyCheck2Count,
                totalVotes
            )
            setupResult(
                fragmentSurveyCompleteBinding.surveyCompleteTextView3,
                fragmentSurveyCompleteBinding.surveyCompleteProgressBar3,
                fragmentSurveyCompleteBinding.surveyCompleteCount3,
                surveyCheck3Text,
                surveyCheck3Count,
                totalVotes
            )
        }

        return fragmentSurveyCompleteBinding.root
    }

    // 툴바 설정
    private fun setupToolbar() {
        fragmentSurveyCompleteBinding.surveyCompleteToolbar.apply {
            title = "설문조사 결과"
            setNavigationIcon(R.drawable.icon_back)
            setNavigationOnClickListener {
                voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
            }
        }
    }

    // 결과 설정
    private fun setupResult(
        textView: TextView,
        progressBar: ProgressBar,
        countView: TextView,
        text: String?,
        count: Int,
        totalVotes: Int
    ) {
        textView.text = text
        val percentage = if (totalVotes > 0) (count * 100) / totalVotes else 0
        countView.text = "$count 표 ($percentage%)"

        // ProgressBar 설정
        progressBar.max = 100
        progressBar.progress = 0

        progressBar.isVisible = true
        progressBar.post {
            progressBar.setProgressWithAnimation(percentage)
        }
    }

    // ProgressBar에 애니메이션 추가 함수
    private fun ProgressBar.setProgressWithAnimation(progress: Int) {
        this.animate()
            .setDuration(1000)
            .setUpdateListener {
                this.progress = (it.animatedFraction * progress).toInt()
            }
            .start()
    }
}

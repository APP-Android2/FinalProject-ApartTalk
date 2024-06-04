package kr.co.lion.application.finalproject_aparttalk.ui.vote

import SurveyViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.lion.application.finalproject_aparttalk.databinding.RowSurveyBinding
import kr.co.lion.application.finalproject_aparttalk.model.Survey
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class SurveyListAdapter(
    private val surveys: List<Survey>,
    private val voteActivity: VoteActivity,
    private val surveyViewModel: SurveyViewModel
) : RecyclerView.Adapter<SurveyListAdapter.SurveyListViewHolder>() {

    inner class SurveyListViewHolder(val rowSurveyBinding: RowSurveyBinding) :
        RecyclerView.ViewHolder(rowSurveyBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowSurveyBinding.inflate(layoutInflater, parent, false)
        return SurveyListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return surveys.size
    }

    override fun onBindViewHolder(holder: SurveyListViewHolder, position: Int) {
        val survey = surveys[position]
        holder.rowSurveyBinding.rowSurveyTextView1.text = survey.surveyTitle
        holder.rowSurveyBinding.rowSurveyTextView2.text = survey.surveyDate

        holder.itemView.setOnClickListener {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "anonymous"
            val surveyId = "survey_${survey.surveyTitle.hashCode()}"

            // Check if the user has already voted
            voteActivity.lifecycleScope.launch {
                val hasVoted = withContext(Dispatchers.IO) {
                    surveyViewModel.hasUserVoted(userId, surveyId)
                }

                val bundle = Bundle().apply {
                    putString("surveyTitle", survey.surveyTitle)
                    putString("surveyContent", survey.surveyContent)
                    putString("surveyCheck1Text", survey.surveyCheck1Text)
                    putString("surveyCheck2Text", survey.surveyCheck2Text)
                    putString("surveyCheck3Text", survey.surveyCheck3Text)
                    putString("surveyEndDate", survey.surveyDate.split(" - ").last()) // 종료 날짜 전달
                }

                if (hasVoted) {
                    // If the user has already voted, navigate to SurveyCompleteFragment
                    bundle.apply {
                        putInt("surveyCheck1Count", survey.surveyCheck1Count)
                        putInt("surveyCheck2Count", survey.surveyCheck2Count)
                        putInt("surveyCheck3Count", survey.surveyCheck3Count)
                    }
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_COMPLETE_FRAGMENT, false, true, bundle)
                } else {
                    // If the user has not voted, navigate to SurveyFragment
                    bundle.apply {
                        putBoolean("surveyCheck1", survey.surveyCheck1)
                        putBoolean("surveyCheck2", survey.surveyCheck2)
                        putBoolean("surveyCheck3", survey.surveyCheck3)
                    }
                    voteActivity.replaceFragment(VoteFragmentName.SURVEY_FRAGMENT, false, true, bundle)
                }
            }
        }
    }
}

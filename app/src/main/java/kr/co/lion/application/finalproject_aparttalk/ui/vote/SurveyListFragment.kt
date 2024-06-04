package kr.co.lion.application.finalproject_aparttalk.ui.vote

import SurveyViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyListBinding
import kr.co.lion.application.finalproject_aparttalk.repository.SurveyRepository
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class SurveyListFragment : Fragment() {

    lateinit var fragmentSurveyListBinding: FragmentSurveyListBinding
    lateinit var voteActivity: VoteActivity
    lateinit var surveyViewModel: SurveyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentSurveyListBinding = FragmentSurveyListBinding.inflate(inflater)
        voteActivity = activity as VoteActivity
        val repository = SurveyRepository()
        val viewModelFactory = SurveyViewModelFactory(repository)
        surveyViewModel = ViewModelProvider(this, viewModelFactory).get(SurveyViewModel::class.java)

        surveyListRecyclerView()
        surveyListButton()
        observeViewModel()

        return fragmentSurveyListBinding.root
    }

    // recylerView
    fun surveyListRecyclerView() {
        fragmentSurveyListBinding.apply {
            surveyListRecyclerView.apply {
                layoutManager = LinearLayoutManager(voteActivity)
            }
        }
    }

    private fun observeViewModel() {
        surveyViewModel.surveys.observe(viewLifecycleOwner, Observer { surveys ->
            fragmentSurveyListBinding.surveyListRecyclerView.adapter = SurveyListAdapter(surveys, voteActivity, surveyViewModel)
        })

        surveyViewModel.fetchSurveys()
    }

    // button
    fun surveyListButton() {
        fragmentSurveyListBinding.apply {
            surveyListButton.setOnClickListener {
                voteActivity.removeFragment(VoteFragmentName.SURVEY_LIST_FRAGMENT)
                voteActivity.replaceFragment(VoteFragmentName.SURVEY_WRITE_FRAGMENT, false, true, null)
            }
        }
    }
}

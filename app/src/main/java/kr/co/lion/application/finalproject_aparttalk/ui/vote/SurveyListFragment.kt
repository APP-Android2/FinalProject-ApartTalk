package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSurveyListBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowSurveyBinding
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName


class SurveyListFragment : Fragment() {

    lateinit var fragmentSurveyListBinding: FragmentSurveyListBinding
    lateinit var voteActivity: VoteActivity
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentSurveyListBinding = FragmentSurveyListBinding.inflate(inflater)
        voteActivity = voteActivity as VoteActivity
        mainActivity = mainActivity as MainActivity

        surveyListRecyclerView()
        surveyListButton()

        return fragmentSurveyListBinding.root
    }

    // recylerView
    fun surveyListRecyclerView(){
        fragmentSurveyListBinding.apply {
            surveyListRecyclerView.apply {

                adapter = SurveyListAdapter()
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    // button

    fun surveyListButton(){
        fragmentSurveyListBinding.apply {
            surveyListButton.setOnClickListener {
                mainActivity.replaceFragment(MainFragmentName.HOME_FRAGMENT,false,null)
            }
        }
    }

    // Adapter
    inner class SurveyListAdapter : RecyclerView.Adapter<SurveyListAdapter.SurveyListViewHolder>(){
        inner class SurveyListViewHolder(val rowSurveyBinding: RowSurveyBinding) : RecyclerView.ViewHolder(rowSurveyBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyListViewHolder {
            // LayoutInflater를 parent.context로부터 가져옴
            val layoutInflater = LayoutInflater.from(parent.context)
            // RowReviewListBinding 인플레이션
            val binding = RowSurveyBinding.inflate(layoutInflater, parent, false)
            // ViewHolder 인스턴스 생성 및 반환
            return SurveyListViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: SurveyListViewHolder, position: Int) {
            holder.rowSurveyBinding.rowSurveyTextView1.text = "오늘 저녁 뭐 먹을까요? $position"
            holder.rowSurveyBinding.rowSurveyTextView2.text = "2024년 04/01 $position"
        }
    }
}
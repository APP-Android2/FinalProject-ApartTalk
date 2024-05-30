package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteListBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowVoteBinding
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName


class VoteListFragment : Fragment() {

    lateinit var fragmentVoteListBinding: FragmentVoteListBinding
    lateinit var voteActivity: VoteActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentVoteListBinding = FragmentVoteListBinding.inflate(inflater)
        voteActivity = activity as VoteActivity

        voteListRecyclerView()
        voteListButton()



        return fragmentVoteListBinding.root
    }

    fun voteListButton(){
        fragmentVoteListBinding.apply {
            voteListButton.setOnClickListener {
                voteActivity.removeFragment(VoteFragmentName.VOTE_LIST_FRAGMENT)
                voteActivity.replaceFragment(VoteFragmentName.VOTE_FRAGMENT,false,true,null)
            }
        }
    }

    fun voteListRecyclerView(){
        fragmentVoteListBinding.apply {
            voteListRecyclerView.apply {

                adapter = VoteListAdapter()
                layoutManager = LinearLayoutManager(voteActivity)
            }
        }
    }
    inner class VoteListAdapter : RecyclerView.Adapter<VoteListAdapter.VoteListViewHolder>(){
        inner class VoteListViewHolder(val rowVoteBinding: RowVoteBinding): RecyclerView.ViewHolder(rowVoteBinding.root)


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteListViewHolder {
            // LayoutInflater를 parent.context로 부터 가져옴
            val layoutInflater = LayoutInflater.from(parent.context)
            // RowVoteBinding 인플레이션
            val binding = RowVoteBinding.inflate(layoutInflater,parent,false)
            // ViewHolder 인스턴스 생성 및 반환
            return VoteListViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: VoteListViewHolder, position: Int) {
            holder.rowVoteBinding.rowVoteTextView1.text = "00 선거"
            holder.rowVoteBinding.rowVoteTextView2.text = "2024년 04/01 ~ 04/07"

            holder.itemView.setOnClickListener {
                voteActivity.replaceFragment(VoteFragmentName.VOTE_HISTORY_FRAGMENT, false,true,null)
            }
        }
    }

}
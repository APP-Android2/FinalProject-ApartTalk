package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteHistoryBinding
import kr.co.lion.application.finalproject_aparttalk.repository.VoteResultRepository
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class VoteHistoryFragment : Fragment() {

    private lateinit var binding: FragmentVoteHistoryBinding
    private lateinit var voteActivity: VoteActivity
    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val voteResultRepository by lazy { VoteResultRepository(firestore) }
    private val voteResultViewModel: VoteResultViewModel by viewModels {
        VoteResultViewModelFactory(voteResultRepository, "txTJQK1BVTgb7C2PfSO6")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVoteHistoryBinding.inflate(inflater, container, false)
        voteActivity = activity as VoteActivity

        voteHistoryToolbar()
        voteHistoryButton()
        setupObservers()

        return binding.root
    }

    private fun voteHistoryToolbar() {
        binding.voteHistoryToolbar.apply {
            title = "지난 투표 내역"
            setNavigationIcon(R.drawable.icon_back)
            setNavigationOnClickListener {
                voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
            }
        }
    }

    private fun voteHistoryButton() {
        binding.voteHistoryButton.setOnClickListener {
            voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
        }
    }

    private fun setupObservers() {
        voteResultViewModel.voteResultData.observe(viewLifecycleOwner, Observer { voteResult ->
            voteResult?.let {
                binding.voteHistoryTextView.text = it.electionName
                binding.voteHistoryTextView1.text = it.electionDate
                binding.voteHistoryTextView2.text = it.results.replace("\\n", "\n")
            }
        })
    }
}

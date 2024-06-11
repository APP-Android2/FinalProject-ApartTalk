package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteListBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.repository.VoteRepository
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class VoteListFragment : Fragment() {

    private lateinit var binding: FragmentVoteListBinding
    private lateinit var voteActivity: VoteActivity
    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    // UserDataSource 및 LocalUserDataSource 초기화
    private val userDataSource by lazy { UserDataSource() }
    private val localUserDataSource by lazy { LocalUserDataSource(requireActivity().applicationContext) }

    // UserRepository 초기화
    private val userRepository by lazy { UserRepository(userDataSource, localUserDataSource) }

    // VoteRepository 초기화
    private val voteRepository by lazy { VoteRepository(firestore) }

    // 실제 도큐먼트 ID를 전달하도록 수정
    private val voteViewModel: VoteViewModel by viewModels {
        VoteViewModelFactory(voteRepository, userRepository, "ltfkOHDzaur5lHbSxxDp")
    }

    private lateinit var voteListAdapter: VoteListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVoteListBinding.inflate(inflater, container, false)
        voteActivity = activity as VoteActivity

        setupRecyclerView()
        setupObservers()
        setupVoteListButton()

        return binding.root
    }

    private fun setupObservers() {
        voteViewModel.voteItems.observe(viewLifecycleOwner, Observer { voteItems ->
            Log.d("VoteListFragment", "Vote items observed: $voteItems")
            voteItems?.let {
                if (it.isNotEmpty() && it[0].electionName.isNotEmpty()) {
                    binding.voteListCardTextView.text = it[0].electionName
                    binding.voteListCardTextView1.text = it[0].electionDate
                    binding.voteListCardTextView.visibility = View.VISIBLE
                    binding.voteListCardTextView1.visibility = View.VISIBLE
                    binding.voteListButton.visibility = View.VISIBLE
                    binding.noVoteTextView.visibility = View.GONE
                } else {
                    binding.voteListCardTextView.visibility = View.GONE
                    binding.voteListCardTextView1.visibility = View.GONE
                    binding.voteListButton.visibility = View.GONE
                    binding.noVoteTextView.visibility = View.VISIBLE
                }
                voteListAdapter.updateVoteItems(it, voteViewModel.pastVotes.value ?: emptyList())
            }
        })

        voteViewModel.pastVotes.observe(viewLifecycleOwner, Observer { pastVoteItems ->
            Log.d("VoteListFragment", "Past votes observed: $pastVoteItems")
            pastVoteItems?.let {
                voteListAdapter.updateVoteItems(voteViewModel.voteItems.value ?: emptyList(), it)
            }
        })

        voteViewModel.hasOngoingVote.observe(viewLifecycleOwner, Observer { hasOngoingVote ->
            Log.d("VoteListFragment", "Has ongoing vote observed: $hasOngoingVote")
            if (hasOngoingVote) {
                binding.voteListCardTextView.visibility = View.VISIBLE
                binding.voteListCardTextView1.visibility = View.VISIBLE
                binding.voteListButton.visibility = View.VISIBLE
                binding.noVoteTextView.visibility = View.GONE
            } else {
                binding.voteListCardTextView.visibility = View.GONE
                binding.voteListCardTextView1.visibility = View.GONE
                binding.voteListButton.visibility = View.GONE
                binding.noVoteTextView.visibility = View.VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {
        voteListAdapter = VoteListAdapter(voteActivity, emptyList(), emptyList())
        binding.voteListRecyclerView.apply {
            adapter = voteListAdapter
            layoutManager = LinearLayoutManager(voteActivity)
        }
    }

    private fun setupVoteListButton() {
        binding.voteListButton.setOnClickListener {
            voteActivity.removeFragment(VoteFragmentName.VOTE_LIST_FRAGMENT)
            voteActivity.replaceFragment(VoteFragmentName.VOTE_FRAGMENT, false, true, null)
        }
    }
}

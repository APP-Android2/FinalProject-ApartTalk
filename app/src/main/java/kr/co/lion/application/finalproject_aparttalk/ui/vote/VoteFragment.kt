package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentVoteBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.repository.VoteRepository
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class VoteFragment : Fragment() {

    private lateinit var binding: FragmentVoteBinding
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVoteBinding.inflate(inflater, container, false)
        voteActivity = activity as VoteActivity

        voteToolbar()
        voteButton()
        setupCheckBoxes()
        setupObservers()

        // FirebaseAuth를 사용하여 사용자 ID를 가져옵니다.
        val userIdx = firebaseAuth.currentUser?.uid
        if (userIdx != null) {
            voteViewModel.getUserVoteStatus(userIdx)
            voteViewModel.getUser(userIdx)
        } else {
            // 사용자 ID를 가져오지 못한 경우 처리
            // 예를 들어, 로그인 페이지로 이동하거나 오류 메시지를 표시할 수 있습니다.
        }

        return binding.root
    }

    private fun voteToolbar() {
        binding.voteToolbar.apply {
            setNavigationIcon(R.drawable.icon_back)
            setNavigationOnClickListener {
                voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
            }
        }
    }

    private fun voteButton() {
        binding.voteButton.setOnClickListener {
            val userIdx = firebaseAuth.currentUser?.uid
            if (userIdx != null) {
                voteViewModel.userVoteStatus.observe(viewLifecycleOwner, Observer { hasVoted ->
                    if (hasVoted) {
                        showAlreadyVotedDialog()
                    } else {
                        voteViewModel.updateUserVoteStatus(userIdx, true)
                        voteActivity.removeFragment(VoteFragmentName.VOTE_FRAGMENT)
                        voteActivity.replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
                    }
                })
            } else {
                // 사용자 ID를 가져오지 못한 경우 처리
            }
        }
    }

    private fun showAlreadyVotedDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("알림")
        builder.setMessage("이미 투표하셨습니다.")
        builder.setPositiveButton("확인") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun setupCheckBoxes() {
        val checkBox1 = binding.voteCheckBox1
        val checkBox2 = binding.voteCheckBox2
        val checkBox3 = binding.voteCheckBox3

        var isUpdating = false

        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox1.setTextColor(resources.getColor(R.color.black, null))
            } else {
                checkBox1.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }

        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox3.isChecked = false
                checkBox2.setTextColor(resources.getColor(R.color.black, null))
            } else {
                checkBox2.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }

        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdating) return@setOnCheckedChangeListener
            isUpdating = true
            if (isChecked) {
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                checkBox3.setTextColor(resources.getColor(R.color.black, null))
            } else {
                checkBox3.setTextColor(resources.getColor(R.color.gray, null))
            }
            isUpdating = false
        }
    }

    private fun setupObservers() {
        voteViewModel.userVoteStatus.observe(viewLifecycleOwner, Observer { hasVoted ->
            if (hasVoted) {
                binding.voteButton.isEnabled = false
                binding.voteButton.text = "이미 투표하셨습니다"
                binding.voteCheckBox1.isEnabled = false
                binding.voteCheckBox2.isEnabled = false
                binding.voteCheckBox3.isEnabled = false
            }
        })

        voteViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            // 사용자 정보를 사용하여 UI 업데이트 (필요한 경우)
        })

        voteViewModel.voteItems.observe(viewLifecycleOwner, Observer { voteItems ->
            voteItems?.let { items ->
                if (items.isNotEmpty()) {
                    val item = items[0]
                    binding.voteTextView.text = item.electionName
                    binding.voteCheckBox1.text = item.checkBox1Text
                    binding.voteCheckBox2.text = item.checkBox2Text
                    binding.voteCheckBox3.text = item.checkBox3Text
                    binding.voteTextView2.text = item.checkBox1Desc
                    binding.voteTextView4.text = item.checkBox2Desc
                    binding.voteTextView6.text = item.checkBox3Desc
                }
            }
        })
    }
}

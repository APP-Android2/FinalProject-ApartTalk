package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyWroteBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyWroteRecyclerViewAdapter

class MyWroteFragment : Fragment() {

    private lateinit var binding: FragmentMyWroteBinding
    private val myWroteViewModel: MyWroteViewModel by viewModels { MyWroteViewModelFactory(
        CommunityPostRepository()
    ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyWroteBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeViewModel()

        // 전달받은 userId를 사용하여 데이터 로드
        myWroteViewModel.loadMyWrotePosts()

        return binding.root
    }

    private fun observeViewModel() {
        myWroteViewModel.myWroteList.observe(viewLifecycleOwner, Observer { posts ->
            updateUI(posts)
            (binding.recyclerViewTabMyWrote.adapter as MyWroteRecyclerViewAdapter).submitList(posts)
        })
    }

    private fun updateUI(postList: List<PostData>) {
        if (postList.isEmpty()) {
            binding.myWroteLayout.visibility = View.GONE
            binding.myWroteBlankLayout.visibility = View.VISIBLE
        } else {
            binding.myWroteLayout.visibility = View.VISIBLE
            binding.myWroteBlankLayout.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTabMyWrote.apply {
            adapter = MyWroteRecyclerViewAdapter(requireContext(), myWroteViewModel)
            layoutManager = LinearLayoutManager(requireContext())
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }
}
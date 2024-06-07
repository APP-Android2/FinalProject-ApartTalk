package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyLikeBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.repository.CommunityPostRepository
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter

class MyLikeFragment : Fragment() {

    private lateinit var binding: FragmentMyLikeBinding
    private val myLikeViewModel: MyLikeViewModel by viewModels { MyLikeViewModelFactory(
        CommunityPostRepository()
    ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyLikeBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeViewModel()

        myLikeViewModel.loadMyLikedPosts()

        return binding.root
    }

    private fun observeViewModel() {
        myLikeViewModel.myLikeList.observe(viewLifecycleOwner, Observer { posts ->
            updateUI(posts)
            (binding.recyclerViewTabMyLike.adapter as MyLikeRecyclerViewAdapter).submitList(posts)
        })
    }

    private fun updateUI(postList: List<PostData>) {
        if (postList.isEmpty()) {
            binding.myLikeLayout.visibility = View.GONE
            binding.myLikeBlankLayout.visibility = View.VISIBLE
        } else {
            binding.myLikeLayout.visibility = View.VISIBLE
            binding.myLikeBlankLayout.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTabMyLike.apply {
            adapter = MyLikeRecyclerViewAdapter(requireContext(), myLikeViewModel)
            layoutManager = LinearLayoutManager(requireContext())
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }
}
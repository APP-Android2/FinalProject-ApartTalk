package kr.co.lion.application.finalproject_aparttalk.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentHomeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.HomeNotificationRecyclerView

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewHomeNotification()

        return binding.root
    }

    // 홈 공지 리사이클러뷰 설정
    private fun settingRecyclerViewHomeNotification() {
        binding.apply {
            homeNotificationRecyclerView.apply {
                adapter = HomeNotificationRecyclerView(requireContext())
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
    }

}
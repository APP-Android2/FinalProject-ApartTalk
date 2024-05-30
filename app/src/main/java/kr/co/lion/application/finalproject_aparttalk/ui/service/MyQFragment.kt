package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFAQBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyQBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.MyQRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyQFragment : Fragment() {

    lateinit var fragmentMyQBinding: FragmentMyQBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentMyQBinding = FragmentMyQBinding.inflate(inflater)
        serviceActivity = activity as ServiceActivity

//        settingFloatingButton()
        settingRecyclerview()
        settingFabTabMyQAddButton()

        // Inflate the layout for this fragment
        return fragmentMyQBinding.root
    }

    fun settingFabTabMyQAddButton(){
        fragmentMyQBinding.apply {
            myQFloatingActionButton.setOnClickListener{
                serviceActivity.replaceFragment(ServiceFragmentName.SINGLE_SERVICE_FRAGMENT, true, true, null)
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
//    fun settingFloatingButton() {
//        fragmentMyQBinding.apply {
//            recyclerViewMyQ.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                var temp: Int = 0
//
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    if (temp == 1) {
//                        super.onScrolled(recyclerView, dx, dy)
//                    }
//                    myQFloatingActionButton.visibility = View.GONE
//                }
//
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//                    myQFloatingActionButton.visibility = View.VISIBLE
//                    temp = 1
//                }
//            })
//        }
//    }

    fun settingRecyclerview(){
        fragmentMyQBinding.apply{
            recyclerViewMyQ.apply{
                adapter = MyQRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(serviceActivity)
                val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
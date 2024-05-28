package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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

        settingFloatingButton()
        settingRecyclerview()

        // Inflate the layout for this fragment
        return fragmentMyQBinding.root
    }

    fun settingFloatingButton(){
        fragmentMyQBinding.apply {
            myQFloatingActionButton.setOnClickListener{
                serviceActivity.replaceFragment(ServiceFragmentName.SINGLE_SERVICE_FRAGMENT, true, true, null)
            }
        }
    }
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
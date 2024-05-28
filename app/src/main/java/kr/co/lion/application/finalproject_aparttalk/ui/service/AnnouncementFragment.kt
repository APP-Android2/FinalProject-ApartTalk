package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAnnouncementBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter


class AnnouncementFragment : Fragment() {
    lateinit var fragmentAnnouncementBinding: FragmentAnnouncementBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentAnnouncementBinding = FragmentAnnouncementBinding.inflate(inflater)
        serviceActivity = activity as ServiceActivity

        settingRecyclerview()
        // Inflate the layout for this fragment
        return fragmentAnnouncementBinding.root
    }

    fun settingRecyclerview(){
        fragmentAnnouncementBinding.apply{
            recyclerViewAnnouncement.apply{
                adapter = AnnouncementRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(serviceActivity)
                val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
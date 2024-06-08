package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFAQBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyQBinding
import kr.co.lion.application.finalproject_aparttalk.model.ServiceModel
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.AnnouncementRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.MyQRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class MyQFragment : Fragment() {

    lateinit var fragmentMyQBinding: FragmentMyQBinding
    lateinit var serviceActivity: ServiceActivity
    private lateinit var adapter: MyQRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMyQBinding = FragmentMyQBinding.inflate(inflater, container, false)
        serviceActivity = activity as ServiceActivity

        settingRecyclerview()
        settingFabTabMyQAddButton()

        return fragmentMyQBinding.root
    }

    private fun settingFabTabMyQAddButton() {
        fragmentMyQBinding.apply {
            myQFloatingActionButton.setOnClickListener {
                serviceActivity.replaceFragment(ServiceFragmentName.SINGLE_SERVICE_FRAGMENT, true, true, null)
            }
        }
    }

    private fun settingRecyclerview() {
        adapter = MyQRecyclerViewAdapter(requireContext())
        fragmentMyQBinding.recyclerViewMyQ.apply {
            this.adapter = this@MyQFragment.adapter
            layoutManager = LinearLayoutManager(serviceActivity)
            val deco = MaterialDividerItemDecoration(serviceActivity, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }

        // Firestore 데이터 로드 및 어댑터에 데이터 설정
        loadServiceData()
    }

    private fun loadServiceData() {
        val db = FirebaseFirestore.getInstance()
        val serviceCollection = db.collection("services")

        serviceCollection.get().addOnSuccessListener { querySnapshot ->
            val serviceList = querySnapshot.toObjects(ServiceModel::class.java)
            adapter.submitList(serviceList)
        }.addOnFailureListener { exception ->
            // 에러 처리 코드 추가
        }
    }
}
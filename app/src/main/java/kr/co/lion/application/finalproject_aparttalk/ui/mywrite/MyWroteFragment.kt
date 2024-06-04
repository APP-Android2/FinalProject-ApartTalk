package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFAQBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyWroteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyWroteRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.adapter.FAQRecyclerViewAdapter

class MyWroteFragment : Fragment() {

    lateinit var fragmentMyWroteBinding: FragmentMyWroteBinding
    lateinit var myWriteActivity: MyWriteActivity
    val dataList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentMyWroteBinding = FragmentMyWroteBinding.inflate(inflater)
        myWriteActivity = activity as MyWriteActivity

        settingRecyclerview()
        updateUI()

        return fragmentMyWroteBinding.root

    }
    fun updateUI(){
        if(dataList.isEmpty()){
            fragmentMyWroteBinding.myWroteLayout.visibility = View.GONE
            fragmentMyWroteBinding.myWroteBlankLayout.visibility = View.VISIBLE
        }else{
            fragmentMyWroteBinding.myWroteLayout.visibility  = View.VISIBLE
            fragmentMyWroteBinding.myWroteBlankLayout.visibility = View.GONE
        }
    }

    fun settingRecyclerview(){
        fragmentMyWroteBinding.apply{
            recyclerViewTabMyWrote.apply{
                adapter = MyWroteRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(myWriteActivity)
                val deco = MaterialDividerItemDecoration(myWriteActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
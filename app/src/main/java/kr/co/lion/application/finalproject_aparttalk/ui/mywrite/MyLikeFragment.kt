package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyWroteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyWroteRecyclerViewAdapter


class MyLikeFragment : Fragment() {

    lateinit var fragmentMyLikeBinding: FragmentMyLikeBinding
    lateinit var myWriteActivity: MyWriteActivity
    val dataList: MutableList<String> = mutableListOf("Item 1", "Item 2 ", "Item 3")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentMyLikeBinding = FragmentMyLikeBinding.inflate(inflater)
        myWriteActivity = activity as MyWriteActivity

        settingRecyclerview()
        updateUI()

        return fragmentMyLikeBinding.root

    }

    fun updateUI(){
        if(dataList.isEmpty()){
            fragmentMyLikeBinding.myLikeLayout.visibility = View.GONE
            fragmentMyLikeBinding.myLikeBlankLayout.visibility = View.VISIBLE
        }else{
            fragmentMyLikeBinding.myLikeLayout.visibility  = View.VISIBLE
            fragmentMyLikeBinding.myLikeBlankLayout.visibility = View.GONE
        }
    }

    fun settingRecyclerview(){
        fragmentMyLikeBinding.apply{
            recyclerViewTabMyLike.apply{
                adapter = MyLikeRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(myWriteActivity)
                val deco = MaterialDividerItemDecoration(myWriteActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
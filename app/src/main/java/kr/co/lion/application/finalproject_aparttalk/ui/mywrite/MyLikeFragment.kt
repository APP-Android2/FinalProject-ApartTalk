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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentMyLikeBinding = FragmentMyLikeBinding.inflate(inflater)
        myWriteActivity = activity as MyWriteActivity

        settingRecyclerview()

        return fragmentMyLikeBinding.root

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
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationSecondBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.adapter.OperationSecondAdapter
import kr.co.lion.application.finalproject_aparttalk.util.AptOperationInfoFragmentName

class OperationSecondFragment : Fragment() {

    lateinit var fragmentOperationSecondBinding: FragmentOperationSecondBinding
    lateinit var operationInfoActivity: OperationInfoActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentOperationSecondBinding = FragmentOperationSecondBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        setToolbar()
        setRecyclerView()

        return fragmentOperationSecondBinding.root
    }

    fun setToolbar(){
        fragmentOperationSecondBinding.apply {
            toolbarAptOperationInfo.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // OperationFirstFragment 로 돌아가기
                    operationInfoActivity.removeFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT)
                }
            }
        }
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentOperationSecondBinding.apply {
            recyclerOperationInfoList.apply {
                // 어댑터 설정
                adapter = OperationSecondAdapter(childFragmentManager)
                // 레이아웃
                layoutManager = LinearLayoutManager(requireContext())
                // 데코
                val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
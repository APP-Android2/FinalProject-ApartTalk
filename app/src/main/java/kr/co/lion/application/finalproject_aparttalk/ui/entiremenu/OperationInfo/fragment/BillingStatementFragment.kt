package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBillingStatementBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.OperationSecondRecyclerView

class BillingStatementFragment : Fragment() {

    lateinit var fragmentBillingStatementBinding: FragmentBillingStatementBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBillingStatementBinding = FragmentBillingStatementBinding.inflate(layoutInflater)

        setRecyclerView()

        return fragmentBillingStatementBinding.root
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentBillingStatementBinding.recyclerViewBillingStatement.apply {
            // 어댑터 설정
            adapter = OperationSecondRecyclerView(childFragmentManager)
            // 레이아웃
            layoutManager = LinearLayoutManager(requireContext())
            // 데코
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBiddingNoticeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.OperationSecondRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.BiddingNoticeViewModel

class BiddingNoticeFragment : Fragment() {

    lateinit var fragmentBiddingNoticeBinding: FragmentBiddingNoticeBinding
    private val viewModel: BiddingNoticeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBiddingNoticeBinding = FragmentBiddingNoticeBinding.inflate(layoutInflater)

        setRecyclerView()

        return fragmentBiddingNoticeBinding.root
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentBiddingNoticeBinding.recyclerViewBiddingNotice.apply {
            // 어댑터 설정
            adapter = OperationSecondRecyclerView(requireContext(), viewModel.biddingNoticeList)
            // 레이아웃
            layoutManager = LinearLayoutManager(requireContext())
            // 데코
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }

    // 입찰공고 리스트 받아오기
    private fun gettingBiddingNoticeList(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingBiddingNoticeList()
            fragmentBiddingNoticeBinding.recyclerViewBiddingNotice.adapter?.notifyDataSetChanged()
        }
    }
}
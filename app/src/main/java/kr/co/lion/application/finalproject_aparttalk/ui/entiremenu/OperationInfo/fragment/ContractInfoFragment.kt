package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentContractInfoBinding
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.OperationSecondRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.OperationInfoViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.OperationInfoViewModelFactory

class ContractInfoFragment : Fragment() {

    lateinit var fragmentContractInfoBinding : FragmentContractInfoBinding
    private val viewModel: OperationInfoViewModel by viewModels {
        OperationInfoViewModelFactory(OperationInfoRepository(OperationInfoDataSource()), LocalApartmentDataSource(requireContext()))
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentContractInfoBinding = FragmentContractInfoBinding.inflate(layoutInflater)

        setRecyclerView()
        observeViewModel()

        return fragmentContractInfoBinding.root
    }

    // RecyclerView 설정
    fun setRecyclerView(){
        fragmentContractInfoBinding.recyclerViewContractInfo.apply {
            // 어댑터 설정
            adapter = OperationSecondRecyclerView(parentFragmentManager, mutableListOf())
            // 레이아웃
            layoutManager = LinearLayoutManager(requireContext())
            // 데코
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }

    // ViewModel 관찰 설정
    private fun observeViewModel() {
        viewModel.filteredList.observe(viewLifecycleOwner) { list ->
            (fragmentContractInfoBinding.recyclerViewContractInfo.adapter as OperationSecondRecyclerView).updateList(list)
        }

        viewModel.getOperationInfoList()
        viewModel.filterOperationInfoList("ContractInfo")
    }
}
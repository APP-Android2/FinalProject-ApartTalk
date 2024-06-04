package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.BiddingNoticeFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.BillingStatementFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.ContractInfoFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.FinancialSystemFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.ManagementRegulationFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.RepairPlanFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment.SafetyManagementFragment

class OperationViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(
        // 계약서정보 프래그먼트
        ContractInfoFragment(),
        // 관리규약
        ManagementRegulationFragment(),
        // 입찰공고
        BiddingNoticeFragment(),
        // 부과명세서
        BillingStatementFragment(),
        // 재무제표
        FinancialSystemFragment(),
        // 안전관리계획
        SafetyManagementFragment(),
        // 수선계획
        RepairPlanFragment(),
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationSecondBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.LocationAdapter
import kr.co.lion.application.finalproject_aparttalk.util.AptOperationInfoFragmentName

class OperationSecondFragment : Fragment() {

    lateinit var fragmentOperationSecondBinding: FragmentOperationSecondBinding
    lateinit var operationInfoActivity: OperationInfoActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentOperationSecondBinding = FragmentOperationSecondBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        setToolbar()
        initView()

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

    // 탭뷰 설정
    private fun initView(){
        val locationViewPager = fragmentOperationSecondBinding.viewPagerAptOperationInfo
        val locationTabLayout = fragmentOperationSecondBinding.tabLayoutAptOperationInfo

        //Fragment 추가
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(ContractInfoFragment())
        fragmentList.add(ManagementRegulationFragment())
        fragmentList.add(BiddingNoticeFragment())
        fragmentList.add(BillingStatementFragment())
        fragmentList.add(FinancialSystemFragment())
        fragmentList.add(SafetyManagementFragment())
        fragmentList.add(RepairPlanFragment())

        locationViewPager.adapter = LocationAdapter(fragmentList, requireActivity())

        val tabTextList = arrayOf<String?>("계약서정보", "관리규악", "입찰공고", "부과명세서", "재무제표", "안전관리계획", "수선계획")

        // TabLayout ViewPager 연결
        TabLayoutMediator(locationTabLayout, locationViewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

        // 전달된 인덱스로 초기 탭 선택
        val tabPosition = arguments?.getInt("tabPosition", 0) ?: 0
        locationViewPager.currentItem = tabPosition
    }
}
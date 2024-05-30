package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationSecondBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.adapter.OperationSecondRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.adapter.OperationViewPagerAdapter
import kr.co.lion.application.finalproject_aparttalk.util.AptOperationInfoFragmentName

class OperationSecondFragment : Fragment() {

    lateinit var fragmentOperationSecondBinding: FragmentOperationSecondBinding
    lateinit var operationInfoActivity: OperationInfoActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentOperationSecondBinding = FragmentOperationSecondBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        setToolbar()
        setupViewPager()

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

    private fun setupViewPager(){
        val adapter = OperationViewPagerAdapter(requireActivity())
        fragmentOperationSecondBinding.viewPagerAptOperationInfo.adapter = adapter

        TabLayoutMediator(fragmentOperationSecondBinding.tabLayoutAptOperationInfo, fragmentOperationSecondBinding.viewPagerAptOperationInfo) { tab, position ->
            tab.text = when(position){
                0 -> "계약서정보"
                1 -> "관리규악"
                2 -> "입찰공고"
                3 -> "부과명세서"
                4 -> "재무제표"
                5 -> "안전관리계획"
                6 -> "수선계획"
                else -> null
            }
        }.attach()
    }
}
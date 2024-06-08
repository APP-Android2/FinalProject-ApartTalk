package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationFirstBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.util.AptOperationInfoFragmentName

class OperationFirstFragment : Fragment() {

    lateinit var fragmentOperationFirstBinding: FragmentOperationFirstBinding
    lateinit var operationInfoActivity: OperationInfoActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentOperationFirstBinding = FragmentOperationFirstBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        setToolbar()
        setButton()

        return fragmentOperationFirstBinding.root
    }

    fun setToolbar(){
        fragmentOperationFirstBinding.apply {
            toolbarAptOperationInfo.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전체메뉴로 돌아가기
                    operationInfoActivity.finish()
                }
            }
        }
    }

    fun setButton() {
        fragmentOperationFirstBinding.apply {
            // 계약서 정보 button
            layoutOperationInfo1.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle().apply { putInt("selectedTab", 0) }
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 관리규악 button
            layoutOperationInfo2.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 1)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 입찰공고 button
            layoutOperationInfo3.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 2)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 부과명세서 button
            layoutOperationInfo4.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 3)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 제무제표 button
            layoutOperationInfo5.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 4)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 안전관리계획 button
            layoutOperationInfo6.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 5)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
            // 수선계획 button
            layoutOperationInfo7.setOnClickListener {
                // 각 버튼 클릭 이벤트에 선택된 탭 인덱스를 bundle에 담아 전달한다.
                val bundle = Bundle()
                bundle.putInt("tabPosition", 6)
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, bundle)
            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationFirstBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.OperationInfoActivity
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
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 관리규악 button
            layoutOperationInfo2.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 입찰공고 button
            layoutOperationInfo3.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 부과명세서 button
            layoutOperationInfo4.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 제무제표 button
            layoutOperationInfo5.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 안전관리계획 button
            layoutOperationInfo6.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
            // 수선계획 button
            layoutOperationInfo7.setOnClickListener {
                // OperationSecondFragment 화면을 띄운다
                operationInfoActivity.replaceFragment(AptOperationInfoFragmentName.OPERATION_SECOND_FRAGMENT, true, false, null)
            }
        }
    }
}
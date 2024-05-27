package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationFirstBinding

class OperationFirstFragment : Fragment() {

    lateinit var fragmentOperationFirstBinding: FragmentOperationFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentOperationFirstBinding = FragmentOperationFirstBinding.inflate(layoutInflater)

        setToolbar()

        return fragmentOperationFirstBinding.root
    }

    fun setToolbar(){
        fragmentOperationFirstBinding.apply {
            toolbarAptOperationInfo.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전체메뉴로 돌아가기
                }
            }
        }
    }
}
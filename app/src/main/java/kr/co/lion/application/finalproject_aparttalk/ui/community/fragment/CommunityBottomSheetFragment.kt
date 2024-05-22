package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityBottomSheetBinding
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityBottomSheetFragment(var communityDetailFragment: CommunityDetailFragment, communityIdx:Int) : BottomSheetDialogFragment() {
    lateinit var fragmentCommunityBottomSheetBinding: FragmentCommunityBottomSheetBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityBottomSheetBinding = FragmentCommunityBottomSheetBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingCommunityBottom()

        return fragmentCommunityBottomSheetBinding.root
    }

    // 수정하기 / 삭제하기
    private fun settingCommunityBottom() {
        fragmentCommunityBottomSheetBinding.apply {
            // 수정
            buttonCommunityDetailModify.setOnClickListener {
                dismiss()
                val bundle = Bundle()
                // bundle.putInt("communityIdx", communityIdx) 게시글 아이디 넘겨주기
                communityActivity.replaceFragment(CommunityFragmentName.COMMUNITY_MODIFY_FRAGMENT, true, false, null)
            }

            // 삭제
            buttonCommunityDetailDelete.setOnClickListener {

            }
        }
    }

    // 다이얼로그가 만들어질 때 자동으로 호출되는 메서드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그를 받는다.
        val dialog = super.onCreateDialog(savedInstanceState)
        // 다이얼로그가 보일때 동작하는 리스너
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            // 높이를 설정한다.
            settingBottomSheetHeight(bottomSheetDialog)
        }

        return dialog
    }

    // BottomSheet의 높이를 설정해준다.
    private fun settingBottomSheetHeight(bottomSheetDialog: BottomSheetDialog){
        // BottomSheet의 기본 뷰 객체를 가져온다
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        // BottomSheet 높이를 설정할 수 있는 객체를 생성한다.
        val behavior = BottomSheetBehavior.from(bottomSheet)
        // 높이를 설정한다.
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

}
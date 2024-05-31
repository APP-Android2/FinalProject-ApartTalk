package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireExtinguisher3BottomSheetBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity

class FireExtinguisher3BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentFireExtinguisher3BottomSheetBinding
    lateinit var fireCheckActivity: FireCheckActivity
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentFireExtinguisher3BottomSheetBinding.inflate(layoutInflater)
        fireCheckActivity = activity as FireCheckActivity

        return binding.root

        return inflater.inflate(R.layout.fragment_fire_extinguisher3_bottom_sheet, container, false)
    }

    // 다이얼로그가 만들어질 때 자동으로 호출되는 메서드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그를 받는다.
        val dialog = super.onCreateDialog(savedInstanceState)
        // 다이얼로그가 보일때 동작하는 리스너
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            // 높이를 설정한다.
            setBottomSheetHeight(bottomSheetDialog)
        }

        return dialog
    }

    // BottomSheet의 높이를 설정해준다.
    fun setBottomSheetHeight(bottomSheetDialog: BottomSheetDialog){
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
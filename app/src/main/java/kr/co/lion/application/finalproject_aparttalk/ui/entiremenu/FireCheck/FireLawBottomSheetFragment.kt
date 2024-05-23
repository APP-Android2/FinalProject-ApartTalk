package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireLawBottomSheetBinding

class FireLawBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentFireLawBottomSheetBinding
    lateinit var fireCheckActivity: FireCheckActivity
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentFireLawBottomSheetBinding.inflate(layoutInflater)
        fireCheckActivity = activity as FireCheckActivity

        return binding.root
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

        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        // 화면 높이의 2/3 크기로 설정
        val screenHeight = resources.displayMetrics.heightPixels
        val desiredHeight = (screenHeight * 0.66).toInt()

        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = desiredHeight
        bottomSheet.layoutParams = layoutParams

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
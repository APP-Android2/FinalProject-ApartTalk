package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentOperationInfoShowBottomSheetBinding
import kr.co.lion.application.finalproject_aparttalk.db.OperationInfoDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.OperationInfoRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.OperationInfoViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.OperationInfoViewModelFactory

class OperationInfoShowBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentOperationInfoShowBottomSheetBinding
    private val viewModel: OperationInfoViewModel by viewModels {
        OperationInfoViewModelFactory(
            OperationInfoRepository(OperationInfoDataSource()),
            LocalApartmentDataSource(requireContext())
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentOperationInfoShowBottomSheetBinding.inflate(layoutInflater)

        // OperationSecondRecyclerView 에서 부터 전달받은 데이터로 UI를 업데이트
        arguments?.let {
            val operationInfoIdx = it.getInt("idx")
            val operationInfoWriter = it.getString("writer")
            val operationInfoType = it.getString("type")
            val operationInfoSubject = it.getString("subject")
            val operationInfoDate = it.getString("date")
            val operationContent = it.getString("content")


            binding.textViewOperationInfoShowSubject.text = operationInfoSubject
            binding.textViewOperationInfoShowContent.text = operationContent!!.replace("\\n", "\n")
        }

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
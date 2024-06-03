package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireExtinguisher1BottomSheetBinding
import kr.co.lion.application.finalproject_aparttalk.repository.FireCheckRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.viewmodel.FireCheckViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.viewmodel.FireCheckViewModelFactory

class FireExtinguisher1BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFireExtinguisher1BottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var fireCheckActivity: FireCheckActivity
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val fireCheckViewModel: FireCheckViewModel by activityViewModels{
        FireCheckViewModelFactory(FireCheckRepository())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFireExtinguisher1BottomSheetBinding.inflate(layoutInflater)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireCheckViewModel.fireCheckData.observe(viewLifecycleOwner) { data ->
            // 데이터 리스트가 비어있지 않은 경우
            if(data.isNotEmpty()) {
                //  첫 번째 항목을 가져옴 (해당 프래그먼트의 경우, 여러개의 데이터가 있지 않음)
                val fireCheckViewModel = data[0]
                binding.textViewFireExtinguisher1Mean.text = fireCheckViewModel.FireCheckExtinguisher1Mean.replace("\\n", "\n")
                binding.textViewFireExtinguisher1Content.text = fireCheckViewModel.FireCheckExtinguisher1Content.replace("\\n", "\n")
            }
        }
        fireCheckViewModel.fetchFireCheckData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
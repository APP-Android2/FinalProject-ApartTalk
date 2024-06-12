package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentAptScheduleShowBottomSheetBinding

class AptScheduleShowBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAptScheduleShowBottomSheetBinding
    lateinit var aptScheduleActivity: AptScheduleActivity
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var aptScheduleType: String // aptScheduleType 변수를 클래스 레벨에서 선언
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentAptScheduleShowBottomSheetBinding.inflate(layoutInflater)
        aptScheduleActivity = activity as AptScheduleActivity

        // OperationSecondRecyclerView 에서 부터 전달받은 데이터로 UI를 업데이트
        arguments?.let {
            val aptScheduleDate = it.getString("date")
            val aptScheduleTime = it.getString("time")
            val aptScheduleSubject = it.getString("subject")
            val aptScheduleContent = it.getString("content")
            aptScheduleType = it.getString("type").toString()

            binding.textViewAptScheduleShowDate.text = aptScheduleDate
            binding.textViewAptScheduleShowTime.text = aptScheduleTime
            binding.textViewAptScheduleShowSubject.text = aptScheduleSubject
            binding.textViewAptScheduleShowContent.text = aptScheduleContent!!.replace("\\n", "\n")
        }

        // aptScheduleType에 따라 이미지 설정
        val imageResource = when (aptScheduleType) {
            "가스점검" -> R.drawable.icon_donut1
            "소독" -> R.drawable.icon_donut2
            "엘레베이터 점검" -> R.drawable.icon_donut3
            "알뜰장" -> R.drawable.icon_donut4
            "쓰레기 수거" -> R.drawable.icon_donut5
            "관리비 공개" -> R.drawable.icon_donut6
            else -> R.drawable.icon_donut1 // 기본 이미지 설정
        }
       binding.imageViewAptBottom.setImageResource(imageResource)

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
package kr.co.lion.application.finalproject_aparttalk.util

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.DialogConfirmCancelBinding

class DialogConfirmCancel(
    dialogConfirmCancelInterface: DialogConfirmCancelInterface,
    subject: String?,
    content: String,
    activity: AppCompatActivity,
    position: Int? = null, // 리사이클러뷰 삭제할 때 아이템 포지션
    ) : DialogFragment() {

    lateinit var dialogConfirmCancelBinding: DialogConfirmCancelBinding

    var subject = subject
    var content = content
    var activity = activity
    var dialogConfirmCancelInterface = dialogConfirmCancelInterface
    var position = position

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogConfirmCancelBinding = DialogConfirmCancelBinding.inflate(layoutInflater)

        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        settingDialog()

        return dialogConfirmCancelBinding.root
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = gettingDeviceSize()
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    // 버튼 처리
    private fun settingDialog() {
        dialogConfirmCancelBinding.apply {

            // 다이얼로그 제목
            if (subject == null) {
                textViewDialogConfirmCancelSubject.isVisible = false
            } else {
                textViewDialogConfirmCancelSubject.text = subject
            }

            // 다이얼로그 내용
            textViewDialogConfirmCancelContent.text = content

            // 확인 버튼
            buttonDialogConfirmCancelConfirm.setOnClickListener {
                if(position != null){
                    // 삭제 확인 버튼
                    dialogConfirmCancelInterface.onConfirmButtonClick(position!!)
                    dismiss()
                }else{
                    // 그 외 확인 버튼
                    dismiss()
                    dialogConfirmCancelInterface.onConfirmButtonClick(activity)
                }

            }

            // 취소 버튼
            buttonDialogConfirmCancelCancel.setOnClickListener {
                dismiss()
            }
        }
    }


    // 디바이스 크기 구하기
    private fun gettingDeviceSize() : Int {
        val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        return size.x
    }
}

interface DialogConfirmCancelInterface {
    fun onConfirmButtonClick(id: Int) // 삭제 확인 버튼

    fun onConfirmButtonClick(activity: AppCompatActivity) // 그 외 확인 버튼
}
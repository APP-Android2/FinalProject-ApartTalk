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
import androidx.fragment.app.DialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.DialogConfirmBinding

class DialogConfirm(
    subject: String?,
    content: String,
    activity: AppCompatActivity
) : DialogFragment() {

    lateinit var dialogConfirmBinding: DialogConfirmBinding

    private lateinit var buttonClickListener: OnButtonClickListener

    var subject = subject
    var content = content
    var activity = activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialogConfirmBinding = DialogConfirmBinding.inflate(layoutInflater)

        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        settingDialog()

        return dialogConfirmBinding.root
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
        dialogConfirmBinding.apply {
            // 다이얼로그 제목
            textViewDialogConfirmSubject.text = subject

            // 다이얼로그 내용
            textViewDialogConfirmContent.text = content

            // 확인 버튼
            // 바텀 시트 닫기 / 게시글 등록 / 게시글 수정 등 처리 했다라는 걸 알려주는 용도로만 사용하는 것은 어떨까요?
            buttonDialogConfirm.setOnClickListener {
                buttonClickListener.okButtonClick()
                dismiss()
            }

        }
    }

    interface OnButtonClickListener{
        fun okButtonClick()
    }

    fun setDialogButtonClickListener(buttonClickListener: OnButtonClickListener){
        this.buttonClickListener = buttonClickListener
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
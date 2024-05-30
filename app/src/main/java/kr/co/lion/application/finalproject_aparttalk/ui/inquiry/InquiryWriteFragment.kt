package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.app.AlertDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.DialogConfirmCancelBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryWriteBinding
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryWriteFragment : Fragment() {

    lateinit var fragmentInquiryWriteBinding: FragmentInquiryWriteBinding
    lateinit var inquiryActivity: InquiryActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentInquiryWriteBinding = FragmentInquiryWriteBinding.inflate(inflater)
        inquiryActivity = activity as InquiryActivity

        // 소프트 입력 모드 설정
        inquiryActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        inquiryWriteToolbar()
        setupButtons()

        return fragmentInquiryWriteBinding.root
    }

    fun inquiryWriteToolbar(){
        fragmentInquiryWriteBinding.apply {
            inquiryWriteToolbar.apply {
                //title
                title = "문의글 작성"
                // back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    inquiryActivity.removeFragment(InquiryFragmentName.INQUIRY_WRITE_FRAGMENT)
                    inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT,false,true,null)
                }
            }
        }
    }


    fun setupButtons() {
        fragmentInquiryWriteBinding.apply {
            inquiryWriteButton.setOnClickListener {
                // 공개 버튼을 눌렀을 때 비공개 버튼의 선택을 해제
                inquiryWriteButtonPrivate.isSelected = false
                inquiryWriteButton.setBackgroundResource(R.drawable.button_setting) // 선택된 버튼의 배경 변경
                inquiryWriteButtonPrivate.setBackgroundResource(R.color.white) // 선택되지 않은 버튼의 배경 변경

                // 글씨 색상 변경
                inquiryWriteButton.setTextColor(resources.getColor(R.color.black, null)) // 선택된 버튼의 글씨 색상 변경
                inquiryWriteButtonPrivate.setTextColor(resources.getColor(R.color.gray, null)) // 선택되지 않은 버튼의 글씨 색상 변경
            }

            inquiryWriteButtonPrivate.setOnClickListener {
                // 비공개 버튼을 눌렀을 때 공개 버튼의 선택을 해제
                inquiryWriteButton.isSelected = false
                inquiryWriteButtonPrivate.setBackgroundResource(R.drawable.button_setting) // 선택된 버튼의 배경 변경
                inquiryWriteButton.setBackgroundResource(R.color.white) // 선택되지 않은 버튼의 배경 변경

                // 글씨 색상 변경
                inquiryWriteButtonPrivate.setTextColor(resources.getColor(R.color.black, null)) // 선택된 버튼의 글씨 색상 변경
                inquiryWriteButton.setTextColor(resources.getColor(R.color.gray, null)) // 선택되지 않은 버튼의 글씨 색상 변경
            }

            inquiryWriteButtonSubmit.setOnClickListener {
                showConfirmCancelDialog()
            }
        }
    }

    // 다이얼로그
    fun showConfirmCancelDialog() {
        val dialogBinding = DialogConfirmCancelBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()

        dialogBinding.apply {
            textViewDialogConfirmCancelSubject.text = "문의글 작성완료?"
            textViewDialogConfirmCancelContent.text = "입력하신 문의 내용을 제출하시겠습니까?"

            buttonDialogConfirmCancelCancel.setOnClickListener {
                dialog.dismiss() // 다이얼로그 닫기
            }

            buttonDialogConfirmCancelConfirm.setOnClickListener {
                // 확인 버튼 클릭 시 InquiryTabFragment로 이동
                dialog.dismiss()
                inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT, false, true, null)
            }
        }

        dialog.show()
    }


}
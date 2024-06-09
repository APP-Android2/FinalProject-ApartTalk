package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryBinding
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName
import java.text.SimpleDateFormat
import java.util.*

class InquiryFragment : Fragment() {

    private lateinit var fragmentInquiryBinding: FragmentInquiryBinding
    private lateinit var inquiryActivity: InquiryActivity
    private val inquiryViewModel: InquiryViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInquiryBinding = FragmentInquiryBinding.inflate(inflater)
        inquiryActivity = activity as InquiryActivity

        inquiryToolbar()
        observeInquiryModel()
        handleOnBackPressed()

        return fragmentInquiryBinding.root
    }

    private fun inquiryToolbar() {
        fragmentInquiryBinding.apply {
            toolbarInquiry.apply {
                //title
                title = "관리사무소 문의"
                // back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT, false, true, null)
                }
            }
        }
    }

    // InquiryModel 데이터를 UI에 표시하는 메서드
    private fun displayInquiryData(inquiry: InquiryModel) {
        fragmentInquiryBinding.apply {
            inquiryTextViewTitle.text = inquiry.inquiryTitle
            inquiryTextViewContent.text = inquiry.inquiryContent
            inquiryTextViewName.text = inquiry.userName

            // 날짜와 시간 포맷팅
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(inquiry.timestamp)
            inquiryTextViewDate.text = dateFormat.format(date)
            inquiryTextViewTime.text = timeFormat.format(date)
        }
    }

    // ViewModel을 통해 InquiryModel을 관찰하는 메서드
    private fun observeInquiryModel() {
        inquiryViewModel.selectedInquiryModel.observe(viewLifecycleOwner) { inquiryModel ->
            inquiryModel?.let {
                displayInquiryData(it)
            }
        }
    }

    // 스마트폰의 뒤로 가기 버튼을 처리하는 메서드
    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로 가기 버튼이 눌렸을 때의 동작을 여기에 작성
                inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT, false, true, null)
            }
        })
    }
}

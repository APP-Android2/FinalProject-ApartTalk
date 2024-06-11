package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryWriteBinding
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryWriteFragment : Fragment() {

    private lateinit var binding: FragmentInquiryWriteBinding
    private val inquiryViewModel: InquiryViewModel by activityViewModels()
    private lateinit var inquiryActivity: InquiryActivity
    private var isPrivate = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInquiryWriteBinding.inflate(inflater, container, false)
        inquiryActivity = activity as InquiryActivity

        setToolbar()
        setupPrivacyButtons()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inquiryViewModel.userModel.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.inquiryWriteButtonSubmit.setOnClickListener {
                    val title = binding.inquiryWriteEditTextTitle.text.toString()
                    val content = binding.inquiryWriteEditTextContent.text.toString()

                    val inquiry = InquiryModel(
                        inquiryTitle = title,
                        inquiryContent = content,
                        inquiryPrivate = isPrivate,
                        apartmentUid = user.apartmentUid,
                        userIdx = user.idx.toString(),
                        userName = user.name
                    )

                    // ViewModel을 통해 InquiryModel을 설정 및 저장
                    inquiryViewModel.createInquiry(inquiry)
                    inquiryViewModel.setSelectedInquiryModel(inquiry)

                    // InquiringTabFragment로 돌아가도록 설정
                    (activity as InquiryActivity).replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT, false, true, null)
                }
            }
        }
    }

    private fun setupPrivacyButtons() {
        binding.inquiryWriteButton.setOnClickListener {
            isPrivate = false
            binding.inquiryWriteButton.setTextColor(resources.getColor(R.color.third, null))
            binding.inquiryWriteButtonPrivate.setTextColor(resources.getColor(R.color.gray, null))
        }

        binding.inquiryWriteButtonPrivate.setOnClickListener {
            isPrivate = true
            binding.inquiryWriteButton.setTextColor(resources.getColor(R.color.gray, null))
            binding.inquiryWriteButtonPrivate.setTextColor(resources.getColor(R.color.third, null))
        }
    }

    private fun setToolbar(){
        binding.apply {
            binding.inquiryWriteToolbar.apply {
                title = "문의 작성"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT,false,true,null)
                }
            }
        }
    }
}

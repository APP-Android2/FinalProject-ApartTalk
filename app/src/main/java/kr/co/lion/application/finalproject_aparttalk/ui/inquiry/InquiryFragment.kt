package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryBinding
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryFragment : Fragment() {

    lateinit var fragmentInquiryBinding: FragmentInquiryBinding
    lateinit var inquiryActivity: InquiryActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInquiryBinding = FragmentInquiryBinding.inflate(inflater)
        inquiryActivity = activity as InquiryActivity

        inquiryToolbar()

        return fragmentInquiryBinding.root
    }

    fun inquiryToolbar() {
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

}
package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityModifyBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityModifyImageBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityModifyImageViewPager2Adapter
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm

class CommunityModifyFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityModifyBinding: FragmentCommunityModifyBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityModifyBinding = FragmentCommunityModifyBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingToolbar()
        settingViewPager2CommunityModifyImage()
        settingData()
        settingButtonCommunityAdd()

        return fragmentCommunityModifyBinding.root
    }

    // 툴바
    private fun settingToolbar() {
        fragmentCommunityModifyBinding.apply {
            toolbarCommunityModify.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    communityActivity.removeFragment(CommunityFragmentName.COMMUNITY_MODIFY_FRAGMENT)
                }
            }
        }
    }

    // 커뮤니티 글 수정 뷰페이저 설정
    private fun settingViewPager2CommunityModifyImage() {
        fragmentCommunityModifyBinding.apply {
            viewPager2CommunityModifyImage.apply {
                adapter = CommunityModifyImageViewPager2Adapter(requireContext())
            }

            circleIndicatorCommunityModify.setViewPager(viewPager2CommunityModifyImage)
            viewPager2CommunityModifyImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityModify.adapterDataObserver
            )
        }
    }

    // 데이터 설정
    private fun settingData() {
        fragmentCommunityModifyBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_community)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_community_add, typeArray)
            textViewCommunityModifyType.setAdapter(typeArrayAdapter)
        }
    }

    // 등록 버튼 활성화 / 비활성화
    private fun settingButtonCommunityAdd() {
        fragmentCommunityModifyBinding.apply {

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val isTitleFilled = textViewCommunityModifySubject.text?.isNotBlank() ?: false
                    val isContentFilled = textViewCommunityModifyContent.text?.isNotBlank() ?: false

                    val isButtonEnabled = isTitleFilled && isContentFilled

                    buttonCommunityModify.isEnabled = isButtonEnabled

                    if (isButtonEnabled) {
                        // 활성 상태일 때
                        buttonCommunityModify.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        buttonCommunityModify.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    } else {
                        // 비활성 상태일 때
                        buttonCommunityModify.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                        // stroke 설정
                        buttonCommunityModify.strokeWidth = 1
                        buttonCommunityModify.strokeColor =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        // 텍스트 색상 설정
                        buttonCommunityModify.setTextColor(
                            ContextCompat.getColor(requireContext(), R.color.third)
                        )
                    }
                }
            }

            textViewCommunityModifySubject.addTextChangedListener(textWatcher)
            textViewCommunityModifyContent.addTextChangedListener(textWatcher)

            buttonCommunityModify.setOnClickListener {
                val dialog = DialogConfirm(
                    "게시글 수정 완료",
                    "게시글 수정이 완료되었습니다.\n확인하러 가시겠습니까?",
                    communityActivity
                )
                dialog.show(communityActivity.supportFragmentManager, "DialogConfirm")

            }
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.community

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityAddBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityAddImageBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityDetailImageBinding

class CommunityAddFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityAddBinding: FragmentCommunityAddBinding
    lateinit var communityActivity: CommunityActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityAddBinding = FragmentCommunityAddBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingToolbar()
        settingViewPager2CommunityAddImage()
        settingData()
        settingButtonCommunityAdd()

        return fragmentCommunityAddBinding.root
    }

    // 툴바
    private fun settingToolbar() {
        fragmentCommunityAddBinding.apply {
            toolbarCommunityAdd.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    communityActivity.finish()
                }
            }
        }
    }

    // 커뮤니티 글 작성 뷰페이저 설정
    private fun settingViewPager2CommunityAddImage() {
        fragmentCommunityAddBinding.apply {
            viewPager2CommunityAddImage.apply {
                adapter = CommunityAddImageViewPager2Adapter()
            }

            circleIndicatorCommunityAdd.setViewPager(viewPager2CommunityAddImage)
            viewPager2CommunityAddImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityAdd.adapterDataObserver
            )
        }
    }

    // 커뮤니티 글 작성 뷰페이저 어댑터
    inner class CommunityAddImageViewPager2Adapter : RecyclerView.Adapter<CommunityAddImageViewPager2Adapter.CommunityAddImageViewHolder>(){
        inner class CommunityAddImageViewHolder(rowCommunityAddImageBinding: RowCommunityAddImageBinding) : RecyclerView.ViewHolder(rowCommunityAddImageBinding.root) {
            val rowCommunityAddImageBinding: RowCommunityAddImageBinding

            init {
                this.rowCommunityAddImageBinding = rowCommunityAddImageBinding

                this.rowCommunityAddImageBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityAddImageViewHolder {
            val rowCommunityAddImageBinding = RowCommunityAddImageBinding.inflate(layoutInflater)
            val communityAddImageViewHolder = CommunityAddImageViewHolder(rowCommunityAddImageBinding)

            return communityAddImageViewHolder
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: CommunityAddImageViewHolder, position: Int) {
            holder.rowCommunityAddImageBinding.imageViewRowCommunityAdd.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    // 데이터 설정
    private fun settingData() {
        fragmentCommunityAddBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_community)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_community_add, typeArray)
            textViewCommunityAddType.setAdapter(typeArrayAdapter)
        }
    }

    // 등록 버튼 활성화 / 비활성화
    private fun settingButtonCommunityAdd() {
        fragmentCommunityAddBinding.apply {

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val isTitleFilled = textViewCommunityAddSubject.text?.isNotBlank() ?: false
                    val isContentFilled = textViewCommunityAddContent.text?.isNotBlank() ?: false

                    val isButtonEnabled = isTitleFilled && isContentFilled

                    buttonCommunityAdd.isEnabled = isButtonEnabled

                    if (isButtonEnabled) {
                        // 활성 상태일 때
                        buttonCommunityAdd.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        buttonCommunityAdd.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    } else {
                        // 비활성 상태일 때
                        buttonCommunityAdd.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                        // stroke 설정
                        buttonCommunityAdd.strokeWidth = 1
                        buttonCommunityAdd.strokeColor =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        // 텍스트 색상 설정
                        buttonCommunityAdd.setTextColor(
                            ContextCompat.getColor(requireContext(), R.color.third)
                        )
                    }
                }
            }

            textViewCommunityAddSubject.addTextChangedListener(textWatcher)
            textViewCommunityAddContent.addTextChangedListener(textWatcher)

            buttonCommunityAdd.setOnClickListener {


            }
        }
    }

}
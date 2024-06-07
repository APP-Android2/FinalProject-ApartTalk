package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityModifyBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityModifyImageBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityModifyImageViewPager2Adapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityAddViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityModifyViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.PostState
import kr.co.lion.application.finalproject_aparttalk.util.PostType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class CommunityModifyFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityModifyBinding: FragmentCommunityModifyBinding
    lateinit var communityActivity: CommunityActivity
    private val viewModel: CommunityModifyViewModel by viewModels()

    var postData: PostData? = null
    // 현재 글 번호를 담을 변수
    var postIdx: Int? = null
    // 현재 글 번호를 담을 변수
    var postId: String? = null
    // 현재 글이 담긴 아파트 아이디
    var postApartId: String? = null

    var imageCommunityModifyList = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityModifyBinding = FragmentCommunityModifyBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        postIdx = arguments?.getInt("postIdx")!!
        postId = arguments?.getString("postId")!!
        postApartId = arguments?.getString("postApartId")!!

        settingToolbar()
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
                adapter = CommunityModifyImageViewPager2Adapter(requireContext(), this@CommunityModifyFragment, viewModel)
            }

            circleIndicatorCommunityModify.setViewPager(viewPager2CommunityModifyImage)
            viewPager2CommunityModifyImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityModify.adapterDataObserver
            )
        }
    }

    // 드롭다운 설정
    fun settingTextInputLayoutCommunityModifyType() {
        fragmentCommunityModifyBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_community)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_community_add, typeArray)
            textViewCommunityModifyType.setAdapter(typeArrayAdapter)
        }
    }

    // 데이터 설정
    private fun settingData() {
        fragmentCommunityModifyBinding.apply {
            viewModel.initializeSubject()
            viewModel.initializeContent()

            CoroutineScope(Dispatchers.Main).launch {
                val postData = viewModel.selectCommunityPostData(postApartId!!, postId!!)

                if (postData?.postImages != null) {
                    imageCommunityModifyList = postData?.postImages!!
                } else {
                    viewPager2CommunityModifyImage.visibility = View.GONE
                    circleIndicatorCommunityModify.visibility = View.GONE
                }

                textViewCommunityModifyType.setText(postData?.postType)
                textViewCommunityModifySubject.setText(postData?.postTitle)
                textViewCommunityModifyContent.setText(postData?.postContent)

                settingTextInputLayoutCommunityModifyType()
                settingViewPager2CommunityModifyImage()

            }
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

                CoroutineScope(Dispatchers.Main).launch {
                    postData = generatingPostObject()
                    updateCommunityPostData(postApartId!!, postIdx!!)

                    val dialog = DialogConfirm(
                        "게시글 수정 완료",
                        "게시글 수정이 완료되었습니다.\n확인하러 가시겠습니까?",
                        communityActivity
                    )
                    dialog.setDialogButtonClickListener(object : DialogConfirm.OnButtonClickListener{
                        override fun okButtonClick() {
                            dialog.dismiss()
                            communityActivity.finish()
                        }

                    })
                    dialog.show(communityActivity.supportFragmentManager, "DialogConfirm")
                }
            }
        }
    }

    // 게시글 수정 객체 생성
    suspend fun generatingPostObject() : PostData {
        fragmentCommunityModifyBinding.apply {

            var postDataModify = PostData()

            val job1 = CoroutineScope(Dispatchers.Main).launch {

                val postData = viewModel.selectCommunityPostData(postApartId!!, postId!!)

                postDataModify.postId = postData!!.postId
                postDataModify.postIdx = postData.postIdx
                if (textViewCommunityModifyType.text.toString() == "질문") {
                    postDataModify.postType = PostType.TYPE_QUESTION.str
                } else if (textViewCommunityModifyType.text.toString() == "거래") {
                    postDataModify.postType = PostType.TYPE_TRADE.str
                } else {
                    postDataModify.postType = PostType.TYPE_ETC.str
                }
                postDataModify.postTitle = textViewCommunityModifySubject.text.toString()
                postDataModify.postContent = textViewCommunityModifyContent.text.toString()
                postDataModify.postLikeCnt = postData.postLikeCnt
                postDataModify.postCommentCnt = postData.postCommentCnt
                postDataModify.postImages = postData.postImages
                postDataModify.postAddDate = postData.postAddDate
                postDataModify.postUserId = postData.postUserId
                postDataModify.postApartId = postData.postApartId
                val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
                postDataModify.postModifyDate = simpleDateFormat.format(Date())
                postDataModify.postState = PostState.POST_STATE_MODIFY.number
            }
            job1.join()

            return postDataModify
        }
    }

    // 게시글 수정 작성
    private fun updateCommunityPostData(postApartId: String, postIdx: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.updateCommunityPostData(postData!!)
            viewModel.updateCommunityPostState(postApartId, postIdx, PostState.POST_STATE_MODIFY)
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityAddBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityAddImageViewPager2Adapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityAddViewModel
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.PostState
import kr.co.lion.application.finalproject_aparttalk.util.PostType
import kr.co.lion.application.finalproject_aparttalk.util.Tools
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class CommunityAddFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityAddBinding: FragmentCommunityAddBinding
    lateinit var communityActivity: CommunityActivity
    private val viewModel: CommunityAddViewModel by viewModels()

    var postData:PostData? = null

    var imageCommunityAddUriList = mutableListOf<Uri>()
    var imageCommunityAddBitmapList = mutableListOf<Bitmap>()
    var imageUploadPossible = 10
    var isAddImage = false
    lateinit var albumLauncher: ActivityResultLauncher<Intent>

    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityAddBinding = FragmentCommunityAddBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        settingToolbar()
        settingInputForm()
        settingAlbumLauncher(imageUploadPossible)
        settingViewPager2CommunityAddImage()
        settingButtonCommunityAdd()

        // 권한 확인
        requestPermissions(permissionList, 0)

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

    // 뷰페이저 업데이트
    fun updateViewPager2CommunityAddImage() {
        fragmentCommunityAddBinding.viewPager2CommunityAddImage.adapter?.notifyDataSetChanged()
    }

    // 커뮤니티 글 작성 뷰페이저 설정
    private fun settingViewPager2CommunityAddImage() {
        fragmentCommunityAddBinding.apply {
            viewPager2CommunityAddImage.apply {
                imageCommunityAddBitmapList.add(getBitmapFromDrawable())
                adapter = CommunityAddImageViewPager2Adapter(requireContext(), imageCommunityAddBitmapList, this@CommunityAddFragment)
            }

            circleIndicatorCommunityAdd.setViewPager(viewPager2CommunityAddImage)
            viewPager2CommunityAddImage.adapter?.registerAdapterDataObserver(
                circleIndicatorCommunityAdd.adapterDataObserver
            )
        }
    }

    // Bitmap으로 반환
    fun getBitmapFromDrawable(): Bitmap {
        val drawable = resources.getDrawable(R.drawable.community_add_default)
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)

        return bitmap
    }

    // 앨범 런처를 실행하는 메서드
    fun startAlbumLauncher() {
        // 앨범에서 사진을 선택할 수 있도록 셋팅된 인텐트를 생성한다.
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 실행할 액티비티의 타입을 설정(이미지를 선택할 수 있는 것이 뜨게 한다)
        albumIntent.setType("image/*")

        // 이미지 여러개 선택 가능
        albumIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        // 액티비티를 실행한다.
        albumLauncher.launch(albumIntent)
    }

    // 앨범 런처 설정
    fun settingAlbumLauncher(imageUploadPossible: Int) {
        // 앨범 실행을 위한 런처
        val contract2 = ActivityResultContracts.StartActivityForResult()
        albumLauncher = registerForActivityResult(contract2) {
            // 사진 선택을 완료한 후 돌아왔다면
            if (it.resultCode == Activity.RESULT_OK) {
                // 선택한 이미지의 경로 데이터를 관리하는 Uri 객체 리스트를 추출한다.
                val uriclip = it.data?.clipData
                uriclip?.let { clipData ->
                    for (i in 0 until uriclip!!.itemCount) {
                        if (imageCommunityAddBitmapList.size > imageUploadPossible + 1) {
                            Snackbar.make(fragmentCommunityAddBinding.root, "사진 첨부는 최대 10장까지 가능합니다.", Snackbar.LENGTH_SHORT).show()
                            break
                        } else {
                            val uri = uriclip.getItemAt(i).uri
                            // 한 장씩 리스트에 추가
                            imageCommunityAddUriList.add(uri)

                            if (uri != null) {
                                // 안드로이드 Q(10) 이상이라면
                                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                    // 이미지를 생성할 수 있는 객체를 생성한다.
                                    val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                                    // Bitmap을 생성한다.
                                    ImageDecoder.decodeBitmap(source)
                                } else {
                                    // 컨텐츠 프로바이더를 통해 이미지 데이터에 접근한다.
                                    val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
                                    if (cursor != null) {
                                        cursor.moveToNext()

                                        // 이미지의 경로를 가져온다.
                                        val idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                                        val source = cursor.getString(idx)

                                        // 이미지를 생성한다
                                        BitmapFactory.decodeFile(source)
                                    } else {
                                        null
                                    }
                                }

                                // 회전 각도값을 가져온다.
                                val degree = Tools.getDegree(requireContext(), uri)
                                // 회전 이미지를 가져온다
                                val bitmap2 = Tools.rotateBitmap(bitmap!!, degree.toFloat())
                                // 크기를 줄인 이미지를 가져온다.
                                val bitmap3 = Tools.resizeBitmap(bitmap2, 1024)

                                // 이미지 리스트에 추가한다.
                                imageCommunityAddBitmapList.add(bitmap3)
                                if(imageCommunityAddBitmapList.size == imageUploadPossible + 1){
                                    imageCommunityAddBitmapList.removeAt(imageCommunityAddBitmapList.size-2)
                                    break
                                }else{
                                    // add_default 이미지를 리스트 마지막으로 이동
                                    var tmp = imageCommunityAddBitmapList[imageCommunityAddBitmapList.size-2]
                                    imageCommunityAddBitmapList[imageCommunityAddBitmapList.size-2] = bitmap3
                                    imageCommunityAddBitmapList[imageCommunityAddBitmapList.size-1] = tmp
                                }
                                isAddImage = true
                            }
                        }
                    }
                }
                // 리사이클러뷰 업데이트
                updateViewPager2CommunityAddImage()
                fragmentCommunityAddBinding.circleIndicatorCommunityAdd.setViewPager(fragmentCommunityAddBinding.viewPager2CommunityAddImage)
            }
        }
    }

    // 드롭다운 설정
    fun settingTextInputLayoutCommunityAddType() {
        fragmentCommunityAddBinding.apply {
            // 드롭다운 설정
            val typeArray = resources.getStringArray(R.array.type_community)
            val typeArrayAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner_community_add, typeArray)
            textViewCommunityAddType.setAdapter(typeArrayAdapter)
            textViewCommunityAddType.setText(typeArray[2], false)
        }
    }

    // 입력 요소 설정
    fun settingInputForm() {
        viewModel.initializeType()
        viewModel.initializeSubject()
        viewModel.initializeContent()
        settingTextInputLayoutCommunityAddType()

        isAddImage = false

        Tools.showSoftInput(requireContext(), fragmentCommunityAddBinding.textViewCommunityAddSubject)
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

                CoroutineScope(Dispatchers.Main).launch {
                    // 글 객체 생성
                    postData = generatingPostObject()
                    // 글 데이터 업로드
                    uploadCommunityPostData(postData!!.postIdx)

                    val dialog = DialogConfirm(
                        "게시글 등록 완료",
                        "게시글 등록이 완료되었습니다.\n확인하러 가시겠습니까?",
                        communityActivity
                    )
                    dialog.setDialogButtonClickListener(object : DialogConfirm.OnButtonClickListener{
                        override fun okButtonClick() {
                            communityActivity.finish()
                        }

                    })
                    dialog.show(communityActivity.supportFragmentManager, "DialogConfirm")
                }
            }
        }
    }

    // 게시글 객체 생성
    suspend fun generatingPostObject() : PostData {
        fragmentCommunityAddBinding.apply {

            var postData = PostData()

            val authUser = App.authRepository.getCurrentUser()
            if (authUser != null) {
                val user = App.userRepository.getUser(authUser.uid)
                if (user != null) {
                    val aprtment = App.apartmentRepository.getApartment(user.apartmentUid)

                    val job1 = CoroutineScope(Dispatchers.Main).launch {
                        // 게시글 시퀀스 값을 가져오기
                        val communityPostSequence = viewModel.getCommunityPostSequence()
                        // 게시글 시퀀스 값을 업데이트 한다.
                        viewModel.updateCommunityPostSequence(communityPostSequence + 1)

                        // 업로드할 정보를 담아준다.
                        postData.postId = UUID.randomUUID().toString()
                        postData.postIdx = communityPostSequence + 1
                        postData.postTitle = textViewCommunityAddSubject.text.toString()
                        if (textViewCommunityAddType.text.toString() == "질문") {
                            postData.postType = PostType.TYPE_QUESTION.str
                        } else if (textViewCommunityAddType.text.toString() == "거래") {
                            postData.postType = PostType.TYPE_TRADE.str
                        } else {
                            postData.postType = PostType.TYPE_ETC.str
                        }
                        postData.postContent = textViewCommunityAddContent.text.toString()
                        postData.postLikeCnt = 0
                        postData.postCommentCnt = 0
                        postData.postImages = mutableListOf<String>()
                        postData.postUserId = user.uid
                        postData.postApartId = aprtment!!.uid

                        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
                        postData.postAddDate = simpleDateFormat.format(Date())
                        postData.postModifyDate = simpleDateFormat.format(Date())
                        postData.postState = PostState.POST_STATE_NORMAL.number
                    }
                    job1.join()
                }
            }
            return postData
        }
    }

    // 게시글 작성
    private fun uploadCommunityPostData(postIdx: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            // 첨부된 이미지가 있다면
            if (isAddImage == true) {
                // 서버로 업로드
                val uploadList = viewModel.uploadImage(requireContext(), postIdx, imageCommunityAddUriList)
                postData!!.postImages = uploadList
            } else {
                postData!!.postImages = null
            }
            viewModel.insertCommunityPostData(postData!!)
        }
    }

}
package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInfoBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    lateinit var infoActivity: InfoActivity

    private val userViewModel: UserViewModel by activityViewModels {
        val userDataSource = UserDataSource()
        val localUserDataSource = LocalUserDataSource(requireActivity().applicationContext)
        UserViewModelFactory(UserRepository(userDataSource, localUserDataSource))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        infoActivity = activity as InfoActivity

        val uid = "some-uid"
        userViewModel.loadUser()

        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.textUserInfoTitle.text = it.name
                binding.userInfoTextViewName.text = it.name
                binding.userInfoTextViewEmail.text = it.email
                binding.userInfoTextViewPhoneNumber.text = it.phoneNumber.let { number -> number } ?: "없음"
                binding.userInfoTextViewBirth.text = "${it.birthYear}-${it.birthMonth}-${it.birthDay}"
                binding.userInfoTextViewCertification.text = if (it.apartCertification) "인증" else "미인증"
                binding.userInfoTextViewAdress.text = "${it.apartmentDong}동 ${it.apartmentHo}호"
                binding.userInfoTextViewLogin.text = it.loginType

            } })

        settingToolbar()
        setEvent()

        return binding.root
    }


    fun settingToolbar() {
        binding.apply {
            userInfoToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    infoActivity.finish()
                }
                inflateMenu(R.menu.menu_userinfo)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.userInfoEdit -> {
                            infoActivity.replaceFragment(
                                InfoFragmentName.EDIT_USER_INFO_FRAGMENT,
                                true,
                                true,
                                null
                            )

                        }
                    }
                    true
                }
            }

        }
    }
    fun setEvent() {
        binding.apply {
            infoImageView.setOnClickListener{
                val infoBottomSheetFragment = InfoBottomSheetFragment()
                infoBottomSheetFragment.show(childFragmentManager, infoBottomSheetFragment.tag)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onResume() {
        super.onResume()
        // InfoFragment가 다시 화면에 나타날 때 사용자를 다시 로드
        val uid = "some-uid" // 실제 사용자 UID로 교체
        userViewModel.loadUser()
    }
}


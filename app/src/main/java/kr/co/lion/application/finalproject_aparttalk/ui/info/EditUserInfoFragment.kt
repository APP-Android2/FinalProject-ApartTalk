package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEditUserInfoBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName


class EditUserInfoFragment : Fragment() {
    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!
    lateinit var infoActivity: InfoActivity


    private val userViewModel: UserViewModel by activityViewModels {
        val userDataSource = UserDataSource()
        val localUserDataSource = LocalUserDataSource(requireActivity().applicationContext)
        UserViewModelFactory(UserRepository(userDataSource, localUserDataSource))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        infoActivity = activity as InfoActivity

        settingToolbar()
        settingButton()
        populateUserInfo()

        val uid = "some-uid"
        userViewModel.loadUser()



        return binding.root
    }

    private fun populateUserInfo() {
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.editUserInfoNameInput.setText(it.name)
                binding.editUserInfoEmailInput.setText(it.email)
                binding.editUserInfoNumberInput.setText(it.phoneNumber ?: "-")
                binding.editUserInfoBirthInput.setText("${it.birthYear}-${it.birthMonth}-${it.birthDay}")
                binding.editUserInfoAdressInput.setText("${it.apartmentDong}동 ${it.apartmentHo}호")
            }
        }
    }

    fun settingToolbar() {
        binding.apply {
            editUserInfoToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    infoActivity.supportFragmentManager.popBackStack()
                }
            }


        }
    }
    fun settingButton() {
        binding.userInfoEditButton.setOnClickListener {
            val user = userViewModel.user.value ?: return@setOnClickListener

            val updatedUser = user.copy(
                name = binding.editUserInfoNameInput.text.toString(),
                email = binding.editUserInfoEmailInput.text.toString(),
                phoneNumber = binding.editUserInfoNumberInput.text.toString(),
                apartmentDong = binding.editUserInfoAdressInput.text.toString().split("동").getOrNull(0)?.toIntOrNull() ?: user.apartmentDong,
                apartmentHo = binding.editUserInfoAdressInput.text.toString().split("호").getOrNull(0)?.toIntOrNull() ?: user.apartmentHo
            )
            userViewModel.updateUser(updatedUser)

            // 데이터 갱신 요청
            userViewModel.loadUser()

            infoActivity.supportFragmentManager.popBackStack()

            // InfoFragment 갱신
            val fragment = infoActivity.supportFragmentManager.findFragmentByTag(InfoFragmentName.INFO_FRAGMENT.name)
            fragment?.let {
                infoActivity.supportFragmentManager.beginTransaction().detach(it).attach(it).commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
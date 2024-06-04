package kr.co.lion.application.finalproject_aparttalk.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEditUserInfoBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName


class EditUserInfoFragment : Fragment() {
    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!
    lateinit var infoActivity: InfoActivity


    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        infoActivity = activity as InfoActivity
        userViewModel = infoActivity.userViewModel

        settingToolbar()
        settingButton()
        populateUserInfo()

        val uid = "some-uid"
        userViewModel.loadUser(uid)



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
                phoneNumber = binding.editUserInfoNumberInput.text.toString().takeIf { it.isNotBlank() },
                apartmentDong = binding.editUserInfoAdressInput.text.toString().split("동").getOrNull(0)?.toIntOrNull() ?: user.apartmentDong,
                apartmentHo = binding.editUserInfoAdressInput.text.toString().split("호").getOrNull(0)?.toIntOrNull() ?: user.apartmentHo
            )
            userViewModel.updateUser(updatedUser)
            infoActivity.supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
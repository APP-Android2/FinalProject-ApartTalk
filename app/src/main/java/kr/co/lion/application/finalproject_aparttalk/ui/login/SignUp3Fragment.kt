package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp3Binding
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.SignUpViewModel
import kr.co.lion.application.finalproject_aparttalk.util.Tools

class SignUp3Fragment : Fragment() {

    private var _binding: FragmentSignUp3Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels {
        SignUpViewModelFactory(
            (requireActivity() as SignUpActivity).userRepository,
            (requireActivity() as SignUpActivity).apartmentRepository
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUp3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDataStates()
        selectGenderRadioButton()
        emailEditTextListeners()
        nextButton()
        backProcess()
    }

    private fun initializeDataStates() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it.gender) {
                "남자" -> binding.signup3RadioButtonMale.isChecked = true
                "여자" -> binding.signup3RadioButtonFemale.isChecked = true
                else -> binding.signup3RadioButtonNone.isChecked = true
            }

            binding.signup3EmailEditText.setText(it.email)
        }
    }

    private fun selectGenderRadioButton() {
        binding.signup3GenderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            Tools.hideSoftInput(requireActivity())
            val selectedGender = when (checkedId) {
                R.id.signup3_radio_button_male -> "남자"
                R.id.signup3_radio_button_female -> "여자"
                else -> "선택안함"
            }
            viewModel.setGender(selectedGender)
        }
    }

    private fun emailEditTextListeners() {
        binding.signup3EmailEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                isInputValid()
                true
            } else {
                false
            }
        }
        binding.signup3EmailEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                isInputValid()
            }
        }
    }

    private fun isInputValid(): Boolean {
        val email = binding.signup3EmailEditText.text.toString().trim()
        val isEmailExist = email.isNotEmpty()
        return if (!isEmailExist || email.isValidEmail()) {
            Tools.hideSoftInput(requireActivity())
            binding.signup3EmailLayout.error = null
            true
        } else {
            binding.signup3EmailLayout.error = "유효한 이메일을 입력해주세요"
            false
        }
    }

    private fun String.isValidEmail(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun nextButton() {
        binding.signup3AgreeButton.setOnClickListener {
            if (isInputValid()) {
                viewModel.setEmail(binding.signup3EmailEditText.text.toString().trim())
                findNavController().navigate(R.id.action_signUp3Fragment_to_signUp4Fragment)
            } else {
                Tools.showSoftInput(requireContext(), binding.signup3EmailEditText)
            }
        }
    }

    private fun backProcess() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Tools.hideSoftInput(requireActivity())
            viewModel.resetGender()
            viewModel.resetEmail()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
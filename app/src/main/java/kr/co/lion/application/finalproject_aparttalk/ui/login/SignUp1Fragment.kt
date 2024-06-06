package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp1Binding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.PrivacyPolicyActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.UserAgreementActivity
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.SignUpViewModel

class SignUp1Fragment : Fragment() {

    private var _binding: FragmentSignUp1Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels{
        SignUpViewModelFactory(
            (requireActivity() as SignUpActivity).userRepository,
            (requireActivity() as SignUpActivity).apartmentRepository
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUp1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDataStates()
        updateCheckBoxStates()
        updateButtonState()
        checkBox1ContentButton()
        checkBox2ContentButton()
        nextButton()
    }

    private fun initializeDataStates() {

        viewModel.user.value?.let {
            binding.signup1Checkbox1.isChecked = it.agreementCheck1
            binding.signup1Checkbox2.isChecked = it.agreementCheck2
            binding.signup1Checkbox3.isChecked = it.agreementCheck3
        }

        checkAllCheckBoxState()
    }

    private fun updateCheckBoxStates() {
        binding.signup1AllCheckbox.setOnClickListener {
            val isCheckedAll = binding.signup1AllCheckbox.isChecked

            binding.signup1Checkbox1.isChecked = isCheckedAll
            binding.signup1Checkbox2.isChecked = isCheckedAll
            binding.signup1Checkbox3.isChecked = isCheckedAll
            updateButtonState()
        }

        binding.signup1Checkbox1.setOnClickListener {
            checkAllCheckBoxState()
            updateButtonState()
        }

        binding.signup1Checkbox2.setOnClickListener {
            checkAllCheckBoxState()
            updateButtonState()
        }

        binding.signup1Checkbox3.setOnClickListener {
            checkAllCheckBoxState()
        }
    }

    private fun checkAllCheckBoxState() {
        val isChecked1 = binding.signup1Checkbox1.isChecked
        val isChecked2 = binding.signup1Checkbox2.isChecked
        val isChecked3 = binding.signup1Checkbox3.isChecked

        binding.signup1AllCheckbox.isChecked = isChecked1 && isChecked2 && isChecked3
    }

    private fun checkBox1ContentButton(){
        binding.signup1Checkbox1Content.setOnClickListener {
            startActivity(Intent(requireActivity(), UserAgreementActivity::class.java))
        }
    }

    private fun checkBox2ContentButton(){
        binding.signup1Checkbox2Content.setOnClickListener {
            startActivity(Intent(requireActivity(), PrivacyPolicyActivity::class.java))
        }
    }

    private fun updateButtonState() {
        val isChecked1 = binding.signup1Checkbox1.isChecked
        val isChecked2 = binding.signup1Checkbox2.isChecked
        val isEnabled = isChecked1 && isChecked2

        binding.signup1AgreeButton.isEnabled = isEnabled
        binding.signup1AgreeButton.alpha = if (isEnabled) 1.0f else 0.5f
    }

    private fun nextButton(){
        binding.signup1AgreeButton.setOnClickListener {
            val check1 = binding.signup1Checkbox1.isChecked
            val check2 = binding.signup1Checkbox2.isChecked
            val check3 = binding.signup1Checkbox3.isChecked
            viewModel.setAgreeCheckBox(check1, check2, check3)
            findNavController().navigate(R.id.action_signUp1Fragment_to_signUp2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
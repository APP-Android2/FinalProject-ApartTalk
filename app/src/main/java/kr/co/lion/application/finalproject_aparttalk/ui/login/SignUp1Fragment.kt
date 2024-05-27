package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp1Binding

class SignUp1Fragment : Fragment() {

    private var _binding: FragmentSignUp1Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUp1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDataStates()
        updateCheckBoxStates()
        updateButtonState()

        nextButton()
    }

    private fun initializeDataStates() {
        binding.signup1Checkbox1.isChecked = viewModel.checkbox1Checked
        binding.signup1Checkbox2.isChecked = viewModel.checkbox2Checked
        binding.signup1Checkbox3.isChecked = viewModel.checkbox3Checked

        checkAllCheckBoxState()
    }

    private fun updateCheckBoxStates() {
        binding.signup1AllCheckbox.setOnClickListener {
            val isCheckedAll = binding.signup1AllCheckbox.isChecked

            binding.signup1Checkbox1.isChecked = isCheckedAll
            binding.signup1Checkbox2.isChecked = isCheckedAll
            binding.signup1Checkbox3.isChecked = isCheckedAll

            viewModel.checkbox1Checked = isCheckedAll
            viewModel.checkbox2Checked = isCheckedAll
            viewModel.checkbox3Checked = isCheckedAll
            updateButtonState()
        }

        binding.signup1Checkbox1.setOnClickListener {
            viewModel.checkbox1Checked = binding.signup1Checkbox1.isChecked
            checkAllCheckBoxState()
            updateButtonState()
        }

        binding.signup1Checkbox2.setOnClickListener {
            viewModel.checkbox2Checked = binding.signup1Checkbox2.isChecked
            checkAllCheckBoxState()
            updateButtonState()
        }

        binding.signup1Checkbox3.setOnClickListener {
            viewModel.checkbox3Checked = binding.signup1Checkbox3.isChecked
            checkAllCheckBoxState()
        }
    }

    private fun checkAllCheckBoxState() {
        val isChecked1 = binding.signup1Checkbox1.isChecked
        val isChecked2 = binding.signup1Checkbox2.isChecked
        val isChecked3 = binding.signup1Checkbox3.isChecked

        binding.signup1AllCheckbox.isChecked = isChecked1 && isChecked2 && isChecked3
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
            findNavController().navigate(R.id.action_signUp1Fragment_to_signUp2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
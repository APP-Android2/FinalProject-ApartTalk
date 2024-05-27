package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp2Binding

class SignUp2Fragment : Fragment() {

    private var _binding: FragmentSignUp2Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUp2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDataStates()

        setupListeners()
        updateButtonState()

        backProcess()
        nextButton()
    }

    private fun initializeDataStates() {
        binding.signup2Year.text = "${viewModel.birthYear.value ?: ""}년"
        binding.signup2Month.text = "${viewModel.birthMonth.value ?: ""}월"
        binding.signup2Day.text = "${viewModel.birthDay.value ?: ""}일"
    }

    private fun setupListeners() {
        binding.signup2SelectBirth.setOnClickListener {
            showDatePickerDialog()
        }

        binding.signup2NameEditText.addTextChangedListener {
            updateButtonState()
        }
    }

    private fun showDatePickerDialog() {
        val initialYear = viewModel.birthYear.value ?: 1980
        val initialMonth = viewModel.birthMonth.value ?: 1
        val initialDay = viewModel.birthDay.value ?: 1

        val datePickerDialogFragment = DatePickerDialogFragment(initialYear, initialMonth, initialDay) { year, month, day ->
            onDateSelected(year, month, day)
        }
        datePickerDialogFragment.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(year: Int, month: Int, day: Int) {

        viewModel.setBirthDate(year, month, day)

        viewModel.birthYear.observe(viewLifecycleOwner) { year ->
            binding.signup2Year.text = "${year ?: ""}년"
        }

        viewModel.birthMonth.observe(viewLifecycleOwner) { month ->
            binding.signup2Month.text = "${month ?: ""}월"
        }

        viewModel.birthDay.observe(viewLifecycleOwner) { day ->
            binding.signup2Day.text = "${day ?: ""}일"
        }

        updateButtonState()
    }

    private fun updateButtonState() {
        val isNameFilled = binding.signup2NameEditText.text?.isNotEmpty() == true
        val isDateSelected = viewModel.birthYear.value != null && viewModel.birthMonth.value != null && viewModel.birthDay.value != null

        binding.signup2AgreeButton.isEnabled = isNameFilled && isDateSelected
        binding.signup2AgreeButton.alpha = if (isNameFilled && isDateSelected) 1.0f else 0.5f
    }

    private fun nextButton(){
        binding.signup2AgreeButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUp2Fragment_to_signUp3Fragment)
        }
    }

    private fun backProcess(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
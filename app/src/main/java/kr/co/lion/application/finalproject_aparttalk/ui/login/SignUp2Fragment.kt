package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp2Binding
import kr.co.lion.application.finalproject_aparttalk.util.Tools

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
        setupNameEditTextListeners()
        setupSelectBirthButton()
        setupAgreeButton()
        setupBackButton()
        updateButtonState()
    }

    private fun initializeDataStates() {
        viewModel.name.observe(viewLifecycleOwner) { name ->
            binding.signup2NameEditText.setText(name.orEmpty())
        }

        viewModel.birthYear.observe(viewLifecycleOwner) { year ->
            binding.signup2Year.text = "${year ?: ""}년"
        }

        viewModel.birthMonth.observe(viewLifecycleOwner) { month ->
            binding.signup2Month.text = "${month ?: ""}월"
        }

        viewModel.birthDay.observe(viewLifecycleOwner) { day ->
            binding.signup2Day.text = "${day ?: ""}일"
        }
    }

    private fun setupNameEditTextListeners() {
        if (viewModel.name.value.isNullOrEmpty()) {
            Tools.showSoftInput(requireContext(), binding.signup2NameEditText)
        }

        binding.signup2NameEditText.addTextChangedListener {
            updateButtonState()
        }

        val editorActionListener = TextView.OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handleNameValidation()
                true
            } else {
                false
            }
        }

        binding.signup2NameEditText.setOnEditorActionListener(editorActionListener)
        binding.signup2NameEditText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                handleNameValidation()
            }
        }
    }

    private fun setupSelectBirthButton() {
        binding.signup2SelectBirth.setOnClickListener {
            Tools.hideSoftInput(requireActivity())
            showDatePickerDialog()
        }
    }

    private fun setupAgreeButton() {
        binding.signup2AgreeButton.setOnClickListener {
            handleNameValidation()
            if (isNameValid(binding.signup2NameEditText.text.toString().trim())) {
                next()
            } else {
                Tools.showSoftInput(requireContext(), binding.signup2NameEditText)
            }
        }
    }

    private fun setupBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Tools.hideSoftInput(requireActivity())
            viewModel.setName("")
            viewModel.setBirthDate(null, null, null)
            findNavController().popBackStack()
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
        updateButtonState()
    }

    private fun updateButtonState() {
        val isNameFilled = binding.signup2NameEditText.text.toString().trim().isNotEmpty()
        val isDateSelected = viewModel.birthYear.value != null && viewModel.birthMonth.value != null && viewModel.birthDay.value != null

        binding.signup2AgreeButton.isEnabled = isNameFilled && isDateSelected
        binding.signup2AgreeButton.alpha = if (isNameFilled && isDateSelected) 1.0f else 0.5f
    }

    private fun handleNameValidation() {
        val name = binding.signup2NameEditText.text.toString().trim()
        val isValidName = isNameValid(name)

        if (isValidName) {
            binding.signup2NameLayout.error = null
            Tools.hideSoftInput(requireActivity())
        } else {
            binding.signup2NameLayout.error = "한글, 영문 대/소문자를 사용해 주세요\n(특수기호, 공백 사용 불가)"
        }
    }

    private fun isNameValid(name: String): Boolean {
        return name.matches(Regex("^[가-힣a-zA-Z]*$"))
    }

    private fun next() {
        Tools.hideSoftInput(requireActivity())
        findNavController().navigate(R.id.action_signUp2Fragment_to_signUp3Fragment)
        viewModel.setName(binding.signup2NameEditText.text.toString().trim())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
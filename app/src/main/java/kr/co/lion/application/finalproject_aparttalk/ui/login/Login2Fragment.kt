package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentLogin2Binding

class Login2Fragment : Fragment() {

    private var _binding: FragmentLogin2Binding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            (requireActivity() as LoginActivity).authRepository,
            (requireActivity() as LoginActivity).userRepository
        )
    }

    private var timer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogin2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton()
        backProcess()
        setupPhoneNumberFormatting()
        setupVerificationCodeInput()
        setupSendVerificationButton()
    }

    private fun backProcess() {
        binding.login2Toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun loginButton(){
        binding.login2LoginButton.setOnClickListener {
            (activity as? LoginActivity)?.launchSignActivity()
        }
    }

    private fun setupPhoneNumberFormatting() {
        binding.phoneNumberEditText.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val phonePattern = "###-####-####"
            private val phoneRegex = Regex("^\\d{3}-\\d{4}-\\d{4}$")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    val userInput = s.toString().replace(Regex("[^\\d]"), "")
                    if (userInput.length <= 11) {
                        current = userInput.chunked(4).joinToString("-")
                        if (userInput.length > 3) {
                            current = userInput.substring(0, 3) + "-" + userInput.substring(3).chunked(4).joinToString("-")
                        }
                        binding.phoneNumberEditText.setText(current)
                        binding.phoneNumberEditText.setSelection(current.length)
                    } else {
                        current = userInput.chunked(4).joinToString("-")
                        binding.phoneNumberEditText.setText(current)
                        binding.phoneNumberEditText.setSelection(current.length)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupVerificationCodeInput() {
        binding.verificationCodeEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {

                if (s?.length == 6) {
                    binding.login2LoginButton.isEnabled = true
                    binding.login2LoginButton.alpha = 1.0f
                } else {
                    binding.login2LoginButton.isEnabled = false
                    binding.login2LoginButton.alpha = 0.5f
                }
            }
        })
    }

    private fun setupSendVerificationButton() {
        binding.sendVerificationButton.setOnClickListener {
            startTimer()
            binding.sendVerificationButtonText.text = "재전송"
            binding.verificationCodeEditText.isEnabled = true
            binding.timerTextView.isVisible = true
        }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000 % 60)
                binding.timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.timerTextView.text = "00:00"
                binding.verificationCodeEditText.isEnabled = false
                binding.sendVerificationButton.isEnabled = true
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timer?.cancel()
    }
}
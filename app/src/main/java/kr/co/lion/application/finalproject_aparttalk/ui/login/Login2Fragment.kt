package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentLogin2Binding
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.LoginViewModel

class Login2Fragment : Fragment() {

    private var _binding: FragmentLogin2Binding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(
            (requireActivity() as LoginActivity).authRepository,
            (requireActivity() as LoginActivity).userRepository,
            (requireActivity() as LoginActivity).apartmentRepository,
        )
    }

    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogin2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        getVerificationCodeButton()
        loginButton()
        updateLoginButtonState()
        backProcess()
    }

    private fun observe() {
        viewModel.verificationId.observe(viewLifecycleOwner) { verificationId ->
            if (verificationId != null) {
                binding.login2VerificationCodeLayout.visibility = View.VISIBLE
                binding.login2VerificationCodeButton.text = "재전송"
                startTimer()
            }
        }
    }

    private fun getVerificationCodeButton() {
        binding.login2VerificationCodeButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.phoneLogin(requireActivity(), requireContext(), binding.login2PhoneNumberEditText.text.toString().trim()).join()
            }
        }
    }

    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(120000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                binding.login2TimerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                binding.login2VerificationCodeLayout.visibility = View.INVISIBLE
                binding.verificationCodeEditText.setText("")
                binding.login2VerificationCodeButton.text = "인증번호"
                viewModel.resetVerificationId()
                Toast.makeText(requireContext(), "인증시간이 초과되었습니다.\n다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }


    private fun loginButton() {
        binding.login2LoginButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val result = viewModel.verifyCode(requireContext(), binding.verificationCodeEditText.text.toString().trim())
                if (result) {
                    (activity as? LoginActivity)?.launchSignActivity()
                } else {
                    Toast.makeText(requireContext(), "인증번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateLoginButtonState(){
        binding.verificationCodeEditText.addTextChangedListener {
            if (it?.length == 6){
                binding.login2LoginButton.isEnabled = true
                binding.login2LoginButton.alpha = 1.0f
            } else {
                binding.login2LoginButton.isEnabled = false
                binding.login2LoginButton.alpha = 0.5f
            }
        }
    }

    private fun backProcess() {
        binding.login2Toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        countDownTimer?.cancel()
    }
}
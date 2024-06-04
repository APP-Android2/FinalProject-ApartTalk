package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentLoginBinding
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            (requireActivity() as LoginActivity).authRepository,
            (requireActivity() as LoginActivity).userRepository
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAuthStateToNavigate()
        googleLoginButton()
        kakaoLoginButton()
        naverLoginButton()
        phoneLoginButton()
    }

    private fun userAuthStateToNavigate(){
        viewModel.userAuthenticationState.observe(viewLifecycleOwner){ state ->
            when (state) {
                NavigationState.TO_MAIN -> { (requireActivity() as LoginActivity).navigateToMain() }
                NavigationState.TO_SIGNUP -> { (requireActivity() as LoginActivity).launchSignActivity() }
                else -> { }
            }
        }
    }

    private fun googleLoginButton() {
        binding.googleLoginButton.setOnClickListener {
            viewModel.googleLogin(requireContext())
        }
    }

    private fun kakaoLoginButton() {
        binding.kakaoLoginButton.setOnClickListener {
        }
    }

    private fun naverLoginButton() {
        binding.naverLoginButton.setOnClickListener {
            (requireActivity() as LoginActivity).navigateToMain()
        }
    }

    private fun phoneLoginButton() {
        binding.phoneNumberLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_login2Fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
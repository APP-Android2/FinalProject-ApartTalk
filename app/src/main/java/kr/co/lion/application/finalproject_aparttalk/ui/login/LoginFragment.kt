package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.LoginActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater)

        login()

        return binding.root
    }

    private fun login(){
        binding.buttonLogin.setOnClickListener {
            (requireActivity() as LoginActivity).navigateToMain()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
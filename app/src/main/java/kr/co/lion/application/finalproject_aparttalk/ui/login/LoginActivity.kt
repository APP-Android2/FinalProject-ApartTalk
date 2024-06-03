package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLoginBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var signActivityLauncher: ActivityResultLauncher<Intent>

    val authRepository: AuthRepository by lazy { AuthRepository(FirebaseAuthService()) }
    val userRepository: UserRepository by lazy { UserRepository(UserDataSource(), LocalUserDataSource(this@LoginActivity)) }
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(authRepository, userRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAuthenticationState()
        initializeActivityResultLauncher()
        showPermissionBottomSheet()
    }

    private fun userAuthenticationState(){
        viewModel.userAuthenticationState.observe(this@LoginActivity) { event ->
            when (event) {
                is NavigationLoginEvent.NavigationToMain -> { navigateToMain() }
                is NavigationLoginEvent.NavigationToSignUp -> { launchSignActivity() }
                is NavigationLoginEvent.NavigationToLogin -> { }
                else -> { }
            }
        }
    }

    private fun initializeActivityResultLauncher(){
        signActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                finish()
            }
        }
    }

    private fun showPermissionBottomSheet(){
        val bottomSheet = PermissionBottomSheetFragment()
        bottomSheet.show(supportFragmentManager, "PermissionBottomSheetFragment")
    }

    fun navigateToMain(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun launchSignActivity() {
        val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
        signActivityLauncher.launch(intent)
    }
}
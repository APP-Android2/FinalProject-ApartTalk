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
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var signActivityLauncher: ActivityResultLauncher<Intent>

    val authRepository: AuthRepository by lazy { AuthRepository(FirebaseAuthService(), LocalUserDataSource(this@LoginActivity), LocalApartmentDataSource(this@LoginActivity)) }
    val userRepository: UserRepository by lazy { UserRepository(UserDataSource(), LocalUserDataSource(this@LoginActivity)) }
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(authRepository, userRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeActivityResultLauncher()
        showPermissionBottomSheet()
    }

    private fun initializeActivityResultLauncher(){
        signActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                finish()
            }
            else{
                authRepository.signOut()
            }
        }
    }

    private fun showPermissionBottomSheet(){
        val bottomSheet = PermissionBottomSheetFragment()
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
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
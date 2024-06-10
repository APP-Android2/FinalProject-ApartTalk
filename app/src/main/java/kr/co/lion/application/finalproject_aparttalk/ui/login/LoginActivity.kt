package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLoginBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var signActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var sharedPreferences: SharedPreferences

    val authRepository: AuthRepository by lazy { AuthRepository(FirebaseAuthService()) }
    val userRepository: UserRepository by lazy { UserRepository(UserDataSource(), LocalUserDataSource(applicationContext)) }
    val apartmentRepository: ApartmentRepository by lazy { ApartmentRepository(ApartmentDataSource(), LocalApartmentDataSource(applicationContext)) }
    private val viewModel: LoginViewModel by viewModels { LoginViewModelFactory(authRepository, userRepository, apartmentRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

        loading()
        initializeActivityResultLauncher()
        checkFirstRun()
    }

    private fun loading(){
        viewModel.isLoading.observe(this) { isLoading ->
            binding.loginProgressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun checkFirstRun() {
        val isFirstRun = sharedPreferences.getBoolean("is_first_run", true)
        if (isFirstRun) {
            showPermissionBottomSheet()
            sharedPreferences.edit().putBoolean("is_first_run", false).apply()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionBottomSheetFragment.REQUEST_CODE) {
            grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        }
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
package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var signActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPermissionBottomSheet()

        // ActivityResultLauncher를 초기화합니다.
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
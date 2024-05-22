package kr.co.lion.application.finalproject_aparttalk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun navigateToMain(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}
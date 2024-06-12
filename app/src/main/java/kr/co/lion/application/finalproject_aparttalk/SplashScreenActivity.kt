package kr.co.lion.application.finalproject_aparttalk

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivitySplashScreenBinding
import kr.co.lion.application.finalproject_aparttalk.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        showImage()
        showSplashScreen()
    }

    //이미지가 0.8초 뒤에 보이게 한다
    private fun showImage(){
        lifecycleScope.launch {
            delay(800) // 0.8초 딜레이
            binding.splashscreenImage.visibility = View.VISIBLE
        }
    }

    private fun showSplashScreen(){
        lifecycleScope.launch {
            delay(2200)
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }
    }
}
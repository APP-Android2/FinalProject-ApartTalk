package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityFireCheckBinding

class FireCheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFireCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFireCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
    }

    // 툴바 구성
    fun setToolbar(){
        binding.apply {
            toolbarFireCheck.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기. (홈화면 또는 전체메뉴화면)
                }
            }
        }
    }
}
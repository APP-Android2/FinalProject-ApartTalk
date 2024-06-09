package kr.co.lion.application.finalproject_aparttalk.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAlarmBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMainBinding

class AlarmActivity : AppCompatActivity() {

    lateinit var binding:ActivityAlarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
    }

    //툴바 설정
    private fun settingToolbar(){
        binding.apply {
            toolbarAlarm.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }
}
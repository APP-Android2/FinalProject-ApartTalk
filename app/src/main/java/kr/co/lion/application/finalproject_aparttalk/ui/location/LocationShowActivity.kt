package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityLocationShowBinding

class LocationShowActivity : AppCompatActivity() {

    lateinit var binding:ActivityLocationShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
    }


    //툴바 설정
    private fun settingToolbar(){
        with(binding){
            toolbarLocationShow.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }
}
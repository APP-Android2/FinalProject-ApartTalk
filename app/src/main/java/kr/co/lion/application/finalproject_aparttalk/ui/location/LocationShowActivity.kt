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
        initView()
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

    //화면 구현
    private fun initView(){
        binding.apply {
            //데이터 받아오기
            val name = intent?.getStringExtra("name")
            val category = intent?.getStringExtra("category")
            val address = intent?.getStringExtra("address")
            val number = intent?.getStringExtra("number")
            val distance = intent?.getStringExtra("distance")

            textLocationShowTitle.text = name
            textLocationShowCategory.text = category
            textLocationShowAddress.text = address
            textLocationShowNumber.text = number
            textLocationShowDistance.text = "우리집에서 부터 ${distance}m 떨어져있어요"
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityCompanyInfoBinding

class CompanyInfoActivity : AppCompatActivity() {

    lateinit var binding:ActivityCompanyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()

    }

    private fun settingToolbar(){
        with(binding){
            toolbarCompanyInfo.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }
}
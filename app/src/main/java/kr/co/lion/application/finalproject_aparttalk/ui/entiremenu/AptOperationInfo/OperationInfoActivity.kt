package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityOperationInfoBinding

class OperationInfoActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityOperationInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOperationInfoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_operation_info)

        setToolbar()
    }

    fun setToolbar(){
        binding.apply {
            toolbarAptOperationInfo.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전체메뉴로 돌아가기
                }
            }
        }
    }
}
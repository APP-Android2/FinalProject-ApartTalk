package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAptScheduleBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.adapter.AptScheduleRecyclerView

class AptScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAptScheduleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAptScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setRecyclerAptSchedule()

    }

    // 툴바 구성
    fun setToolbar(){
        binding.apply {
            toolbarAptSchedule.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기. (홈화면 또는 전체메뉴화면)
                    finish()
                }
            }
        }
    }

    // recyclerView 설정
    private fun setRecyclerAptSchedule() {
        binding.recyclerViewAptSchedule.apply {
            // 어댑터 설정
            adapter = AptScheduleRecyclerView(supportFragmentManager)
            // 레이아웃 매니저 설정
            layoutManager = LinearLayoutManager(this@AptScheduleActivity)
            // 데코
            val deco = MaterialDividerItemDecoration(this@AptScheduleActivity, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }
}

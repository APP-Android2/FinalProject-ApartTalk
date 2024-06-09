package kr.co.lion.application.finalproject_aparttalk.ui.home

import android.os.Bundle
import android.widget.Adapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAlarmBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMainBinding
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.AlarmAdapter

class AlarmActivity : AppCompatActivity() {

    lateinit var binding:ActivityAlarmBinding

    val alarmAdapter : AlarmAdapter by lazy {
        val adapter = AlarmAdapter()
        adapter
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
        connectAdapter()
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

    //어댑터 연결
    private fun connectAdapter(){
        binding.apply {
            recyclerviewAlarm.apply {
                adapter = alarmAdapter
                layoutManager = LinearLayoutManager(this@AlarmActivity)
                val deco = MaterialDividerItemDecoration(this@AlarmActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
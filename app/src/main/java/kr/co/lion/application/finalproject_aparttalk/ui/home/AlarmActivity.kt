package kr.co.lion.application.finalproject_aparttalk.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAlarmBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMainBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.AlarmAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.home.viewmodel.AlarmViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class AlarmActivity : AppCompatActivity() {

    lateinit var binding:ActivityAlarmBinding

    val alarmAdapter : AlarmAdapter by lazy {
        val adapter = AlarmAdapter()
        adapter.setRecyclerviewClick(object : AlarmAdapter.ItemOnClickListener{
            override fun alarmRecyclerviewClick(postIdx: Int, postId: String, postApartId: String) {
                val newIntent = Intent(this@AlarmActivity, CommunityActivity::class.java)
                newIntent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                newIntent.putExtra("postIdx", postIdx)
                newIntent.putExtra("postId", postId)
                newIntent.putExtra("postApartId", postApartId)
                startActivity(newIntent)
            }

        })
        adapter
    }

    val viewModel : AlarmViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
        connectAdapter()
        gettingData()
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

    //데이터 받아오기
    private fun gettingData(){
        lifecycleScope.launch {
            val authUser = App.authRepository.getCurrentUser()
            if (authUser != null){
                val user = App.userRepository.getUser(authUser.uid)
                if (user != null){
                    viewModel.getAlarmInfo(user.apartmentUid)

                }
            }
        }
        viewModel.alarmList.observe(this@AlarmActivity){ value ->
            alarmAdapter.submitList(value)

            Log.d("apartTalk1234", "${value}")
        }
    }
}
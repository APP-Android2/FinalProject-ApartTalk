package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityDetailFacilityBinding
import kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter.DetailViewPagerAdapter

class DetailFacilityActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailFacilityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFacilityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        settingToolbar()
        connectAdapter()
        settingEvent()

    }

    private fun settingToolbar(){
        with(binding){
            toolbarDetailFacility.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }


    //뷰페이저 어댑터 연결
    private fun connectAdapter(){
        binding.apply {
            val adapter = DetailViewPagerAdapter()

            viewPagerDetail.adapter = adapter

            val imageResIds = listOf(
                R.drawable.test_facility, R.drawable.test_facility, R.drawable.icon_settings,
                R.drawable.icon_facility
            )

            adapter.submitList(imageResIds)

            springDotIndicator.setViewPager(viewPagerDetail)
        }
    }

    private fun settingEvent(){
        with(binding){
            buttonGoReservation.setOnClickListener {
                startActivity(Intent(this@DetailFacilityActivity, FacReservationActivity::class.java))
                finish()
            }
        }
    }
}
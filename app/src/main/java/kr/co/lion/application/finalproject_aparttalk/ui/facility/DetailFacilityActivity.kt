package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityDetailFacilityBinding
import kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter.DetailViewPagerAdapter
import kr.co.lion.application.finalproject_aparttalk.util.setImage

class DetailFacilityActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailFacilityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFacilityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        settingToolbar()
        settingEvent()
        checkButton()
        initView()

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


    private fun settingEvent(){
        with(binding){
            buttonGoReservation.setOnClickListener {
                startActivity(Intent(this@DetailFacilityActivity, FacReservationActivity::class.java))
                finish()
            }
        }
    }

    //버튼 활성화
    private fun checkButton(){
        binding.apply {
            val canReserve = intent?.getBooleanExtra("canReserve", false)

            if (canReserve == false){
                buttonGoReservation.visibility = View.GONE
            }else{
                buttonGoReservation.visibility = View.VISIBLE
            }
        }
    }

    //정보 보여주기
    private fun initView(){
        binding.apply {
            val titleText = intent?.getStringExtra("titleText")
            val content = intent?.getStringExtra("content")
            val price = intent?.getStringExtra("price")
            val image = intent?.getStringExtra("imageRes")

            textDetailName.text = titleText
            textDetailInfo.text = content
            textFacilityPrice.text = "가격 : ${price}"
            imageFacilityDetail.context.setImage(imageFacilityDetail, image)
        }
    }
}
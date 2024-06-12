package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityDetailFacilityBinding
import kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel.FacilityResInfoViewmodel
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.setImage

class DetailFacilityActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailFacilityBinding

    val viewModel : FacilityResInfoViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailFacilityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        settingToolbar()
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

            lifecycleScope.launch {
                val authUser = App.authRepository.getCurrentUser()
                if (authUser != null){
                    val user = App.userRepository.getUser(authUser.uid)
                    if (user != null){
                        viewModel.getFacilityResData(user.uid)

                        viewModel.facilityList.observe(this@DetailFacilityActivity){value ->
                            val isOneDayPassed = viewModel.checkFacilityRes()
                        }

                    }
                }
            }

            buttonGoReservation.setOnClickListener {
                if (true) {
                    val newIntent = Intent(
                        this@DetailFacilityActivity,
                        FacReservationActivity::class.java
                    )
                    newIntent.putExtra("titleText", titleText)
                    newIntent.putExtra("price", price)
                    newIntent.putExtra("imageRes", image)
                    startActivity(newIntent)

                    finish()

                } else {
                    val dialog = DialogConfirm(
                        "예약 오류",
                        "예약은 하루에 한 번만 가능합니다",
                        this@DetailFacilityActivity
                    )
                    dialog.setDialogButtonClickListener(object :
                        DialogConfirm.OnButtonClickListener {
                        override fun okButtonClick() {
                            dialog.dismiss()
                        }

                    })
                    dialog.show(
                        this@DetailFacilityActivity.supportFragmentManager,
                        "DialogConfirm"
                    )
                }
            }
        }
    }

    private fun check(){

    }

}
package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityFacReservationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel.FacilityResInfoViewmodel
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

class FacReservationActivity : AppCompatActivity() {

    lateinit var binding:ActivityFacReservationBinding

    val viewModel : FacilityResInfoViewmodel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
        connectingAdapter()
        initView()
        settingEvent()

    }

    private fun initView(){
        binding.apply {
            lifecycleScope.launch {
                val authUser = App.authRepository.getCurrentUser()
                if (authUser != null){
                    val user = App.userRepository.getUser(authUser.uid)
                    if (user != null){
                        val apart = App.apartmentRepository.getApartment(user.uid)

                        textFacReservationPerson.text = user.name
                        textFacReservationNumber.text = user.phoneNumber
                        textFacReservationAddress.text = apart?.address
                    }
                }
            }
        }
    }


    private fun settingToolbar(){
        with(binding){
            toolbarFacReservation.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }

    //Adapter 연결
    private fun connectingAdapter(){
        binding.apply {
            ArrayAdapter.createFromResource(this@FacReservationActivity, R.array.reservation_time, R.layout.dropdown_item)
                .also { adapter ->
                    adapter.setDropDownViewResource(R.layout.dropdown_item)
                    timeSpinner.adapter = adapter
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun settingEvent(){
        binding.apply {
            buttonReserve.setOnClickListener {
                val selectedPosition = timeSpinner.selectedItemPosition

                if (selectedPosition == 0){
                    Toast.makeText(this@FacReservationActivity, "이용시간을 선택해주세요", Toast.LENGTH_SHORT).show()
                }else{
                    insertResData()
                }
            }
        }
    }

    //예약정보를 저장한다
        @RequiresApi(Build.VERSION_CODES.O)
        private fun insertResData(){
            lifecycleScope.launch {
                binding.apply {
                    val authUser = App.authRepository.getCurrentUser()
                    if (authUser != null){
                        val user = App.userRepository.getUser(authUser.uid)
                        if (user != null){
                            val title = intent?.getStringExtra("titleText")
                            val price = intent?.getStringExtra("price")
                            val image = intent?.getStringExtra("imageRes")

                            val userUid = user.uid
                            val titleText = title
                            val useTime = timeSpinner.selectedItem
                            val imageRes = image
                            val usePrice = price
                            val reservationState = true
                            val localDate: String = LocalDate.now().toString()
                            val userName = user.name
                            val userNumber = user.phoneNumber

                            viewModel.insertResData(userUid, titleText?:"", useTime.toString(), imageRes?:"", usePrice?:"", reservationState, localDate, userName, userNumber){ success ->
                                if (success){
                                    val dialog = DialogConfirm("예약 완료", "예약이 완료되셨습니다.\n결제는 현장에서 진행해주시면 됩니다.", this@FacReservationActivity)
                                    dialog.setDialogButtonClickListener(object : DialogConfirm.OnButtonClickListener{
                                        override fun okButtonClick() {
                                            finish()
                                        }

                                    })
                                    dialog.show(this@FacReservationActivity.supportFragmentManager, "DialogConfirm")

                                }
                            }
                        }
                    }
                }
            }
        }

}
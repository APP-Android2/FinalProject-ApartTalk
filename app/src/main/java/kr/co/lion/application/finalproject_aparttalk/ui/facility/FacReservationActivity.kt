package kr.co.lion.application.finalproject_aparttalk.ui.facility

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityFacReservationBinding

class FacReservationActivity : AppCompatActivity() {

    lateinit var binding:ActivityFacReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingToolbar()
        connectingAdapter()

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
        val facReservationArray = resources.getStringArray(R.array.reservation_time)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, facReservationArray)
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
    }
}
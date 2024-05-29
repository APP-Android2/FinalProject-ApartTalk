package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityParkingBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.EntireMenuFragment
import kr.co.lion.application.finalproject_aparttalk.ui.facility.FacilityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.home.fragment.AlarmFragment
import kr.co.lion.application.finalproject_aparttalk.ui.home.fragment.HomeFragment
import kr.co.lion.application.finalproject_aparttalk.ui.location.LocationFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ParkingFragmentName

class ParkingActivity : AppCompatActivity() {

    lateinit var binding: ActivityParkingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ParkingFragmentName.PARKING_CHECK_FRAGMENT, false, null)

    }

    //Fragment 교체
    fun replaceFragment(name: ParkingFragmentName, addToBackStack: Boolean, data:Bundle?){

        SystemClock.sleep(200)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when(name){
            ParkingFragmentName.PARKING_CHECK_FRAGMENT -> fragmentTransaction.replace(R.id.parkingContainer, ParkingCheckFragment())
            ParkingFragmentName.PARKING_RESERVE_FRAGMENT -> fragmentTransaction.replace(R.id.parkingContainer, ParkingReserveFragment())

        }

        if (addToBackStack){
            if (supportFragmentManager.backStackEntryCount > 0){
                val lastFragmentName = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount -1).name

                if (lastFragmentName != name.str){
                    fragmentTransaction.addToBackStack(name.str)
                }else{
                    fragmentTransaction.addToBackStack(name.str)
                }
            }
        }
        fragmentTransaction.commit()

    }

    fun removeFragment(name: ParkingFragmentName){
        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityParkingBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityModifyFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunitySearchFragment
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

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParkingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ParkingFragmentName.PARKING_CHECK_FRAGMENT, false, null)

    }

    //Fragment 교체
    fun replaceFragment(name: ParkingFragmentName, addToBackStack:Boolean, data:Bundle?){
        SystemClock.sleep(200)
        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name){
            // 검색
            ParkingFragmentName.PARKING_CHECK_FRAGMENT -> {
                newFragment = ParkingCheckFragment()
            }
            ParkingFragmentName.PARKING_RESERVE_FRAGMENT -> {
                newFragment = ParkingReserveFragment()
            }
        }
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){
            fragmentTransaction.replace(R.id.parkingContainer, newFragment!!)
            if(addToBackStack){
                fragmentTransaction.addToBackStack(name.str)
            }
            fragmentTransaction.commit()
        }
    }

    fun removeFragment(name: ParkingFragmentName){
        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
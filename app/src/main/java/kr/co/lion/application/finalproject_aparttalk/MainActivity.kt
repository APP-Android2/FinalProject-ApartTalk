package kr.co.lion.application.finalproject_aparttalk

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMainBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.EntireMenuFragment
import kr.co.lion.application.finalproject_aparttalk.ui.facility.FacilityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.home.fragment.AlarmFragment
import kr.co.lion.application.finalproject_aparttalk.ui.home.fragment.HomeFragment
import kr.co.lion.application.finalproject_aparttalk.ui.location.LocationFragment
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNaviClick()
        initView()

    }

    @Deprecated("This method has been deprecated in favor of using the\n{@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed()
            return
        }

        this.backPressedOnce = true
        Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

        backPressHandler.postDelayed(backPressRunnable, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressHandler.removeCallbacks(backPressRunnable)
    }

    private fun initView(){
        replaceFragment(MainFragmentName.HOME_FRAGMENT, true, null)
        binding.bottomNavi.selectedItemId = R.id.home_item
    }



    //bottomNavi 클릭 이벤트
    private fun bottomNaviClick(){
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.community_item -> {
                    replaceFragment(MainFragmentName.COMMUNITY_FRAGMENT, true, null)
                    true
                }
                R.id.location_item -> {
                    replaceFragment(MainFragmentName.LOCATION_FRAGMENT, true, null)
                    true
                }
                R.id.home_item -> {
                    replaceFragment(MainFragmentName.HOME_FRAGMENT, true, null)
                    true
                }
                R.id.facility_item -> {
                    replaceFragment(MainFragmentName.FACILITY_FRAGMENT, true, null)
                    true
                }
                R.id.entiremenu_item -> {
                    replaceFragment(MainFragmentName.ENTIRE_MENU_FRAGMENT,true,null)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    //Fragment 교체
    fun replaceFragment(name:MainFragmentName, addToBackStack: Boolean, data:Bundle?){

        SystemClock.sleep(200)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when(name){
            MainFragmentName.COMMUNITY_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, CommunityFragment())
            MainFragmentName.LOCATION_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, LocationFragment())
            MainFragmentName.HOME_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, HomeFragment())
            MainFragmentName.FACILITY_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, FacilityFragment())
            MainFragmentName.ENTIRE_MENU_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, EntireMenuFragment())
            MainFragmentName.ALARM_FRAGMENT -> fragmentTransaction.replace(R.id.main_container, AlarmFragment())
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
            fragmentTransaction.commit()
        }

    }

    fun removeFragment(){
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
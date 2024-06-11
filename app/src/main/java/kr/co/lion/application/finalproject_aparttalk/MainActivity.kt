package kr.co.lion.application.finalproject_aparttalk

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMainBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.EntireMenuFragment
import kr.co.lion.application.finalproject_aparttalk.ui.facility.FacilityFragment
import kr.co.lion.application.finalproject_aparttalk.ui.facility.viewmodel.FacilityViewmodel
import kr.co.lion.application.finalproject_aparttalk.ui.home.fragment.HomeFragment
import kr.co.lion.application.finalproject_aparttalk.ui.location.LocationFragment
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private var backPressedOnce = false
    private val backPressHandler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }

    // 현재 선택된 Fragment ID 저장
    private var currentSelectedItemId: Int? = null

    private val viewModel: FacilityViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNaviClick()
        initView()

        onBackProcess()

        viewModel.getFacilityInfoData()
    }

    private fun onBackProcess() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    finish()
                } else {
                    backPressedOnce = true
                    Toast.makeText(this@MainActivity, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                    backPressHandler.postDelayed(backPressRunnable, 2000)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressHandler.removeCallbacks(backPressRunnable)
    }

    private fun initView(){
        replaceFragment(MainFragmentName.HOME_FRAGMENT, false, null)
        binding.bottomNavi.selectedItemId = R.id.home_item
    }



    //bottomNavi 클릭 이벤트
    private fun bottomNaviClick(){
        binding.bottomNavi.setOnItemSelectedListener {

            // 현재 선택된 아이템과 동일한 경우 아무 작업도 하지 않음
            if (currentSelectedItemId == it.itemId) {
                return@setOnItemSelectedListener true
            }

            when(it.itemId){
                R.id.community_item -> {
                    replaceFragment(MainFragmentName.COMMUNITY_FRAGMENT, false, null)
                    true
                }
                R.id.location_item -> {
                    replaceFragment(MainFragmentName.LOCATION_FRAGMENT, false, null)
                    true
                }
                R.id.home_item -> {
                    replaceFragment(MainFragmentName.HOME_FRAGMENT, false, null)
                    true
                }
                R.id.facility_item -> {
                    replaceFragment(MainFragmentName.FACILITY_FRAGMENT, false, null)
                    true
                }
                R.id.entiremenu_item -> {
                    replaceFragment(MainFragmentName.ENTIRE_MENU_FRAGMENT,false,null)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    //Fragment 교체
    fun replaceFragment(name: MainFragmentName, addToBackStack: Boolean, data: Bundle?) {
        // 현재 표시된 프래그먼트와 같은 경우 아무 작업도 하지 않음
        if (currentSelectedItemId == name.id) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 전환 애니메이션 추가
        fragmentTransaction.setCustomAnimations(
            android.R.anim.fade_in,  // 들어올 때
            android.R.anim.fade_out, // 나갈 때
            android.R.anim.fade_in,  // 다시 들어올 때 (뒤로가기)
            android.R.anim.fade_out  // 다시 나갈 때 (뒤로가기)
        )

        val fragment = when (name) {
            MainFragmentName.COMMUNITY_FRAGMENT -> CommunityFragment()
            MainFragmentName.LOCATION_FRAGMENT -> LocationFragment()
            MainFragmentName.HOME_FRAGMENT -> HomeFragment()
            MainFragmentName.FACILITY_FRAGMENT -> FacilityFragment()
            MainFragmentName.ENTIRE_MENU_FRAGMENT -> EntireMenuFragment()
        }

        if (data != null) {
            fragment.arguments = data
        }

        fragmentTransaction.replace(R.id.main_container, fragment)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(name.str)
        }
        fragmentTransaction.commit()

        // 현재 표시된 프래그먼트 이름 업데이트
        currentSelectedItemId = name.id
        binding.bottomNavi.selectedItemId = name.id
    }

    fun removeFragment() {
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
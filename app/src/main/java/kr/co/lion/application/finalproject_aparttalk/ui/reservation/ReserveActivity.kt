package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityReserveBinding
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReserveActivity : AppCompatActivity() {
    lateinit var activityReserveBinding: ActivityReserveBinding

    // 프래그먼트의 주소값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityReserveBinding = ActivityReserveBinding.inflate(layoutInflater)
        setContentView(activityReserveBinding.root)

        // FireCheckMainFragment 나타나도록 한다.
        replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: ReserveFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        when (name) {
            ReserveFragmentName.RESERVATION_FRAGMENT -> {
                newFragment = ReservationFragment()
            }
            ReserveFragmentName.RESERVATION_CANCEL_FRAGMENT -> {
                newFragment = ReservationCancelFragment()
            }
            ReserveFragmentName.RESERVATION_COMPLETE_FRAGMENT -> {
                newFragment = ReservationCompleteFragment()
            }
            ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT -> {
                newFragment = ReservationConfirmFragment()
            }
            ReserveFragmentName.RESERVATION_CANCEL_COMPLETE_FRAGMENT -> {
                newFragment = ReservationCancelCompleteFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

        if (newFragment != null) {
            // 애니메이션 설정
            if (isAnimate) {
                if (oldFragment != null) {
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            fragmentTransaction.replace(R.id.reservationFragmentContainerView, newFragment!!)

            if (addToBackStack) {
                fragmentTransaction.addToBackStack(name.str)
            }

            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: ReserveFragmentName) {
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
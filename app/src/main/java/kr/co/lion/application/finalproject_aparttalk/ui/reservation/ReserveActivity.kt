package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityReserveBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.model.UserModel
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReserveActivity : AppCompatActivity() {
    lateinit var activityReservebinding: ActivityReserveBinding

    // 프래그먼트의 주소값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    // 선택한 유저 정보를 저장할 프로퍼티 추가
    private var selectedUser: UserModel? = null

    // 필요한 의존성 초기화
    private val localUserDataSource by lazy { LocalUserDataSource(this) } // LocalUserDataSource 초기화 방법 정의
    private val userDataSource by lazy { UserDataSource() } // UserDataSource 초기화 방법 정의
    private val userRepository by lazy { UserRepository(userDataSource, localUserDataSource) }

    // ViewModel 초기화
    private val userViewModel: ReservationUserViewModel by viewModels {
        ReservationUserViewModelFactory(userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityReservebinding = ActivityReserveBinding.inflate(layoutInflater)
        setContentView(activityReservebinding.root)

        // FireCheckMainFragment 나타나도록 한다.
        replaceFragment(ReserveFragmentName.RESERVATION_FRAGMENT, false, false, null)
    }

    // 선택한 유저 정보를 설정하는 메서드
    fun setSelectedUser(user: UserModel) {
        selectedUser = user
    }

    // 선택한 유저 정보를 반환하는 메서드
    fun getSelectedUser(): UserModel? {
        return selectedUser
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: ReserveFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?) {
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        when(name){
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
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){
            // 애니메이션 설정
            if(isAnimate){
                if(oldFragment != null){
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

            if(addToBackStack){
                fragmentTransaction.addToBackStack(name.str)
            }

            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: ReserveFragmentName){
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
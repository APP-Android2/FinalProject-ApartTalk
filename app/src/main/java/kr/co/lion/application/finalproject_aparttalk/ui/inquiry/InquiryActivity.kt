package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.transition.MaterialSharedAxis
import com.google.firebase.auth.FirebaseAuth
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityInquiryBinding
import kr.co.lion.application.finalproject_aparttalk.repository.InquiryRepository
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryActivity : AppCompatActivity() {

    lateinit var binding: ActivityInquiryBinding
    lateinit var viewModel: InquiryViewModel

    // 프래그먼트 객체를 담을 변수
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInquiryBinding.inflate(layoutInflater)

        // ViewModel 초기화
        val repository = InquiryRepository()
        val factory = InquiryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(InquiryViewModel::class.java)

        setContentView(binding.root)

        // 현재 로그인된 유저의 UID를 가져와 ViewModel에 설정
        val userUid = getLoggedInUserUid() // 로그인된 사용자의 UID를 가져오는 메서드
        viewModel.getUser(userUid) { user ->
            if (user != null) {
                viewModel.setUserModel(user)
                replaceFragment(InquiryFragmentName.INQUIRY_TAB_FRAGMENT, false, true, null)
            } else {
                // 사용자 정보가 null인 경우 처리
                handleUserNotFound()
            }
        }
    }

    private fun getLoggedInUserUid(): String {
        // 여기에 로그인된 사용자의 UID를 가져오는 로직을 추가하십시오
        // 예: SharedPreferences, FirebaseAuth 등
        return FirebaseAuth.getInstance().currentUser?.uid ?: ""
    }

    private fun handleUserNotFound() {
        // 사용자 정보가 없을 경우 처리하는 로직
        // 예: 로그인 화면으로 이동, 에러 메시지 표시 등
        // 여기에 코드를 추가합니다.
    }

    fun replaceFragment(name: InquiryFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        newFragment = when (name) {
            // 관리사무소 탭 화면
            InquiryFragmentName.INQUIRY_TAB_FRAGMENT -> InquiryTabFragment()
            // 관리사무소 문의 화면
            InquiryFragmentName.INQUIRY_FRAGMENT -> InquiryFragment().apply {
                if (data != null) {
                    arguments = data
                }
            }
            // 관리사무소 문의 작성 화면
            InquiryFragmentName.INQUIRY_WRITE_FRAGMENT -> InquiryWriteFragment()
            // 관리사무소 답변 완료 목록
            InquiryFragmentName.INQUIRY_COMPLETE_FRAGMENT -> InquiryCompleteFragment()
            // 관리사무소 문의 중 목록
            InquiryFragmentName.INQUIRING_FRAGMENT -> InquiringFragment()
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

            // Fragment를 교체한다.
            fragmentTransaction.replace(R.id.inquiryContainer, newFragment!!)

            if (addToBackStack) {
                fragmentTransaction.addToBackStack(name.str)
            }

            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: InquiryFragmentName) {
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

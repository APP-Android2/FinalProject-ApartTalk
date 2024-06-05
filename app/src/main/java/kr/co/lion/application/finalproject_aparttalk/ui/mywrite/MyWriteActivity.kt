package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.util.MyWriteFragmentName

class MyWriteActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyWriteBinding

    // 프래그먼트의 주소값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FireCheckMainFragment 나타나도록 한다.
        replaceFragment(MyWriteFragmentName.MY_WRITE_FRAGMENT, false, false, null)
    }

    // 사용자 ID를 반환하는 메서드
    fun getUserId(): Int {
        // 실제 사용자 ID를 반환하도록 구현
        return 123 // 예시로 123을 반환
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name: MyWriteFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {

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
            MyWriteFragmentName.MY_WRITE_FRAGMENT -> MyWriteFragment()
            MyWriteFragmentName.MY_WROTE_FRAGMENT -> MyWroteFragment().apply {
                arguments = Bundle().apply {
                    putInt("userId", getUserId())
                }
            }
            MyWriteFragmentName.MY_LIKE_FRAGMENT -> MyLikeFragment()
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

            // Fragment를 교체한다.
            fragmentTransaction.replace(R.id.myWriteFragmentContainerView, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(name.str)
            }

            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: MyWriteFragmentName) {
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityServiceBinding
import kr.co.lion.application.finalproject_aparttalk.util.InfoFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ServiceFragmentName

class ServiceActivity : AppCompatActivity() {
    lateinit var binding: ActivityServiceBinding

    // 프래그먼트의 주소값을 담을 프로퍼티
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FireCheckMainFragment 나타나도록 한다.
        replaceFragment(ServiceFragmentName.SERVICE_FRAGMENT, false, false, null)
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(
        name: ServiceFragmentName,
        addToBackStack: Boolean,
        isAnimate: Boolean,
        data: Bundle?
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (newFragment != null) {
            oldFragment = newFragment
        }

        newFragment = when (name) {
            ServiceFragmentName.SERVICE_FRAGMENT -> ServiceFragment()
            ServiceFragmentName.SINGLE_SERVICE_FRAGMENT -> SingleServiceFragment()
            ServiceFragmentName.VIEW_MY_Q_FRAGMENT -> ViewMyQFragment()
            ServiceFragmentName.F_A_Q_FRAGMENT -> FAQFragment()
            ServiceFragmentName.MY_Q_FRAGMENT -> MyQFragment()
            ServiceFragmentName.ANNOUNCEMENT_FRAGMENT -> AnnouncementFragment()
            ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT -> ViewAnnouncementFragment()
            ServiceFragmentName.VIEW_FAQ_FRAGMENT -> ViewFAQFragment()
            ServiceFragmentName.VIEW_MYQ_FRAGMENT -> ViewMyQFragment()
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

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

        fragmentTransaction.replace(R.id.serviceFragmentContainerView, newFragment!!)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(name.str)
        }

        fragmentTransaction.commit()
    }

    // Fragment를 추가하는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun addFragment(
        name: ServiceFragmentName,
        addToBackStack: Boolean,
        isAnimate: Boolean,
        data: Bundle?
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        newFragment = when (name) {
            ServiceFragmentName.SERVICE_FRAGMENT -> ServiceFragment()
            ServiceFragmentName.SINGLE_SERVICE_FRAGMENT -> SingleServiceFragment()
            ServiceFragmentName.VIEW_MY_Q_FRAGMENT -> ViewMyQFragment()
            ServiceFragmentName.F_A_Q_FRAGMENT -> FAQFragment()
            ServiceFragmentName.MY_Q_FRAGMENT -> MyQFragment()
            ServiceFragmentName.ANNOUNCEMENT_FRAGMENT -> AnnouncementFragment()
            ServiceFragmentName.VIEW_ANNOUNCEMENT_FRAGMENT -> ViewAnnouncementFragment()
            ServiceFragmentName.VIEW_FAQ_FRAGMENT -> ViewFAQFragment()
            ServiceFragmentName.VIEW_MYQ_FRAGMENT -> ViewMyQFragment()
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

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

        fragmentTransaction.add(R.id.serviceFragmentContainerView, newFragment!!)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(name.str)
        }

        fragmentTransaction.commit()
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: ServiceFragmentName) {
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    // Fragment 인스턴스를 직접 받아 교체하는 메서드 추가
    fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean,
        isAnimate: Boolean,
        tag: String?
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (newFragment != null) {
            oldFragment = newFragment
        }

        newFragment = fragment

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

        fragmentTransaction.replace(R.id.serviceFragmentContainerView, newFragment!!)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityMyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityAddFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityDetailFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunityModifyFragment
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.CommunitySearchFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
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

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name: Any, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {
        SystemClock.sleep(200)

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (newFragment != null) {
            oldFragment = newFragment
        }

        newFragment = when (name) {
            is MyWriteFragmentName -> when (name) {
                MyWriteFragmentName.MY_WRITE_FRAGMENT -> MyWriteFragment()
                MyWriteFragmentName.MY_WROTE_FRAGMENT -> MyWroteFragment()
                MyWriteFragmentName.MY_LIKE_FRAGMENT -> MyLikeFragment()
            }
            is CommunityFragmentName -> when (name) {
                CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT -> CommunityDetailFragment(data)
                CommunityFragmentName.COMMUNITY_ADD_FRAGMENT -> CommunityAddFragment(data)
                CommunityFragmentName.COMMUNITY_MODIFY_FRAGMENT -> CommunityModifyFragment(data)
                CommunityFragmentName.COMMUNITY_SEARCH_FRAGMENT -> CommunitySearchFragment()
                // 필요한 다른 CommunityFragment들을 여기에 추가
            }
            else -> throw IllegalArgumentException("Unknown fragment name: $name")
        }

        if (data != null) {
            newFragment?.arguments = data
        }

        if (newFragment != null) {
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

            fragmentTransaction.replace(R.id.myWriteFragmentContainerView, newFragment!!)

            if (addToBackStack) {
                fragmentTransaction.addToBackStack(name.toString())
            }

            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: MyWriteFragmentName) {
        SystemClock.sleep(200)
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
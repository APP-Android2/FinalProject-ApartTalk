package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R

import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityBinding
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.CommunityTabFragmentName

class CommunityFragment : Fragment() {

    lateinit var binding:FragmentCommunityBinding
    lateinit var mainActivity: MainActivity

    // 프래그먼트 객체를 담을 변수
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommunityBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingTabLayoutCommunity()

        replaceFragment(CommunityTabFragmentName.TAB_NOTIFICATION_FRAGMENT, false, false, null, R.id.containerCommunityTab)

        return binding.root
    }

    // 툴바
    private fun settingToolbar() {
        binding.apply {
            toolbarCommunity.apply {
                inflateMenu(R.menu.menu_community)
                setOnMenuItemClickListener {
                    when(it.itemId) {
                        // 검색 버튼
                        R.id.menuItemCommunitySearch -> {
                            val intent = Intent(mainActivity, CommunityActivity::class.java)
                            intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_SEARCH_FRAGMENT)
                            startActivity(intent)
                        }
                    }
                    true
                }
            }
        }
    }

    // 탭 설정
    private fun settingTabLayoutCommunity() {
        binding.apply {
            tabLayoutCommunity.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        // 공지 탭
                        0 -> {
                            replaceFragment(CommunityTabFragmentName.TAB_NOTIFICATION_FRAGMENT, false, false, null, R.id.containerCommunityTab)
                        }
                        // 질문 탭
                        1 -> {
                            replaceFragment(CommunityTabFragmentName.TAB_QUESTION_FRAGMENT, false, false, null, R.id.containerCommunityTab)
                        }
                        // 거래 탭
                        2 -> {
                            replaceFragment(CommunityTabFragmentName.TAB_TRADE_FRAGMENT, false, false, null, R.id.containerCommunityTab)
                        }
                        // 기타 탭
                        3 -> {
                            replaceFragment(CommunityTabFragmentName.TAB_ETC_FRAGMENT, false, false, null, R.id.containerCommunityTab)
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // 탭이 선택되지 않았을 때
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // 탭이 다시 선택되었을 때
                }
            })
        }
    }

    fun replaceFragment(name: CommunityTabFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?, container:Int = R.id.containerCommunityTab){
        SystemClock.sleep(200)
        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name){
            CommunityTabFragmentName.TAB_NOTIFICATION_FRAGMENT -> {
                newFragment = TabNotificationFragment()
            }
            CommunityTabFragmentName.TAB_QUESTION_FRAGMENT -> {
                newFragment = TabQuestionFragment()
            }
            CommunityTabFragmentName.TAB_TRADE_FRAGMENT -> {
                newFragment = TabTradeFragment()
            }
            CommunityTabFragmentName.TAB_ETC_FRAGMENT -> {
                newFragment = TabEtcFragment()
            }
        }
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){
            // 애니메이션 설정
            if(isAnimate){
                // oldFragment -> newFragment
                // oldFragment : exit
                // newFragment : enter

                // newFragment -> oldFragment
                // oldFragment : reenter
                // newFragment : return

                // MaterialSharedAxis : 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
                // X - 좌우
                // Y - 위아래
                // Z - 공중 바닥
                // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지 여부를 설정해준다.
                // true : 새로운 화면이 나타나는 애니메이션이 동작한다.
                // false : 이전으로 되돌아가는 애니메이션이 동작한다.
                if(oldFragment != null){
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // new에서 old로 되돌아갈때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }
            // Fragment를 교체한다.(이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            // 첫 번째 매개 변수 : Fragment를 배치할 FragmentContainerView의 ID
            // 두 번째 매개 변수 : 보여주고하는 Fragment 객체를
            fragmentTransaction.replace(container, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if(addToBackStack){
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: CommunityTabFragmentName){
        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        SystemClock.sleep(200)
        mainActivity.supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
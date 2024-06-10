package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryTabBinding
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryTabFragment : Fragment() {

    private lateinit var fragmentInquiryTabBinding: FragmentInquiryTabBinding
    private lateinit var inquiryActivity: InquiryActivity

    // 프래그먼트 객체를 담을 변수
    private var oldFragment: Fragment? = null
    private var newFragment: Fragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInquiryTabBinding = FragmentInquiryTabBinding.inflate(inflater, container, false)
        inquiryActivity = activity as InquiryActivity

        inquiryTabToolbar()
        inquiryTabLayout()
//        setupSearchView()

        // 기본 탭을 선택하고 해당 프래그먼트를 표시
        selectDefaultTab()

        return fragmentInquiryTabBinding.root
    }

    // 툴바 설정
    private fun inquiryTabToolbar() {
        fragmentInquiryTabBinding.apply {
            inquiryTapToolbar.apply {
                //title
                title = "관리사무소 문의"
                // back
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    inquiryActivity.finish()
                }
            }
        }
    }

    // tabLayout 설정
    private fun inquiryTabLayout() {
        fragmentInquiryTabBinding.apply {
            inquiryTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.position) {
                        // 문의 중
                        0 -> {
                            replaceFragment(InquiryFragmentName.INQUIRING_FRAGMENT, false, false, null)
                        }
                        // 답변 완료
                        1 -> {
                            replaceFragment(InquiryFragmentName.INQUIRY_COMPLETE_FRAGMENT, false, false, null)
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

//    // 서치뷰 설정
//    private fun setupSearchView() {
//        fragmentInquiryTabBinding.search.isSubmitButtonEnabled = true
//        fragmentInquiryTabBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // 검색 버튼이 눌렸을 때의 동작을 정의합니다.
//                query?.let {
//                    searchInquiries(it)
//                }
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // 입력된 텍스트가 변경될 때마다 호출
//                newText?.let {
//                    searchInquiries(it)
//                }
//                return true
//            }
//        })
//    }
//
//    // 검색 쿼리를 처리하는 메서드
//    private fun searchInquiries(query: String) {
//        // 현재 표시된 프래그먼트를 찾아서 검색 쿼리를 전달
//        val currentFragment = childFragmentManager.findFragmentById(R.id.inquiryFrameLayout)
//        if (currentFragment is InquiringFragment) {
//            currentFragment.searchInquiries(query)
//        } else if (currentFragment is InquiryCompleteFragment) {
//            currentFragment.searchInquiries(query)
//        }
//    }

    // 기본 탭을 선택하고 해당 프래그먼트를 표시
    private fun selectDefaultTab() {
        fragmentInquiryTabBinding.inquiryTabLayout.getTabAt(0)?.select()
        replaceFragment(InquiryFragmentName.INQUIRING_FRAGMENT, false, false, null)
    }

    private fun replaceFragment(name: InquiryFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

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
            InquiryFragmentName.INQUIRY_FRAGMENT -> InquiringFragment()
            // 관리사무소 문의 작성 화면
            InquiryFragmentName.INQUIRY_WRITE_FRAGMENT -> InquiryWriteFragment()
            // 관리사무소 답변 완료 목록
            InquiryFragmentName.INQUIRY_COMPLETE_FRAGMENT -> InquiryCompleteFragment()
            // 관리사무소 문의 중 목록
            InquiryFragmentName.INQUIRING_FRAGMENT -> InquiringFragment()
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

        if (newFragment != null) {
            // 애니메이션 설정
            if (isAnimate) {
                if (oldFragment != null) {
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // new에서 old로 되돌아갈 때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈 때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            // Fragment를 교체한다.(이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            fragmentTransaction.replace(R.id.inquiryFrameLayout, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if (addToBackStack) {
                // BackStack 포함 시킬 때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }

            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    private fun removeFragment(name: InquiryFragmentName) {
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        requireActivity().supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityVoteBinding
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class VoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityVoteBinding

    // 프래그먼트 객체를 담을 변수
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, false, null)

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.voteContainer)

        if (currentFragment is SurveyCompleteFragment) {
            replaceFragment(VoteFragmentName.VOTE_TAB_FRAGMENT, false, true, null)
        } else {
            super.onBackPressed()
        }
    }

    fun replaceFragment(name: VoteFragmentName, addToBackStack: Boolean, isAnimate: Boolean, data: Bundle?) {

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when (name) {
            // 투표 탭화면
            VoteFragmentName.VOTE_TAB_FRAGMENT -> {
                newFragment = VoteTabFragment()
            }
            // 투표 화면
            VoteFragmentName.VOTE_FRAGMENT -> {
                newFragment = VoteFragment()
            }
            // 투표 목록
            VoteFragmentName.VOTE_LIST_FRAGMENT -> {
                newFragment = VoteListFragment()
            }
            // 투표 내역
            VoteFragmentName.VOTE_HISTORY_FRAGMENT -> {
                newFragment = VoteHistoryFragment()
            }
            // 설문조사 투표 화면
            VoteFragmentName.SURVEY_FRAGMENT -> {
                newFragment = SurveyFragment()
            }
            // 설문조사 목록
            VoteFragmentName.SURVEY_LIST_FRAGMENT -> {
                newFragment = SurveyListFragment()
            }
            // 설문조사 작성
            VoteFragmentName.SURVEY_WRITE_FRAGMENT -> {
                newFragment = SurveyWriteFragment()
            }
            // 설문조사 완료
            VoteFragmentName.SURVEY_COMPLETE_FRAGMENT -> {
                newFragment = SurveyCompleteFragment()
            }
        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if (data != null) {
            newFragment?.arguments = data
        }

        if (newFragment != null) {

            // 애니메이션 설정
            if (isAnimate) {
                // oldFragment -> newFragment
                // oldFragment : exitTransition
                // newFragment : enterTransition

                // newFragment -> oldFragment
                // oldFragment : reenterTransition
                // newFragment : returnTransition

                // MaterialSharedAxis : 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
                // X - 좌우
                // Y - 위아래
                // Z - 공중 바닥
                // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지 여부를 설정해준다.
                // true : 새로운 화면이 나타나는 애니메이션이 동작한다.
                // false : 이전으로 되돌아가는 애니메이션이 동작한다.

                if (oldFragment != null) {
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
            fragmentTransaction.replace(R.id.voteContainer, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if (addToBackStack) {
                // BackStack 포함 시킬때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name: VoteFragmentName) {
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

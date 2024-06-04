package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireCheckMainBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.util.FireCheckFragmentName

class FireCheckMainFragment : Fragment() {

    lateinit var fragmentFireCheckMainBinding : FragmentFireCheckMainBinding
    lateinit var fireCheckActivity: FireCheckActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentFireCheckMainBinding = FragmentFireCheckMainBinding.inflate(layoutInflater)
        fireCheckActivity = activity as FireCheckActivity

        setToolbar()
        setYoutube()
        setEvent()

        return fragmentFireCheckMainBinding.root
    }

    fun setToolbar(){
        fragmentFireCheckMainBinding.apply {
            toolbarFireCheck1.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    fireCheckActivity.removeFragment(FireCheckFragmentName.FIRE_CHECK_MAIN_FRAGMENT)
                    fireCheckActivity.finish()
                }
            }
        }
    }
    fun setEvent(){
        fragmentFireCheckMainBinding.apply {
            cardViewFireLaw.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val fireLawBottomSheetFragment = FireLawBottomSheetFragment()
                fireLawBottomSheetFragment.show(childFragmentManager, fireLawBottomSheetFragment.tag)
            }
            cardViewFireCheck2.setOnClickListener {
                // 프래그먼트 나타나게 한다.
                fireCheckActivity.replaceFragment(FireCheckFragmentName.FIRE_CHECK_SELF_FRAGMENT, true, false, null)
            }
        }
    }

    // 영상 재생 설정
    fun setYoutube() {
        // YouTubePlayerView 요소를 fragmentFireCheckMainBinding에서 찾아옵니다.
        val youtubePlayerView: YouTubePlayerView = fragmentFireCheckMainBinding.youtubePlayerFireCheck
        // 액티비티 또는 프래그먼트의 라이프사이클을 관찰하여
        // YouTubePlayerView를 라이프사이클 이벤트와 동기화하도록 설정합니다.
        lifecycle.addObserver(youtubePlayerView)
        // YouTubePlayerListener를 구현한 익명 클래스를 생성하고,
        // YouTubePlayerView에 이 리스너를 추가합니다.
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            // YouTube 플레이어가 준비되면 호출되는 콜백 메서드입니다.
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // 재생할 동영상의 ID를 설정합니다.
                val videoId = "Ry3_FjO4wOU"

                // 설정한 동영상 ID로 동영상을 로드하고 재생합니다.
                // 시작 시간을 0초로 설정하여 동영상을 처음부터 재생합니다.
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })
    }
}
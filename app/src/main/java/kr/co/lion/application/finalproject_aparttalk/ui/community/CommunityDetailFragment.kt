package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityDetailBinding

class CommunityDetailFragment(data: Bundle?) : Fragment() {
    lateinit var fragmentCommunityDetailBinding: FragmentCommunityDetailBinding
    lateinit var communityActivity: CommunityActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentCommunityDetailBinding = FragmentCommunityDetailBinding.inflate(inflater)
        communityActivity = activity as CommunityActivity

        return fragmentCommunityDetailBinding.root
    }


}
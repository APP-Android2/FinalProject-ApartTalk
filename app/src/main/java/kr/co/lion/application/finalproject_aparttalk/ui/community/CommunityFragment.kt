package kr.co.lion.application.finalproject_aparttalk.ui.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.MainActivity

import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    lateinit var binding:FragmentCommunityBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommunityBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        return binding.root
    }
}
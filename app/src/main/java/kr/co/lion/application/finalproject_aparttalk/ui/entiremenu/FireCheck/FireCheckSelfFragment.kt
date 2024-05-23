package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFireCheckSelfBinding

class FireCheckSelfFragment : Fragment() {

    lateinit var fragmentFireCheckSelfBinding: FragmentFireCheckSelfBinding
    lateinit var fireCheckActivity: FireCheckActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentFireCheckSelfBinding = FragmentFireCheckSelfBinding.inflate(layoutInflater)
        fireCheckActivity = activity as FireCheckActivity

        return fragmentFireCheckSelfBinding.root
    }
}
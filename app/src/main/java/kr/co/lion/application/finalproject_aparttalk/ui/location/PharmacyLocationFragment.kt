package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentPharmacyLocationBinding

class PharmacyLocationFragment : Fragment() {

    lateinit var binding: FragmentPharmacyLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentPharmacyLocationBinding.inflate(layoutInflater)
        return binding.root
    }
}
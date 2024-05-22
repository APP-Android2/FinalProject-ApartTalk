package kr.co.lion.application.finalproject_aparttalk.ui.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentLocationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.location.adapter.LocationAdapter

class LocationFragment : Fragment() {

    lateinit var binding:FragmentLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLocationBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }


    private fun initView(){
        val locationViewPager = binding.viewPagerLocation
        val locationTabLayout = binding.tabLocation

        //Fragment 추가
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(AllLocationFragment())
        fragmentList.add(FoodLocationFragment())
        fragmentList.add(HospitalLacationFragment())
        fragmentList.add(PharmacyLocationFragment())
        fragmentList.add(CafeLocationFragment())

        locationViewPager.adapter = LocationAdapter(fragmentList, requireActivity())

        //tabLayout 텍스트 추가
        val tabTextList = arrayOf<String?>("전체", "음식점", "병원", "약국", "카페")

        locationViewPager.post {
            TabLayoutMediator(locationTabLayout, locationViewPager){ tab, position ->
                tab.text = tabTextList[position]

            }.attach()
        }
    }
}
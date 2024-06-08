package kr.co.lion.application.finalproject_aparttalk.ui.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.google.android.material.tabs.TabLayout
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInfoBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentServiceBinding
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class ServiceFragment : Fragment() {

    lateinit var tab1: AnnouncementFragment
    lateinit var tab2: FAQFragment
    lateinit var tab3: MyQFragment

    lateinit var fragmentServiceBinding: FragmentServiceBinding
    lateinit var serviceActivity: ServiceActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentServiceBinding = FragmentServiceBinding.inflate(inflater, container, false)
        serviceActivity = activity as ServiceActivity

        settingTabLayout()
        settingToolbar()

        return fragmentServiceBinding.root
    }

    private fun settingTabLayout() {
        fragmentServiceBinding.apply {
            serviceTabLayout.apply {

                tab1 = AnnouncementFragment()
                tab2 = FAQFragment()
                tab3 = MyQFragment()

                childFragmentManager.beginTransaction().replace(R.id.serviceFrameLayout, tab1).commit()

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        when (tab?.position) {
                            0 -> childFragmentManager.beginTransaction()
                                .replace(R.id.serviceFrameLayout, tab1).commit()
                            1 -> childFragmentManager.beginTransaction()
                                .replace(R.id.serviceFrameLayout, tab2).commit()
                            2 -> childFragmentManager.beginTransaction()
                                .replace(R.id.serviceFrameLayout, tab3).commit()
                        }
                    }

                    override fun onTabUnselected(p0: TabLayout.Tab?) {}
                    override fun onTabReselected(p0: TabLayout.Tab?) {}
                })
            }
        }
    }

    private fun settingToolbar() {
        fragmentServiceBinding.apply {
            serviceToolbar.apply {
                textViewServiceToolbarTitle.text = "고객센터"
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    serviceActivity.finish()
                }
            }
        }
    }
}



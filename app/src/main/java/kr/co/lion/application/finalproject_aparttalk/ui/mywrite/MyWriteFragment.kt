package kr.co.lion.application.finalproject_aparttalk.ui.mywrite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentServiceBinding
import kr.co.lion.application.finalproject_aparttalk.ui.service.AnnouncementFragment
import kr.co.lion.application.finalproject_aparttalk.ui.service.FAQFragment
import kr.co.lion.application.finalproject_aparttalk.ui.service.MyQFragment
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName


class MyWriteFragment : Fragment() {


    lateinit var tab1 : MyWroteFragment
    lateinit var tab2 : MyLikeFragment

    lateinit var fragmentMyWriteBinding : FragmentMyWriteBinding
    lateinit var myWriteActivity : MyWriteActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentMyWriteBinding = FragmentMyWriteBinding.inflate(layoutInflater)
        myWriteActivity = activity as MyWriteActivity

        settingTabLayout()
        settingToolbar()

        return fragmentMyWriteBinding.root
    }

    fun settingTabLayout(){
        fragmentMyWriteBinding.apply {
            myWriteTabLayout.apply {

                tab1 = MyWroteFragment()
                tab2 = MyLikeFragment()

                childFragmentManager.beginTransaction().replace(R.id.myWriteFrameLayout, tab1). commit()

                myWriteTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                    override fun onTabSelected(tab: TabLayout.Tab?) {

                        when (tab?.position) {
                            0 -> {
                                childFragmentManager.beginTransaction()
                                    .replace(R.id.myWriteFrameLayout, tab1).commit()
                            }

                            1 -> {
                                childFragmentManager.beginTransaction()
                                    .replace(R.id.myWriteFrameLayout, tab2).commit()
                            }

                        }
                    }


                    override fun onTabUnselected(p0: TabLayout.Tab?) {
                    }


                    override fun onTabReselected(p0: TabLayout.Tab?) {

                    }
                })
            }
        }
    }

    fun settingToolbar(){
        fragmentMyWriteBinding.apply {
            myWriteToolbar.apply {
                textViewMyWriteToolbarTitle.text = "내가 쓴글"
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    myWriteActivity.finish()
                }
            }
        }
    }


}
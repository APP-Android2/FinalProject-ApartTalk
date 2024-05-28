package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentMyWriteBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationBinding
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyLikeFragment
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWriteActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWroteFragment
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class ReservationFragment : Fragment() {

    lateinit var tab1 : ReservationCompleteFragment
    lateinit var tab2 : ReservationCancelFragment

    lateinit var fragmentReservationBinding : FragmentReservationBinding
    lateinit var reserveActivity : ReserveActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentReservationBinding = FragmentReservationBinding.inflate(layoutInflater)
        reserveActivity = activity as ReserveActivity

        settingTabLayout()
        settingToolbar()

        return fragmentReservationBinding.root
    }

    fun settingTabLayout(){
        fragmentReservationBinding.apply {
            reservationTabLayout.apply {

                tab1 = ReservationCompleteFragment()
                tab2 = ReservationCancelFragment()

                childFragmentManager.beginTransaction().replace(R.id.reservationFrameLayout, tab1). commit()

                reservationTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                    override fun onTabSelected(tab: TabLayout.Tab?) {

                        when (tab?.position) {
                            0 -> {
                                childFragmentManager.beginTransaction()
                                    .replace(R.id.reservationFrameLayout, tab1).commit()
                            }

                            1 -> {
                                childFragmentManager.beginTransaction()
                                    .replace(R.id.reservationFrameLayout, tab2).commit()
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
        fragmentReservationBinding.apply {
            reservationToolbar.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기.
                    reserveActivity.finish()
                }
            }
        }
    }

}
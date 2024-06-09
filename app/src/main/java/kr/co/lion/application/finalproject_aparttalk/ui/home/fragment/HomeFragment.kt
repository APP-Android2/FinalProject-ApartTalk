package kr.co.lion.application.finalproject_aparttalk.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentHomeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.ui.home.AlarmActivity
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.HomeNotificationRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.info.InfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.inquiry.InquiryActivity
import kr.co.lion.application.finalproject_aparttalk.ui.parking.ParkingActivity
import kr.co.lion.application.finalproject_aparttalk.ui.vote.VoteActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        settingRecyclerViewHomeNotification()
        settingEvent()
        initView()

        return binding.root
    }

    // 홈 공지 리사이클러뷰 설정
    private fun settingRecyclerViewHomeNotification() {
        binding.apply {
            homeNotificationRecyclerView.apply {
                adapter = HomeNotificationRecyclerView(requireContext())
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }
    }

    //화면 설정
    private fun initView(){
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                val authUser = App.authRepository.getCurrentUser()
                if (authUser != null){
                    val user = App.userRepository.getUser(authUser.uid)
                    if (user != null){
                        val apart = App.apartmentRepository.getApartment(user.uid)

                        textHomeAddress.text = apart?.address
                        textHomeApt.text = apart?.name
                        textHomeHouse.text = "${apart?.totalHouseholds}세대"
                        textHomeParking.text = "${apart?.totalCarParked}대"
                        textHomeMoveIn.text = apart?.moveIn
                        textHomeSize.text = apart?.sizeOfComplex
                    }
                }
            }
        }
    }

    //클릭 이벤트
    private fun settingEvent(){
        binding.apply {
            homeAllMyPageLayout.setOnClickListener {
                startActivity(Intent(requireActivity(), InfoActivity::class.java))
            }
            homeAllVisitCarLayout.setOnClickListener {
                //방문 주차 예약
                startActivity(Intent(requireActivity(), ParkingActivity::class.java))
            }
            homeAllVoteLayout.setOnClickListener {
                //주민 투표
                startActivity(Intent(requireActivity(), VoteActivity::class.java))
            }
            homeAllManagementOfficeLayout.setOnClickListener {
                //관리사무소 문의로 이동
                startActivity(Intent(requireActivity(), InquiryActivity::class.java))
            }
            homeAllAnnouncementLayout.setOnClickListener {
                //안내방송
                val intent = Intent(requireActivity(), BroadcastActivity::class.java)
                intent.putExtra("fragmentName", BroadcastFragmentName.BROADCAST_FRAGMENT)
                startActivity(intent)
            }
            homeAllAptScheduleLayout.setOnClickListener {
                //아파트 일정
                startActivity(Intent(requireActivity(), AptScheduleActivity::class.java))
            }
            homeAllFireLayout.setOnClickListener {
                //소방
                startActivity(Intent(requireActivity(), FireCheckActivity::class.java))
            }
            homeAllAptInfoLayout.setOnClickListener {
                //아파트 운영
                startActivity(Intent(requireActivity(), OperationInfoActivity::class.java))
            }
            imageToAlarm.setOnClickListener {
                //알림
                startActivity(Intent(mainActivity, AlarmActivity::class.java))
            }

            cardViewRestaurant.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("tabPosition", 1) // 음식점
                mainActivity.replaceFragment(MainFragmentName.LOCATION_FRAGMENT, false, bundle)
            }
            cardViewHospital.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("tabPosition", 2) // 병원
                mainActivity.replaceFragment(MainFragmentName.LOCATION_FRAGMENT, false, bundle)
            }
            cardViewPharmacy.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("tabPosition", 3) // 약국
                mainActivity.replaceFragment(MainFragmentName.LOCATION_FRAGMENT, false, bundle)
            }
            cardViewCafe.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("tabPosition", 4) // 카페
                mainActivity.replaceFragment(MainFragmentName.LOCATION_FRAGMENT, false, bundle)
            }
        }
    }

}
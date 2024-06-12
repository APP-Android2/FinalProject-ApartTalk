package kr.co.lion.application.finalproject_aparttalk.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentHomeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityNotificationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.ui.home.AlarmActivity
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.HomeAptScheduleRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.home.adapter.HomeNotificationRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.info.InfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.inquiry.InquiryActivity
import kr.co.lion.application.finalproject_aparttalk.ui.parking.ParkingActivity
import kr.co.lion.application.finalproject_aparttalk.ui.vote.VoteActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.MainFragmentName
import java.util.Calendar

class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    lateinit var mainActivity: MainActivity
    private val viewModel: CommunityNotificationViewModel by viewModels()

    private val aptViewModel: AptScheduleViewModel by viewModels()
    private lateinit var homeAptScheduleRecyclerView: HomeAptScheduleRecyclerView

    // 선택된 날짜를 저장할 변수
    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        gettingCommunityPostList()
        settingRecyclerViewHomeNotification()
        settingEvent()
        initView()
        setRecyclerAptSchedule()

        // 코루틴을 사용하여 gettingApartId 호출 및 setCalendarView 설정
        GlobalScope.launch(Dispatchers.Main) {
            setCalendarView(gettingApartId())
        }

        return binding.root
    }

    // 게시글 리스트 받아오기
    private fun gettingCommunityPostList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunityNotificationList(gettingApartId())
            if (viewModel.notificationList.isNotEmpty()) {
                binding.homeNotificationRecyclerView.adapter?.notifyDataSetChanged()
            } else {
                Log.d("hyuun", "Notification list is empty.")
            }
        }
    }

    // 홈 공지 리사이클러뷰 설정
    private fun settingRecyclerViewHomeNotification() {
        binding.apply {
            homeNotificationRecyclerView.apply {
                adapter = HomeNotificationRecyclerView(requireContext(), viewModel)
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

    // 아파트 아이디 가져오기
    suspend fun gettingApartId(): String {
        var apartmentId = ""
        try {
            val authUser = App.authRepository.getCurrentUser()
            if (authUser != null) {
                val user = App.userRepository.getUser(authUser.uid)
                if (user != null) {
                    val apartment = App.apartmentRepository.getApartment(user.apartmentUid)
                    apartmentId = apartment!!.uid
                }
            }
        } catch (e: Exception) {
            //Log.e("BiddingNoticeFragment", "Error fetching apartment ID", e)
        }
        //Log.d("BiddingNoticeFragment", "Apartment ID: $apartmentId")
        return apartmentId
    }

    // 캘린더뷰 설정
    private fun setCalendarView(apartmentUid: String) {

        // 현재 날짜를 "yyyy-MM-dd" 형식의 문자열로 저장합니다.
        val calendar = Calendar.getInstance()
        selectedDate = String.format("%04d-%02d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))

        getSelectedDateDate(apartmentUid)

        binding.homeCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // 사용자가 선택한 날짜를 "yyyy-MM-dd" 형식의 문자열로 저장합니다.
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

            getSelectedDateDate(apartmentUid)

            // ScheduleType을 숨기고 ScheduleList를 표시합니다.
            binding.linearLayoutScheduleType.visibility = View.GONE
            binding.linearLayoutScheduleList.visibility = View.VISIBLE
        }
    }

    // 해당 날짜의 일정을 가져옵니다.
    fun getSelectedDateDate(apartmentUid: String){
        // selectedDate가 null이 아닌 경우에만 ViewModel을 통해 해당 날짜의 일정을 가져옵니다.
        selectedDate?.let { date ->
            // 코루틴을 사용하여 gettingSelectedDateList를 호출합니다.
            GlobalScope.launch(Dispatchers.Main) {
                val selectedDateList = aptViewModel.gettingSelectedDateList(apartmentUid, date)
                // 선택된 날짜의 일정 목록을 어댑터에 설정합니다.。。
                homeAptScheduleRecyclerView.setScheduleList(selectedDateList)
            }
        }
    }

    // recyclerView 설정
    fun setRecyclerAptSchedule() {
        homeAptScheduleRecyclerView = HomeAptScheduleRecyclerView()
        binding.recyclerViewAptScheduleList.apply {
            // 어댑터 설정
            adapter = homeAptScheduleRecyclerView
            // 레이아웃 매니저 설정
            layoutManager = LinearLayoutManager(requireContext())
            // 데코
            val deco = MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }

}
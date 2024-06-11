package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEntireMenuBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.ui.info.InfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.inquiry.InquiryActivity
import kr.co.lion.application.finalproject_aparttalk.ui.login.LoginActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWriteActivity
import kr.co.lion.application.finalproject_aparttalk.ui.parking.ParkingActivity
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.ui.service.ServiceActivity
import kr.co.lion.application.finalproject_aparttalk.ui.vote.VoteActivity
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class EntireMenuFragment : Fragment() {

    lateinit var binding:FragmentEntireMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntireMenuBinding.inflate(layoutInflater)

        settingEvent()
        extraEvent()
        settingToolbar()
        return binding.root
    }

    //툴바 설정
    private fun settingToolbar(){
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                val authUser = App.authRepository.getCurrentUser()
                if (authUser != null){
                    val user = App.userRepository.getUser(authUser.uid)
                    if (user != null){
                        textNameMenu.text = user.name

                        if (user.apartCertification == true){
                            textUserCheckMenu.text = "인증 완료"
                        }else{
                            textUserCheckMenu.text = "미인증"
                        }

                        val apartInfo = App.apartmentRepository.getApartment(user.uid)
                        if (apartInfo != null){
                            textAddressMenu.text = apartInfo.address
                        }
                    }
                }
            }
        }
    }

    //클릭 이벤트
    private fun settingEvent(){
        with(binding){

            viewLifecycleOwner.lifecycleScope.launch {
                val authUser = App.authRepository.getCurrentUser()
                if (authUser != null){
                    val user = App.userRepository.getUser(authUser.uid)
                    if (user != null){

                        mypageMenu.setOnClickListener {
                            //마이페이지로 이동
                            startActivity(Intent(requireActivity(), InfoActivity::class.java))
                        }

                        buttonUserLogout.setOnClickListener {
                            // 로그인 화면으로 이동
                            App.authRepository.signOut()
                            startActivity(Intent(requireActivity(), LoginActivity::class.java))
                            requireActivity().finish()
                        }

                        LinearContactMenu.setOnClickListener {
                            //고객센터로 이동
                            startActivity(Intent(requireActivity(), ServiceActivity::class.java))
                        }

                        //입주민 인증
                        if (user.apartCertification == true){

                            LinearWriteMenu.setOnClickListener {
                                //내가 쓴 글로 이동
                                startActivity(Intent(requireActivity(), MyWriteActivity::class.java))
                            }


                            LinearReservationMenu.setOnClickListener {
                                //예약 내역으로 이동
                                startActivity(Intent(requireActivity(), ReserveActivity::class.java))
                            }

                            buttonResParkingMenu.setOnClickListener {
                                //방문 주차 예약으로 이동
                                startActivity(Intent(requireActivity(), ParkingActivity::class.java))
                            }
                            buttonVoteMenu.setOnClickListener {
                                //전자 투표로 이동
                                startActivity(Intent(requireActivity(), VoteActivity::class.java))
                            }
                            buttonFireMenu.setOnClickListener {
                                //소방 점검으로 이동
                                startActivity(Intent(requireActivity(), FireCheckActivity::class.java))
                            }
                            buttonContactMenu.setOnClickListener {
                                //관리사무소 문의로 이동
                                startActivity(Intent(requireActivity(), InquiryActivity::class.java))
                            }
                            buttonBroadcastMenu.setOnClickListener {
                                //안내방송으로 이동
                                val intent = Intent(requireActivity(), BroadcastActivity::class.java)
                                intent.putExtra("fragmentName", BroadcastFragmentName.BROADCAST_FRAGMENT)
                                startActivity(intent)
                            }
                            buttonAptSchduleMenu.setOnClickListener {
                                //아파트 일정으로 이동
                                startActivity(Intent(requireActivity(), AptScheduleActivity::class.java))
                            }
                            buttonAptInfoMenu.setOnClickListener {
                                //아파트 운영정보로 이동
                                startActivity(Intent(requireActivity(), OperationInfoActivity::class.java))
                            }
                            //입주민 미인증
                        }else{
                            LinearWriteMenu.isClickable = false
                            LinearReservationMenu.isClickable = false
                            buttonResParkingMenu.isClickable = false
                            buttonVoteMenu.isClickable = false
                            buttonFireMenu.isClickable = false
                            buttonContactMenu.isClickable = false
                            buttonBroadcastMenu.isClickable = false
                            buttonAptSchduleMenu.isClickable = false
                            buttonAptInfoMenu.isClickable = false
                        }
                    }
                }
            }
        }
    }

    //하단 정보 클릭 이벤트
    private fun extraEvent(){
        with(binding){
            textCompanyInfo.setOnClickListener {
                //회사 소개
                startActivity(Intent(requireActivity(), CompanyInfoActivity::class.java))
            }
            textUseMenu.setOnClickListener {
                //이용약관
                startActivity(Intent(requireActivity(), UserAgreementActivity::class.java))
            }
            textUserInfoMenu.setOnClickListener {
                //개인정보 보호 방침
                startActivity(Intent(requireActivity(), PrivacyPolicyActivity::class.java))
            }
        }
    }
}
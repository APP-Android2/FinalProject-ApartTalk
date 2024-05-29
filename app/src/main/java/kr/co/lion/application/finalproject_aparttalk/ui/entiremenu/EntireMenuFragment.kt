package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentEntireMenuBinding
import kr.co.lion.application.finalproject_aparttalk.ui.broadcast.activity.BroadcastActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptOperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.AptScheduleActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.FireCheck.FireCheckActivity
import kr.co.lion.application.finalproject_aparttalk.ui.info.InfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.inquiry.InquiryActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWriteActivity
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
        return binding.root
    }

    //클릭 이벤트
    private fun settingEvent(){
        with(binding){
            mypageMenu.setOnClickListener {
                //마이페이지로 이동
                startActivity(Intent(requireActivity(), InfoActivity::class.java))
            }

            LinearWriteMenu.setOnClickListener {
                //내가 쓴 글로 이동
                startActivity(Intent(requireActivity(), MyWriteActivity::class.java))
            }

            LinearReservationMenu.setOnClickListener {
                //예약 내역으로 이동
                startActivity(Intent(requireActivity(), ReserveActivity::class.java))
            }

            LinearContactMenu.setOnClickListener {
                //고객센터로 이동
                startActivity(Intent(requireActivity(), ServiceActivity::class.java))
            }

            buttonResParkingMenu.setOnClickListener {
                //방문 주차 예약으로 이동
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
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
import kr.co.lion.application.finalproject_aparttalk.util.BroadcastFragmentName

class EntireMenuFragment : Fragment() {

    lateinit var binding:FragmentEntireMenuBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEntireMenuBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        settingToolbar()
        settingEvent()
        extraEvent()
        return binding.root
    }


    //툴바 설정
    private fun settingToolbar(){
        with(binding){
            toolbarMenu.apply {
                inflateMenu(R.menu.menu_setting)
                setOnMenuItemClickListener {
                    //설정 액티비티로 이동

                    true
                }

            }
        }
    }

    //클릭 이벤트
    private fun settingEvent(){
        with(binding){
            mypageMenu.setOnClickListener {
                //마이페이지로 이동
            }

            LinearWriteMenu.setOnClickListener {
                //내가 쓴 글로 이동
            }

            LinearReservationMenu.setOnClickListener {
                //예약 내역으로 이동
            }

            LinearContactMenu.setOnClickListener {
                //고객센터로 이동
            }

            buttonResParkingMenu.setOnClickListener {
                //방문 주차 예약으로 이동
            }
            buttonVoteMenu.setOnClickListener {
                //전자 투표로 이동
            }
            buttonFireMenu.setOnClickListener {
                //소방 점검으로 이동
            }
            buttonContactMenu.setOnClickListener {
                //관리사무소 문의로 이동
            }
            buttonBroadcastMenu.setOnClickListener {
                //안내방송으로 이동
                val intent = Intent(mainActivity, BroadcastActivity::class.java)
                intent.putExtra("fragmentName", BroadcastFragmentName.BROADCAST_FRAGMENT)
                startActivity(intent)
            }
            buttonAptSchduleMenu.setOnClickListener {
                //아파트 일정으로 이동
            }
            buttonAptInfoMenu.setOnClickListener {
                //아파트 운영정보로 이동
            }
        }
    }

    //하단 정보 클릭 이벤트
    private fun extraEvent(){
        with(binding){
            textCompanyInfo.setOnClickListener {
                //회사 소개
            }
            textUseMenu.setOnClickListener {
                //이용약관
            }
            textUserInfoMenu.setOnClickListener {
                //개인정보 보호 방침
            }
        }
    }
}
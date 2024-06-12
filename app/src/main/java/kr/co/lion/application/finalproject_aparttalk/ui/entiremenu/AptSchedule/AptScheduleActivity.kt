package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App.Companion.apartmentRepository
import kr.co.lion.application.finalproject_aparttalk.App.Companion.authRepository
import kr.co.lion.application.finalproject_aparttalk.App.Companion.userRepository
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAptScheduleBinding
import java.util.Calendar

class AptScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAptScheduleBinding
    private val viewModel: AptScheduleViewModel by viewModels()
    private lateinit var aptScheduleRecyclerView: AptScheduleRecyclerView

    // 선택된 날짜를 저장할 변수
    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAptScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setRecyclerAptSchedule()

        // 코루틴을 사용하여 gettingApartId 호출 및 setCalendarView 설정
        GlobalScope.launch(Dispatchers.Main) {
            setCalendarView(gettingApartId())
        }
    }

    override fun onResume() {
        super.onResume()
        setRecyclerAptSchedule()
    }

    // 툴바 구성
    fun setToolbar(){
        binding.apply {
            toolbarAptSchedule.apply {
                // 뒤로가기
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    // 전화면으로 돌아가기. (홈화면 또는 전체메뉴화면)
                    finish()
                }
            }
        }
    }

    // 아파트 아이디 가져오기
    suspend fun gettingApartId(): String {
        var apartmentId = ""
        try {
            val authUser = authRepository.getCurrentUser()
            if (authUser != null) {
                val user = userRepository.getUser(authUser.uid)
                if (user != null) {
                    val apartment = apartmentRepository.getApartment(user.apartmentUid)
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

        binding.calendarViewAptSchedule.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // 사용자가 선택한 날짜를 "yyyy-MM-dd" 형식의 문자열로 저장합니다.
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

            getSelectedDateDate(apartmentUid)
        }
    }

    // 해당 날짜의 일정을 가져옵니다.
    fun getSelectedDateDate(apartmentUid: String){
        // selectedDate가 null이 아닌 경우에만 ViewModel을 통해 해당 날짜의 일정을 가져옵니다.
        selectedDate?.let { date ->
            // 코루틴을 사용하여 gettingSelectedDateList를 호출합니다.
            GlobalScope.launch(Dispatchers.Main) {
                val selectedDateList = viewModel.gettingSelectedDateList(apartmentUid, date)
                // 선택된 날짜의 일정 목록을 어댑터에 설정합니다.
                aptScheduleRecyclerView.setScheduleList(selectedDateList)
            }
        }
    }

    // recyclerView 설정
    fun setRecyclerAptSchedule() {
        aptScheduleRecyclerView = AptScheduleRecyclerView(supportFragmentManager)
        binding.recyclerViewAptSchedule.apply {
            // 어댑터 설정
            adapter = aptScheduleRecyclerView
            // 레이아웃 매니저 설정
            layoutManager = LinearLayoutManager(this@AptScheduleActivity)
            // 데코
            val deco = MaterialDividerItemDecoration(this@AptScheduleActivity, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }
}

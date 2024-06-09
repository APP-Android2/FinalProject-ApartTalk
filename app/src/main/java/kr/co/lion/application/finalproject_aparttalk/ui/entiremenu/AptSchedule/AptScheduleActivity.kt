package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAptScheduleBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.adapter.AptScheduleRecyclerView

class AptScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAptScheduleBinding
    private val viewModel: AptScheduleViewModel by viewModels()

    // 선택된 날짜를 저장할 변수
    private var selectedDate: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAptScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
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

    // recyclerView 설정
    private fun setRecyclerAptSchedule() {
        binding.recyclerViewAptSchedule.apply {
            // 어댑터 설정
            adapter = AptScheduleRecyclerView(supportFragmentManager)
            // 레이아웃 매니저 설정
            layoutManager = LinearLayoutManager(this@AptScheduleActivity)
            // 데코
            val deco = MaterialDividerItemDecoration(this@AptScheduleActivity, MaterialDividerItemDecoration.VERTICAL)
            addItemDecoration(deco)
        }
    }


    // 캘린더뷰 설정
    private fun setCalendarView(apartmentUid :String) {
        binding.calendarViewAptSchedule.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // 사용자가 선택한 날짜를 "yyyy-MM-dd" 형식의 문자열로 저장합니다.
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)

            // selectedDate가 null이 아닌 경우에만 ViewModel을 통해 해당 날짜의 일정을 가져옵니다.
            selectedDate?.let { date ->
                // 코루틴을 사용하여 gettingSelectedDateList를 호출합니다.
                GlobalScope.launch(Dispatchers.Main) {
                    val selectedDateList = viewModel.gettingSelectedDateList(apartmentUid, date)
                    // 여기에서 RecyclerView에 선택된 날짜의 일정 목록을 업데이트하는 코드를 추가할 수 있습니다.
                }
            }
        }
    }

    // 선택된 날짜를 반환하는 메서드
    private fun getSelectedDate(): String? {
        return selectedDate
    }
}

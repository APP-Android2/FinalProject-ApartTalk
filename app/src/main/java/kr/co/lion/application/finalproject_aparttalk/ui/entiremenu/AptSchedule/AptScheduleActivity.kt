package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.app.ActionBar
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.ActivityAptScheduleBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCalendarAptsheduleBinding

class AptScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAptScheduleBinding
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
    fun setRecyclerAptSchedule(){
        binding.apply {
            recyclerViewAptSchedule.apply {
                // 어뎁터 설정
                adapter = RecyclerAptScheduleAdapter()
                // 레이아웃 매니저 설정
                layoutManager = LinearLayoutManager(this@AptScheduleActivity)
                // 데코
                val deco = MaterialDividerItemDecoration(this@AptScheduleActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // recyclerView의 어뎁터
    inner class RecyclerAptScheduleAdapter : RecyclerView.Adapter<RecyclerAptScheduleAdapter.RecyclerAptScheduleViewHolder>(){
        inner class RecyclerAptScheduleViewHolder(rowCalendarAptsheduleBinding: RowCalendarAptsheduleBinding) : RecyclerView.ViewHolder(rowCalendarAptsheduleBinding.root){
            val rowCalendarAptsheduleBinding:RowCalendarAptsheduleBinding

            init {
                this.rowCalendarAptsheduleBinding = rowCalendarAptsheduleBinding
                this.rowCalendarAptsheduleBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAptScheduleViewHolder {
            val rowCalendarAptsheduleBinding = RowCalendarAptsheduleBinding.inflate(layoutInflater)
            val recyclerAptScheduleViewHolder = RecyclerAptScheduleViewHolder(rowCalendarAptsheduleBinding)

            return recyclerAptScheduleViewHolder
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: RecyclerAptScheduleViewHolder, position: Int) {
            holder.rowCalendarAptsheduleBinding.textViewRowCalendarDate.text = "$position 0000-00-00-요일"
            holder.rowCalendarAptsheduleBinding.textViewRowCalendarTime.text = "$position 00:00 - 00:00"
            holder.rowCalendarAptsheduleBinding.textviewRowCalendarSubject.text = "$position 엘베점검"

            // 항목을 누르면 동작하는 리스너
            holder.rowCalendarAptsheduleBinding.root.setOnClickListener {
                // 바텀시트 화면이 나타나게 한다.
                val bottomSheetFragment = AptScheduleShowBottomSheetFragment()
                bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
            }
        }
    }

}
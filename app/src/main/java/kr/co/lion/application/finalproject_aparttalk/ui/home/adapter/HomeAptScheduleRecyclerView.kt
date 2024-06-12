package kr.co.lion.application.finalproject_aparttalk.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowHomeCalendarAptscheduleBinding
import kr.co.lion.application.finalproject_aparttalk.model.AptScheduleModel

class HomeAptScheduleRecyclerView : RecyclerView.Adapter<HomeAptScheduleRecyclerView.ScheduleViewHolder>() {

    private var selectedDateList: List<AptScheduleModel> = emptyList()

    inner class ScheduleViewHolder(rowHomeCalendarAptscheduleBinding: RowHomeCalendarAptscheduleBinding) : RecyclerView.ViewHolder(rowHomeCalendarAptscheduleBinding.root) {

        val rowHomeCalendarAptscheduleBinding:RowHomeCalendarAptscheduleBinding

        init {
            this.rowHomeCalendarAptscheduleBinding = rowHomeCalendarAptscheduleBinding
            this.rowHomeCalendarAptscheduleBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = RowHomeCalendarAptscheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedDateList.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = selectedDateList[position]
        holder.rowHomeCalendarAptscheduleBinding.textViewRowCalendarTime.text = schedule.aptScheduleTime
        holder.rowHomeCalendarAptscheduleBinding.textviewRowCalendarSubject.text = schedule.aptScheduleSubject

        // aptScheduleType에 따라 이미지 설정
        val imageResource = when (schedule.aptScheduleType) {
            "가스점검" -> R.drawable.icon_donut1
            "소독" -> R.drawable.icon_donut2
            "엘레베이터 점검" -> R.drawable.icon_donut3
            "알뜰장" -> R.drawable.icon_donut4
            "쓰레기 수거" -> R.drawable.icon_donut5
            "관리비 공개" -> R.drawable.icon_donut6
            else -> R.drawable.icon_donut1 // 기본 이미지 설정
        }
        holder.rowHomeCalendarAptscheduleBinding.imageView.setImageResource(imageResource)
    }

    // 일정 목록을 갱신하는 함수
    fun setScheduleList(newScheduleList: List<AptScheduleModel>) {
        selectedDateList = newScheduleList
        notifyDataSetChanged()
    }
}

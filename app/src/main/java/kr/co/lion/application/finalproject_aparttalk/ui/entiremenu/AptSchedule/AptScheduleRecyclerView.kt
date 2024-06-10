package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCalendarAptsheduleBinding
import kr.co.lion.application.finalproject_aparttalk.model.AptScheduleModel

class AptScheduleRecyclerView(private val supportFragmentManager: androidx.fragment.app.FragmentManager) :  RecyclerView.Adapter<AptScheduleRecyclerView.ViewHolder>()  {

    private var selectedDateList: List<AptScheduleModel> = emptyList()

    inner class ViewHolder(rowCalendarAptsheduleBinding: RowCalendarAptsheduleBinding) : RecyclerView.ViewHolder(rowCalendarAptsheduleBinding.root) {
        val rowCalendarAptsheduleBinding:RowCalendarAptsheduleBinding

        init {
            this.rowCalendarAptsheduleBinding = rowCalendarAptsheduleBinding
            this.rowCalendarAptsheduleBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCalendarAptsheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedDateList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = selectedDateList[position]
        holder.rowCalendarAptsheduleBinding.textViewRowCalendarDate.text = schedule.aptScheduleDate
        holder.rowCalendarAptsheduleBinding.textViewRowCalendarTime.text = schedule.aptScheduleTime
        holder.rowCalendarAptsheduleBinding.textviewRowCalendarSubject.text = schedule.aptScheduleSubject

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
        holder.rowCalendarAptsheduleBinding.imageView.setImageResource(imageResource)


        // 항목을 누르면 동작하는 리스너
        holder.rowCalendarAptsheduleBinding.root.setOnClickListener {
            // 바텀시트 화면이 나타나게 한다.
            val bottomSheetFragment = AptScheduleShowBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString("date", schedule.aptScheduleDate)
                    putString("time", schedule.aptScheduleTime)
                    putString("subject", schedule.aptScheduleSubject)
                    putString("content", schedule.aptScheduleContent)
                    putString("type", schedule.aptScheduleType)
                }
            }
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }

    // 새로운 데이터를 설정하고 RecyclerView를 갱신하는 메서드
    fun setScheduleList(newScheduleList: List<AptScheduleModel>) {
        selectedDateList = newScheduleList
        notifyDataSetChanged()
    }
}
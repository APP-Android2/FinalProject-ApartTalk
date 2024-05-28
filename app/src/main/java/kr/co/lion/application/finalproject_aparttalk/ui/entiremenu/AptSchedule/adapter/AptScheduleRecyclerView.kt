package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCalendarAptsheduleBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.AptSchedule.fragment.AptScheduleShowBottomSheetFragment

class AptScheduleRecyclerView(private val supportFragmentManager: androidx.fragment.app.FragmentManager) :  RecyclerView.Adapter<AptScheduleRecyclerView.ViewHolder>()  {

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
        return 10
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMylikeTabLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReservationCompleteRecyclerViewAdapter (val context: Context) : RecyclerView.Adapter<ReservationCompleteRecyclerViewAdapter.ReservationCompleteViewHolder>() {

    inner class ReservationCompleteViewHolder(rowReservationItemBinding: RowReservationItemBinding) :
        RecyclerView.ViewHolder(rowReservationItemBinding.root) {
        val rowReservationItemBinding: RowReservationItemBinding

        init {
            this.rowReservationItemBinding = rowReservationItemBinding

            this.rowReservationItemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCompleteRecyclerViewAdapter.ReservationCompleteViewHolder {
        val rowReservationItemBinding = RowReservationItemBinding.inflate(LayoutInflater.from(parent.context))
        val ReservationCompleteViewHolder = ReservationCompleteViewHolder(rowReservationItemBinding)

        return ReservationCompleteViewHolder
    }

    override fun onBindViewHolder(
        holder: ReservationCompleteRecyclerViewAdapter.ReservationCompleteViewHolder,
        position: Int
    ) {
        holder.rowReservationItemBinding.apply {
            reservationTextViewDate.text = "2024.03.01"
            textViewReservationLabelEtc.text = "예약완료"
            reservationTextViewPrice.text = "40,000"
            reservationTextViewTime.text = "15:00 ~ 17:00"
            reservationTextViewFacility.text = "수영장"

            reservationLayout.setOnClickListener {
                (context as ReserveActivity).replaceFragment(
                    ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT, true, true, null
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}

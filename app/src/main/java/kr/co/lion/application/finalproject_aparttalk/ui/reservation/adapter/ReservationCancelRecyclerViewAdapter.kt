package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationCancelItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReservationCancelRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<ReservationCancelRecyclerViewAdapter.ReservationCancelViewHolder>() {

    inner class ReservationCancelViewHolder(rowReservationCancelItemBinding: RowReservationCancelItemBinding) :
        RecyclerView.ViewHolder(rowReservationCancelItemBinding.root) {
        val rowReservationCancelItemBinding: RowReservationCancelItemBinding

        init {
            this.rowReservationCancelItemBinding = rowReservationCancelItemBinding

            this.rowReservationCancelItemBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCancelRecyclerViewAdapter.ReservationCancelViewHolder {
        val rowReservationCancelItemBinding =
            RowReservationCancelItemBinding.inflate(LayoutInflater.from(parent.context))
        val ReservationCancelViewHolder = ReservationCancelViewHolder(rowReservationCancelItemBinding)

        return ReservationCancelViewHolder
    }

    override fun onBindViewHolder(
        holder: ReservationCancelRecyclerViewAdapter.ReservationCancelViewHolder,
        position: Int
    ) {
        holder.rowReservationCancelItemBinding.apply {
            reservationCancelTextViewDate.text = "2024.03.01"
            textViewReservationCancelLabelEtc.text = "예약취소"
            reservationCancelTextViewPrice.text = "40,000"
            reservationCancelTextViewTime.text = "15:00 ~ 17:00"
            reservationCancelTextViewFacility.text = "수영장"

            reservationCancelLayout.setOnClickListener {
                (context as ReserveActivity).replaceFragment(
                    ReserveFragmentName.RESERVATION_CANCEL_COMPLETE_FRAGMENT, true, true, null
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationCancelItemBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReservationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReservationCancelRecyclerViewAdapter(
    val context: Context,
    private val viewModel: ReservationViewModel
) : ListAdapter<FacilityResModel, ReservationCancelRecyclerViewAdapter.ReservationCancelViewHolder>(FacilityResModelDiffCallback()) {

    inner class ReservationCancelViewHolder(val binding: RowReservationCancelItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCancelViewHolder {
        val binding = RowReservationCancelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationCancelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationCancelViewHolder, position: Int
    ) {
        val reservation = getItem(position)
        holder.binding.apply {
            reservationCancelTextViewDate.text = reservation.reservationDate
            textViewReservationCancelLabelEtc.text = if (reservation.reservationState) "예약완료" else "예약취소"
            reservationCancelTextViewPrice.text = reservation.usePrice
            reservationCancelTextViewTime.text = reservation.useTime
            reservationCancelTextViewFacility.text = reservation.titleText

            reservationCancelLayout.setOnClickListener {
                viewModel.setSelectedReservation(reservation)
                (context as ReserveActivity).replaceFragment(
                    ReserveFragmentName.RESERVATION_CANCEL_COMPLETE_FRAGMENT, true, true, null
                )
            }
        }
    }

    class FacilityResModelDiffCallback : DiffUtil.ItemCallback<FacilityResModel>() {
        override fun areItemsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem.userUid == newItem.userUid
        }

        override fun areContentsTheSame(oldItem: FacilityResModel, newItem: FacilityResModel): Boolean {
            return oldItem == newItem
        }
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowMylikeTabLikeBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowReservationItemBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReservationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.ReserveActivity
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName
import kr.co.lion.application.finalproject_aparttalk.util.ReserveFragmentName

class ReservationCompleteRecyclerViewAdapter(
    private val context: Context,
    private val viewModel: ReservationViewModel
) : ListAdapter<FacilityResModel, ReservationCompleteRecyclerViewAdapter.ReservationCompleteViewHolder>(FacilityResModelDiffCallback()) {

    inner class ReservationCompleteViewHolder(val binding: RowReservationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationCompleteViewHolder {
        val binding = RowReservationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationCompleteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReservationCompleteViewHolder, position: Int
    ) {
        val reservation = getItem(position)
        holder.binding.apply {
            reservationTextViewDate.text = reservation.reservationDate
            textViewReservationLabelEtc.text = if (reservation.reservationState) "예약완료" else "예약취소"
            reservationTextViewPrice.text = reservation.usePrice
            reservationTextViewTime.text = reservation.useTime
            reservationTextViewFacility.text = reservation.titleText

            reservationLayout.setOnClickListener {
                viewModel.setSelectedReservation(reservation)
                (context as ReserveActivity).replaceFragment(
                    ReserveFragmentName.RESERVATION_CONFIRM_FRAGMENT, true, true, null
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
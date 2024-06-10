package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCancelBinding
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCancelRecyclerViewAdapter


class ReservationCancelFragment : Fragment() {

    lateinit var fragmentReservationCancelBinding: FragmentReservationCancelBinding
    lateinit var reserveActivity: ReserveActivity
    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCancelBinding = FragmentReservationCancelBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()

        reservationViewModel.reservations.observe(viewLifecycleOwner, Observer { reservations ->
            (fragmentReservationCancelBinding.recyclerViewTabReservationCancel.adapter as ReservationCancelRecyclerViewAdapter).submitList(reservations)
        })

        return fragmentReservationCancelBinding.root
    }

    fun settingRecyclerview() {
        fragmentReservationCancelBinding.apply {
            recyclerViewTabReservationCancel.apply {
                adapter = ReservationCancelRecyclerViewAdapter(requireContext(), reservationViewModel)
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(reserveActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
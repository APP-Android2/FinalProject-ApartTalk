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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityResModel
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCompleteRecyclerViewAdapter


class ReservationCompleteFragment : Fragment() {

    private lateinit var fragmentReservationCompleteBinding: FragmentReservationCompleteBinding
    private val reservationViewModel: ReservationViewModel by activityViewModels()
    lateinit var reserveActivity: ReserveActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentReservationCompleteBinding =
            FragmentReservationCompleteBinding.inflate(inflater, container, false)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()

        // ViewModel의 데이터를 관찰하여 RecyclerView에 표시
        reservationViewModel.reservations.observe(viewLifecycleOwner, Observer { reservations ->
            (fragmentReservationCompleteBinding.recyclerViewTabReservationComplete.adapter as ReservationCompleteRecyclerViewAdapter).submitList(
                reservations
            )
        })

        return fragmentReservationCompleteBinding.root
    }

    private fun settingRecyclerview() {
        fragmentReservationCompleteBinding.apply {
            recyclerViewTabReservationComplete.apply {
                adapter = ReservationCompleteRecyclerViewAdapter(requireContext(), reservationViewModel)
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(reserveActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
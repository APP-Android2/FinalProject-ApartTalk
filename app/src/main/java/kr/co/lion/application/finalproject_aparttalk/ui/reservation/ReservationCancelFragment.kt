package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCancelBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCancelRecyclerViewAdapter


class ReservationCancelFragment : Fragment() {

    lateinit var fragmentReservationCancelBinding: FragmentReservationCancelBinding
    lateinit var reserveActivity: ReserveActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentReservationCancelBinding = FragmentReservationCancelBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()

        return fragmentReservationCancelBinding.root

    }

    fun settingRecyclerview(){
        fragmentReservationCancelBinding.apply{
            recyclerViewTabReservationCancel.apply{
                adapter = ReservationCancelRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(reserveActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
package kr.co.lion.application.finalproject_aparttalk.ui.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentReservationCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.MyWriteActivity
import kr.co.lion.application.finalproject_aparttalk.ui.mywrite.adapter.MyLikeRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.reservation.adapter.ReservationCompleteRecyclerViewAdapter


class ReservationCompleteFragment : Fragment() {

    lateinit var fragmentReservationCompleteBinding: FragmentReservationCompleteBinding
    lateinit var reserveActivity: ReserveActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        fragmentReservationCompleteBinding = FragmentReservationCompleteBinding.inflate(inflater)
        reserveActivity = activity as ReserveActivity

        settingRecyclerview()

        return fragmentReservationCompleteBinding.root

    }

    fun settingRecyclerview(){
        fragmentReservationCompleteBinding.apply{
            recyclerViewTabReservationComplete.apply{
                adapter = ReservationCompleteRecyclerViewAdapter(requireContext())
                layoutManager = LinearLayoutManager(reserveActivity)
                val deco = MaterialDividerItemDecoration(reserveActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

}
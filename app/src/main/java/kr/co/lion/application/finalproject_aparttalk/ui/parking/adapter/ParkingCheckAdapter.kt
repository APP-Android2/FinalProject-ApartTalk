package kr.co.lion.application.finalproject_aparttalk.ui.parking.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowParkingReserveBinding
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel

class ParkingCheckAdapter : ListAdapter<ParkingModel, ParkingCheckViewHolder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingCheckViewHolder {
        return ParkingCheckViewHolder(RowParkingReserveBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ParkingCheckViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    companion object{
        val differ = object : DiffUtil.ItemCallback<ParkingModel>(){
            override fun areItemsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
                return oldItem.userUid == newItem.userUid
            }

            override fun areContentsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class ParkingCheckViewHolder(val binding: RowParkingReserveBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item : ParkingModel){
        binding.textCarNumberRecycler.text = item.carNumber
        binding.textOwnerNameRecycler.text = item.ownerName
        Log.d("seonguk1234", "${item}")
    }
}
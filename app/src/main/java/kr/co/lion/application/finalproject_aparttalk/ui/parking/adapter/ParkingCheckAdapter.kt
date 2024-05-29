package kr.co.lion.application.finalproject_aparttalk.ui.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowParkingReserveBinding

class ParkingCheckAdapter : ListAdapter<String, ParkingCheckViewHolder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingCheckViewHolder {
        return ParkingCheckViewHolder(RowParkingReserveBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ParkingCheckViewHolder, position: Int) {
        holder.bind()
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<String>(){
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class ParkingCheckViewHolder(val binding: RowParkingReserveBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(){
        binding.textCarNumberRecycler.text = "000가 0000"
        binding.textOwnerNameRecycler.text = "허성욱"
    }
}
package kr.co.lion.application.finalproject_aparttalk.ui.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowParkingReserveBinding
import kr.co.lion.application.finalproject_aparttalk.model.ParkingModel

class ParkingReserveAdapter : ListAdapter<ParkingModel, ParkingReserveViewHolder>(differ) {

    lateinit var recyclerviewClick: ItemOnClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingReserveViewHolder {
        return ParkingReserveViewHolder(RowParkingReserveBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ParkingReserveViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.binding.root.setOnClickListener {
            recyclerviewClick.recyclerviewOnClickListener()
        }
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<ParkingModel>(){
            override fun areItemsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ParkingModel, newItem: ParkingModel): Boolean {
                return oldItem == newItem
            }

        }
    }
    interface ItemOnClickListener{
        fun recyclerviewOnClickListener()
    }

    fun setRecyclerview(recyclerviewClick : ItemOnClickListener){
        this.recyclerviewClick = recyclerviewClick
    }
}

class ParkingReserveViewHolder(val binding: RowParkingReserveBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(item : ParkingModel){
        binding.textCarNumberRecycler.text = item.carNumber
        binding.textOwnerNameRecycler.text = item.ownerName
    }
}

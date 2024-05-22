package kr.co.lion.application.finalproject_aparttalk.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemExtraMenuBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationExtraData

class ExtraAdapter : ListAdapter<LocationExtraData, ExtraAdapter.ExtraViewHolder>(differ) {


    inner class ExtraViewHolder(val binding: ItemExtraMenuBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(locationExtraData: LocationExtraData){
            binding.textTitleMenuExtra.text = locationExtraData.title
            binding.textAddressMenuExtra.text = locationExtraData.address
        }
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<LocationExtraData>(){
            override fun areItemsTheSame(oldItem: LocationExtraData, newItem: LocationExtraData): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: LocationExtraData, newItem: LocationExtraData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraViewHolder {
        return ExtraViewHolder(ItemExtraMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ExtraViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


}
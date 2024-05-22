package kr.co.lion.application.finalproject_aparttalk.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemAllMenuBinding
import kr.co.lion.application.finalproject_aparttalk.model.LocationAllData

class AllAdapter : ListAdapter<LocationAllData, AllAdapter.AllViewHolder>(differ) {

    inner class AllViewHolder(val binding: ItemAllMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(locationAllData: LocationAllData) {
            binding.textMenuTitleAll.text = locationAllData.title
            binding.textAddressMenuAll.text = locationAllData.address
            binding.textCategoryMenuAll.text = locationAllData.category
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<LocationAllData>(){
            override fun areItemsTheSame(oldItem: LocationAllData, newItem: LocationAllData): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: LocationAllData, newItem: LocationAllData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllViewHolder {
        return AllViewHolder(ItemAllMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AllViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
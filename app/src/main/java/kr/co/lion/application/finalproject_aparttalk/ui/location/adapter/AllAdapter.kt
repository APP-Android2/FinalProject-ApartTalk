package kr.co.lion.application.finalproject_aparttalk.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemAllMenuBinding
import kr.co.lion.application.finalproject_aparttalk.model.Place

class AllAdapter : ListAdapter<Place, AllAdapter.AllViewHolder>(differ) {

    private lateinit var recyclerviewClick: ItemOnClickListener

    inner class AllViewHolder(val binding: ItemAllMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(locationAllData: Place) {
            binding.textMenuTitleAll.text = locationAllData.place_name
            binding.textAddressMenuAll.text = locationAllData.road_address_name
            binding.textCategoryMenuAll.text = locationAllData.category_name
            binding.root.setOnClickListener {
                recyclerviewClick.recyclerviewClickListener(
                    locationAllData.place_name?:"", locationAllData.category_name?:"", locationAllData.road_address_name?:"",
                    locationAllData.phone?:"", locationAllData.distance?:"", locationAllData.x?:"", locationAllData.y?:"")
            }
        }

    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Place>(){
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
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

    interface ItemOnClickListener{
        fun recyclerviewClickListener(name:String, category:String, address:String, number:String, distance:String, x:String, y:String)
    }

    fun setRecyclerviewClick(recyclerviewClick : ItemOnClickListener){
        this.recyclerviewClick = recyclerviewClick
    }
}
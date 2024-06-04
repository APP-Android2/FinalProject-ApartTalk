package kr.co.lion.application.finalproject_aparttalk.ui.location.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemExtraMenuBinding
import kr.co.lion.application.finalproject_aparttalk.model.Place

class ExtraAdapter() : ListAdapter<Place, ExtraAdapter.ExtraViewHolder>(differ) {

    private lateinit var recyclerviewClick: ExtraItemOnClickListener


    inner class ExtraViewHolder(val binding: ItemExtraMenuBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(locationExtraData: Place){
            binding.textTitleMenuExtra.text = locationExtraData.place_name
            binding.textAddressMenuExtra.text = locationExtraData.road_address_name
            binding.root.setOnClickListener {
                recyclerviewClick.extraRecyclerviewClickListener(locationExtraData.place_name?:"", locationExtraData.category_name?:"", locationExtraData.road_address_name?:"",
                    locationExtraData.phone?:"", locationExtraData.distance?:"", locationExtraData.x?:"", locationExtraData.y?:"")
            }
        }
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<Place>(){
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
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

    interface ExtraItemOnClickListener{
        fun extraRecyclerviewClickListener(name:String, category:String, address:String, number:String, distance:String, x:String, y:String)
    }

    fun setExtraRecyclerviewClick(recyclerviewClick: ExtraItemOnClickListener){
        this.recyclerviewClick = recyclerviewClick
    }

}
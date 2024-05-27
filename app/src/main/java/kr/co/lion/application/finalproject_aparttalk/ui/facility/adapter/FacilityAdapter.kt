package kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemFacilityGridBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityData

class FacilityAdapter : ListAdapter<FacilityData, FacilityAdapter.FacilityViewHolder>(differ) {

    private lateinit var gridRecyclerviewClick: FacilityItemClickListener

    inner class FacilityViewHolder(val binding:ItemFacilityGridBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: FacilityData){
            binding.imageFacilityGrid.setImageResource(item.imageRes)
            binding.textFacilityGrid.text = item.titleText
        }
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<FacilityData>(){
            override fun areItemsTheSame(oldItem: FacilityData, newItem: FacilityData): Boolean {
                return oldItem.dataId == newItem.dataId
            }

            override fun areContentsTheSame(oldItem: FacilityData, newItem: FacilityData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        return FacilityViewHolder(ItemFacilityGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.binding.buttonFacilityGrid.setOnClickListener {
            gridRecyclerviewClick.gridRecyclerClickListener()
        }
    }

    interface FacilityItemClickListener{
        fun gridRecyclerClickListener()
    }

    fun setGridRecyclerviewClick(gridRecyclerviewClick: FacilityItemClickListener){
        this.gridRecyclerviewClick = gridRecyclerviewClick
    }


}
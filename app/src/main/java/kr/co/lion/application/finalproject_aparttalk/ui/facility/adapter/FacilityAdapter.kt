package kr.co.lion.application.finalproject_aparttalk.ui.facility.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemFacilityGridBinding
import kr.co.lion.application.finalproject_aparttalk.model.FacilityModel
import kr.co.lion.application.finalproject_aparttalk.util.setImage

class FacilityAdapter : ListAdapter<FacilityModel, FacilityAdapter.FacilityViewHolder>(differ) {

    private lateinit var gridRecyclerviewClick: FacilityItemClickListener

    inner class FacilityViewHolder(val binding:ItemFacilityGridBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: FacilityModel){
            binding.root.context.setImage(binding.imageFacilityGrid, item.imageRes)
            binding.textFacilityGrid.text = item.titleText
            //Log.e("seonguk1234", "${item.titleText}")
            binding.root.setOnClickListener {
                gridRecyclerviewClick.gridRecyclerClickListener(
                    item.titleText, item.imageRes, item.canReserve, item.content?:"", item.price?:""
                )
            }
        }
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<FacilityModel>(){
            override fun areItemsTheSame(oldItem: FacilityModel, newItem: FacilityModel): Boolean {
                return oldItem.dataId == newItem.dataId
            }

            override fun areContentsTheSame(oldItem: FacilityModel, newItem: FacilityModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilityViewHolder {
        return FacilityViewHolder(ItemFacilityGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FacilityViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    interface FacilityItemClickListener{
        fun gridRecyclerClickListener(titleText:String, imageRes:String, canReserve:Boolean, content:String, price:String)
    }

    fun setGridRecyclerviewClick(gridRecyclerviewClick: FacilityItemClickListener){
        this.gridRecyclerviewClick = gridRecyclerviewClick
    }


}
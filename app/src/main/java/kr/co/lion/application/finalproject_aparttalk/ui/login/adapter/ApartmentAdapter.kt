package kr.co.lion.application.finalproject_aparttalk.ui.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemApartmentBinding
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel

class ApartmentAdapter(
    private var apartments: List<ApartmentModel>,
    private val onItemClicked: (apartmentName: String, apartmentUid: String) -> Unit
) : RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {

    inner class ApartmentViewHolder(private val binding: ItemApartmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(apartment: ApartmentModel) {
            binding.itemApartmentName.text = apartment.name
            binding.itemApartmentAddress.text = apartment.address
            binding.root.setOnClickListener {
                onItemClicked(apartment.name, apartment.uid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApartmentViewHolder {
        val binding = ItemApartmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApartmentViewHolder(binding)
    }

    override fun getItemCount() = apartments.size

    override fun onBindViewHolder(holder: ApartmentViewHolder, position: Int) {
        holder.bind(apartments[position])
    }

    fun updateData(newApartments: List<ApartmentModel>) {
        apartments = newApartments
        notifyDataSetChanged()
    }
}

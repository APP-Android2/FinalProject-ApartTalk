package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.ItemApartmentBinding

class ApartmentAdapter(
    private var apartments: List<Pair<String, String>>,
    private val onItemClicked: (apartmentName: String, apartmentAddress: String) -> Unit
) : RecyclerView.Adapter<ApartmentAdapter.ApartmentViewHolder>() {

    inner class ApartmentViewHolder(private val binding: ItemApartmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(apartment: Pair<String, String>) {
            binding.itemApartmentName.text = apartment.first
            binding.itemApartmentAddress.text = apartment.second
            binding.root.setOnClickListener {
                onItemClicked(apartment.first, apartment.second)
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

    fun updateData(newApartments: List<Pair<String, String>>) {
        apartments = newApartments
        notifyDataSetChanged()
    }
}

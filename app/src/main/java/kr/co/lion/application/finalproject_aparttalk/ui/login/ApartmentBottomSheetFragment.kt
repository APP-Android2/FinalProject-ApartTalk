package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentApartmentBottomSheetBinding
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel
import kr.co.lion.application.finalproject_aparttalk.ui.login.adapter.ApartmentAdapter

class ApartmentBottomSheetFragment(
    private val apartments: List<ApartmentModel>,
    private val onApartmentSelected: (apartmentName: String, apartmentAddress: String) -> Unit,
) : BottomSheetDialogFragment() {

    private var _binding: FragmentApartmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ApartmentAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            bottomSheet.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        }
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentApartmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewAdapter()
        searchEditTextListener()
    }

    private fun recyclerViewAdapter() {
        adapter = ApartmentAdapter(apartments) { apartmentName, apartmentUid ->
            onApartmentSelected(apartmentName, apartmentUid)
            SystemClock.sleep(200)
            dismiss()
        }
        binding.apartmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.apartmentRecyclerView.adapter = adapter
    }

    private fun searchEditTextListener() {
        binding.searchEditText.addTextChangedListener {
            val query = it.toString()
            filterApartments(query)
        }

        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }
    }

    private fun filterApartments(query: String) {
        val filteredApartments = apartments.filter {
            it.name.contains(query, ignoreCase = true) || it.address.contains(
                query,
                ignoreCase = true
            )
        }
        if (filteredApartments.isEmpty()) {
            binding.noResults.visibility = View.VISIBLE
            binding.apartmentRecyclerView.visibility = View.GONE
        } else {
            binding.noResults.visibility = View.GONE
            binding.apartmentRecyclerView.visibility = View.VISIBLE
            adapter.updateData(filteredApartments)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
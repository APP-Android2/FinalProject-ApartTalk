package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentApartmentDongHoPickerDialogBinding

class ApartmentDongHoPickerDialogFragment(private val onApartmentDongHoSelected: (dong: Int, ho: Int) -> Unit) : DialogFragment() {

    private var _binding: FragmentApartmentDongHoPickerDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentApartmentDongHoPickerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupApartDongPicker(picker = binding.apartDongPicker,
            minValue = 101,
            maxValue = 112,
            formatter = { value -> "${value}동" }
        )
        setupApartHoPicker(picker = binding.apartHoPicker)

        cancelButton()
        confirmButton()
    }

    private fun setupApartDongPicker(picker: NumberPicker, minValue: Int, maxValue: Int, formatter: (Int) -> String) {
        picker.apply {
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            this.minValue = minValue
            this.maxValue = maxValue
            value = minValue
            displayedValues = (minValue..maxValue).map { formatter(it) }.toTypedArray()
        }
    }

    private fun setupApartHoPicker(picker: NumberPicker) {
        val hoValues = generateHoValues()
        picker.apply {
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            this.minValue = 0
            this.maxValue = hoValues.size - 1
            value = 0
            displayedValues = hoValues
        }
    }

    private fun generateHoValues(): Array<String> {
        val hoValues = mutableListOf<String>()
        for (i in 1..20) {
            for (j in 1..6) {
                val ho = i * 100 + j
                hoValues.add("${ho}호")
            }
        }
        return hoValues.toTypedArray()
    }

    private fun cancelButton(){
        binding.apartDongHoCancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun confirmButton(){
        binding.apartDongHoConfirmButton.setOnClickListener {
            val dongValue = binding.apartDongPicker.value
            val hoValue = binding.apartHoPicker.displayedValues[binding.apartHoPicker.value].replace("호", "").toInt()
            onApartmentDongHoSelected(dongValue, hoValue)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
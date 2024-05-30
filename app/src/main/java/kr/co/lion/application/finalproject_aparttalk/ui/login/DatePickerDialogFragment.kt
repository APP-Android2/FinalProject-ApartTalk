package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentDatePickerDialogBinding

class DatePickerDialogFragment(
    private val initialYear: Int,
    private val initialMonth: Int,
    private val initialDay: Int,
    private val onDateSelected: (year: Int, month: Int, day: Int) -> Unit
) : DialogFragment() {

    private var _binding: FragmentDatePickerDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDatePickerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        setupNumberPicker(
            picker = binding.dateYearPicker,
            minValue = 1900,
            maxValue = currentYear,
            initialValue = initialYear,
            formatter = { value -> "${value}년" }
        )

        setupNumberPicker(
            picker = binding.dateMonthPicker,
            minValue = 1,
            maxValue = 12,
            initialValue = initialMonth,
            formatter = { value -> "${value}월" }
        )

        val initialDaysInMonth = getDaysInMonth(initialYear, initialMonth)
        setupNumberPicker(
            picker = binding.dateDayPicker,
            minValue = 1,
            maxValue = initialDaysInMonth,
            initialValue = initialDay,
            formatter = { value -> "${value}일" }
        )

        datePickerListeners()
        cancelButton()
        confirmButton()
    }

    private fun setupNumberPicker(picker: NumberPicker, minValue: Int, maxValue: Int, initialValue: Int, formatter: (Int) -> String) {
        picker.apply {
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            this.minValue = minValue
            this.maxValue = maxValue
            value = initialValue
            displayedValues = (minValue..maxValue).map { formatter(it) }.toTypedArray()
        }
    }

    private fun datePickerListeners(){
        binding.dateMonthPicker.setOnValueChangedListener { _, _, newVal ->
            updateDayPicker(binding.dateYearPicker.value, newVal)
        }

        binding.dateYearPicker.setOnValueChangedListener { _, _, newVal ->
            updateDayPicker(newVal, binding.dateMonthPicker.value)
        }
    }

    private fun updateDayPicker(year: Int, month: Int) {
        val dayPicker = binding.dateDayPicker
        val daysInMonth = getDaysInMonth(year, month)
        val displayedDays = (1..daysInMonth).map { day -> "${day}일" }.toTypedArray()
        dayPicker.displayedValues = null  // 기존 값을 지우기
        dayPicker.minValue = 1
        dayPicker.maxValue = daysInMonth
        dayPicker.displayedValues = displayedDays
    }

    private fun getDaysInMonth(year: Int, month: Int): Int {
        return when (month) {
            2 -> if (isLeapYear(year)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    private fun cancelButton(){
        binding.datePickerCancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun confirmButton(){
        binding.datePickerConfirmButton.setOnClickListener {
            onDateSelected(binding.dateYearPicker.value, binding.dateMonthPicker.value, binding.dateDayPicker.value)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

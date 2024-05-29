package kr.co.lion.application.finalproject_aparttalk.ui.parking

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentParkingReserveBinding
import kr.co.lion.application.finalproject_aparttalk.util.DialogConfirm
import kr.co.lion.application.finalproject_aparttalk.util.ParkingFragmentName
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ParkingReserveFragment : Fragment() {

    lateinit var binding: FragmentParkingReserveBinding

    lateinit var parkingActivity: ParkingActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentParkingReserveBinding.inflate(layoutInflater)
        parkingActivity = activity as ParkingActivity
        settingToolbar()
        settingButtonParkingAdd()
        settingCal()
        return binding.root
    }

    private fun settingToolbar(){
        binding.apply {
            toolbarParkingRes.apply {
                setNavigationIcon(R.drawable.icon_back)
                setNavigationOnClickListener {
                    parkingActivity.removeFragment(ParkingFragmentName.PARKING_RESERVE_FRAGMENT)
                }
            }
        }
    }

    //캘린더 설정
    private fun settingCal(){
        binding.apply {
            //오늘 날짜 가져오기
            val today = Calendar.getInstance()

            //캘린더에서 과거 날짜를 선택하지 못하게 설정
            parkingCalendarView.minDate = today.timeInMillis

            parkingCalendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                //선택된 날짜를 "dd/MM/yyyy" 형식으로 변환
                val selectDate = "$year/${month + 1}/$dayOfMonth"
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                val date = dateFormat.parse(selectDate)

                //날짜를 TextInputLayout에 설정
                textViewParkingVisitDate.setText(dateFormat.format(date))
            }
        }
    }

    // 등록 버튼 활성화 / 비활성화
    private fun settingButtonParkingAdd() {
        binding.apply {

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val isCarNumberFilled = textViewParkingCarNumber.text?.isNotBlank() ?: false
                    val isOwnerNumber = textViewParkingOwnerNumber.text?.isNotBlank() ?: false
                    val isOwnerName = textViewParkingOwnerName.text?.isNotBlank() ?: false
                    val visitDate = textViewParkingVisitDate.text?.isNotBlank() ?: false

                    val isButtonEnabled = isCarNumberFilled && isOwnerNumber && isOwnerName && visitDate

                    buttonGoParking.isEnabled = isButtonEnabled

                    if (isButtonEnabled) {
                        // 활성 상태일 때
                        buttonGoParking.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        buttonGoParking.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    } else {
                        // 비활성 상태일 때
                        buttonGoParking.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.white))
                        // stroke 설정
                        buttonGoParking.strokeWidth = 1
                        buttonGoParking.strokeColor =
                            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.third))
                        // 텍스트 색상 설정
                        buttonGoParking.setTextColor(
                            ContextCompat.getColor(requireContext(), R.color.third)
                        )
                    }
                }
            }

            textViewParkingCarNumber.addTextChangedListener(textWatcher)
            textViewParkingOwnerNumber.addTextChangedListener(textWatcher)
            textViewParkingOwnerName.addTextChangedListener(textWatcher)
            textViewParkingVisitDate.addTextChangedListener(textWatcher)

            buttonGoParking.setOnClickListener {
                val dialog = DialogConfirm(
                    "방문차량 신청", "방문차량 신청이 완료되었습니다.", parkingActivity
                )
                dialog.setDialogButtonClickListener(object : DialogConfirm.OnButtonClickListener{
                    override fun okButtonClick() {
                        parkingActivity.finish()
                    }

                })
                dialog.show(parkingActivity.supportFragmentManager, "DialogConfirm")
            }

        }
    }
}
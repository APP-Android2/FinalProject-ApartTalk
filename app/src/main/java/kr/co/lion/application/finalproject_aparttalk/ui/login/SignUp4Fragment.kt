package kr.co.lion.application.finalproject_aparttalk.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp4Binding

class SignUp4Fragment : Fragment() {

    private var _binding: FragmentSignUp4Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSignUp4Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeDataStates()
        searchApartButton()
        selectApartDongHoButton()
        selectIsApartCertificationRadioButton()
        completeButton()
        backProcess()
    }

    private fun initializeDataStates() {
        viewModel.apartName.observe(viewLifecycleOwner) { apartName ->
            binding.signup4ApartName.text = apartName
            updateApartDongHoState(apartName.isNotEmpty())
            updateButtonState(apartName.isNotEmpty())
            viewModel.initializeApartDongHo()
            viewModel.initializeIsApartCertification()
        }

        viewModel.apartDong.observe(viewLifecycleOwner) { dong ->
            binding.signup4ApartDong.text = "${dong ?: ""}동"
            viewModel.initializeIsApartCertification()
            updateIsApartCertificationState(dong != null)
        }

        viewModel.apartHo.observe(viewLifecycleOwner) { ho ->
            binding.signup4ApartHo.text = "${ho ?: ""}호"
        }

        viewModel.isApartCertification.observe(viewLifecycleOwner) { isApartCertification ->
            when (isApartCertification) {
                true -> binding.signup4RadioButtonRequest.isChecked = true
                false -> binding.signup4RadioButtonDefer.isChecked = true
            }
        }
    }

    private fun searchApartButton() {
        binding.signup4ApartLayout.setOnClickListener {
            showApartListBottomSheet()
        }
    }

    private fun showApartListBottomSheet() {
        val apartmentBottomSheetFragment = ApartmentBottomSheetFragment{ apartName, apartAddress ->
            onApartSelected(apartName, apartAddress)
        }
        apartmentBottomSheetFragment.show(requireActivity().supportFragmentManager, apartmentBottomSheetFragment.tag)
    }

    private fun onApartSelected(apartName: String, apartAddress: String){
        viewModel.setApartInfo(apartName, apartAddress)
    }

    private fun updateApartDongHoState(isApartInfoExist:Boolean){
        binding.signup4DonghoLayout.isVisible = isApartInfoExist
    }

    private fun selectApartDongHoButton(){
        binding.signup4SelectApartDongHo.setOnClickListener {
            showApartDongHoDialog()
        }
    }

    private fun showApartDongHoDialog() {
        val apartmentDongHoPickerDialogFragment = ApartmentDongHoPickerDialogFragment{ dong, ho ->
            onApartDongHoSelected(dong, ho)
        }
        apartmentDongHoPickerDialogFragment.show(requireActivity().supportFragmentManager, apartmentDongHoPickerDialogFragment.tag)
    }

    private fun onApartDongHoSelected(dong: Int, ho: Int){
        viewModel.setApartDongHo(dong, ho)
    }

    private fun updateIsApartCertificationState(isApartDongHoExist:Boolean){
        binding.signup4IsapartcertificationLayout.isVisible = isApartDongHoExist
    }

    private fun selectIsApartCertificationRadioButton() {
        binding.signup4ApartcertificationRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selected = when (checkedId) {
                R.id.signup4_radio_button_request -> true
                R.id.signup4_radio_button_defer -> false
                else -> false
            }
            viewModel.setIsApartCertification(selected)
        }
    }

    private fun updateButtonState(isApartInfoExist:Boolean) {
        binding.signup4AgreeButton.isEnabled = isApartInfoExist
        binding.signup4AgreeButton.alpha = if (isApartInfoExist) 1.0f else 0.5f
    }

    private fun completeButton(){
        binding.signup4AgreeButton.setOnClickListener {
            viewModel.setIsCompleteInputUserInfo(true)
            viewModel.userAllInfo()
            (activity as? SignUpActivity)?.setResultAndFinish()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun backProcess(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.initializeApartInfo()
            viewModel.initializeIsApartCertification()
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
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
        searchApartmentButton()
        selectApartDongHoButton()
        completeButton()
        backProcess()
    }

    private fun initializeDataStates() {
        viewModel.apartmentName.observe(viewLifecycleOwner) { apartmentName ->
            binding.signup4ApartName.text = apartmentName
            updateApartDongHoState(apartmentName.isNotEmpty())
            updateButtonState(apartmentName.isNotEmpty())
            viewModel.initializeApartmentDongHo()
        }

        viewModel.apartmentDong.observe(viewLifecycleOwner) { dong ->
            binding.signup4ApartDong.text = "${dong ?: ""}동"
        }

        viewModel.apartmentHo.observe(viewLifecycleOwner) { ho ->
            binding.signup4ApartHo.text = "${ho ?: ""}호"
        }
    }

    private fun searchApartmentButton() {
        binding.signup4ApartLayout.setOnClickListener {
            showApartmentListBottomSheet()
        }
    }

    private fun showApartmentListBottomSheet() {
        val apartmentBottomSheetFragment = ApartmentBottomSheetFragment{ apartmentName, apartmentAddress ->
            onApartmentSelected(apartmentName, apartmentAddress)
        }
        apartmentBottomSheetFragment.show(requireActivity().supportFragmentManager, apartmentBottomSheetFragment.tag)
    }

    private fun onApartmentSelected(apartmentName: String, apartmentAddress: String){
        viewModel.setApartmentInfo(apartmentName, apartmentAddress)
    }

    private fun updateApartDongHoState(isApartInfoExist:Boolean){
        binding.signup4DonghoLayout.isVisible = isApartInfoExist
    }

    private fun selectApartDongHoButton(){
        binding.signup4SelectApartDongHo.setOnClickListener {
            showApartmentDongHoDialog()
        }
    }

    private fun showApartmentDongHoDialog() {
        val apartmentDongHoPickerDialogFragment = ApartmentDongHoPickerDialogFragment{ dong, ho ->
            onApartmentDongHoSelected(dong, ho)
        }
        apartmentDongHoPickerDialogFragment.show(requireActivity().supportFragmentManager, apartmentDongHoPickerDialogFragment.tag)
    }

    private fun onApartmentDongHoSelected(dong: Int, ho: Int){
        viewModel.setApartmentDongHo(dong, ho)
    }

    private fun updateButtonState(isApartInfoExist:Boolean) {
        binding.signup4AgreeButton.isEnabled = isApartInfoExist
        binding.signup4AgreeButton.alpha = if (isApartInfoExist) 1.0f else 0.5f
    }

    private fun completeButton(){
        binding.signup4AgreeButton.setOnClickListener {
            viewModel.setIsCompleteGetUserInfo(true)
            viewModel.userAllInfo()
            (activity as? SignUpActivity)?.setResultAndFinish()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun backProcess(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.initializeApartmentInfo()
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
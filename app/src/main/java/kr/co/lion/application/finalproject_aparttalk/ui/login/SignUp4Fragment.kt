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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSignUp4Binding
import kr.co.lion.application.finalproject_aparttalk.model.ApartmentModel
import kr.co.lion.application.finalproject_aparttalk.ui.login.viewmodel.SignUpViewModel

class SignUp4Fragment : Fragment() {

    private var _binding: FragmentSignUp4Binding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by activityViewModels {
        SignUpViewModelFactory(
            (requireActivity() as SignUpActivity).userRepository,
            (requireActivity() as SignUpActivity).apartmentRepository
        )
    }

    private lateinit var apartmentList: List<ApartmentModel>

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
        viewModel.user.observe(viewLifecycleOwner){
            when(it.apartCertification){
                true -> {binding.signup4RadioButtonRequest.isChecked = true}
                false -> {binding.signup4RadioButtonDefer.isChecked = true}
            }
        }
        viewModel.apartmentList.observe(viewLifecycleOwner){
            apartmentList = it
        }
    }

    private fun searchApartButton() {
        binding.signup4ApartLayout.setOnClickListener {
            showApartListBottomSheet()
        }
    }

    private fun showApartListBottomSheet() {
        val apartmentBottomSheetFragment = ApartmentBottomSheetFragment(apartmentList){ apartmentName, apartmentUid ->
            onApartSelected(apartmentName, apartmentUid)
        }
        apartmentBottomSheetFragment.show(requireActivity().supportFragmentManager, apartmentBottomSheetFragment.tag)
    }

    private fun onApartSelected(apartmentName: String, apartmentUid: String){
        binding.signup4ApartName.text = apartmentName
        viewModel.setApartInfo(apartmentUid)
        updateButtonState(apartmentName.isNotEmpty())
        updateApartDongHoState(apartmentName.isNotEmpty())
        viewModel.resetApartDongHo()
        viewModel.resetApartCertification()
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
        binding.signup4ApartDong.text = "${dong}동"
        binding.signup4ApartHo.text = "${ho}호"
        updateApartCertificationState(true)
        viewModel.setApartDongHo(dong, ho)
        viewModel.resetApartCertification()
    }

    private fun updateApartCertificationState(isApartDongHoExist:Boolean){
        binding.signup4IsapartcertificationLayout.isVisible = isApartDongHoExist
    }

    private fun selectIsApartCertificationRadioButton() {
        binding.signup4ApartcertificationRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selected = when (checkedId) {
                R.id.signup4_radio_button_request -> true
                R.id.signup4_radio_button_defer -> false
                else -> false
            }
            viewModel.setApartCertification(selected)
        }
    }

    private fun updateButtonState(isApartInfoExist:Boolean) {
        binding.signup4AgreeButton.isEnabled = isApartInfoExist
        binding.signup4AgreeButton.alpha = if (isApartInfoExist) 1.0f else 0.5f
    }

    private fun completeButton() {
        binding.signup4AgreeButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveUserInfo(requireContext()).join()
                (activity as? SignUpActivity)?.setResultAndFinish()
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }


    private fun backProcess(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.resetApartInfo()
            viewModel.resetApartCertification()
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
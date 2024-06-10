package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentSafetyManagementBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.SafetyManagementRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.SafetyManagementViewModel

class SafetyManagementFragment : Fragment() {

    lateinit var fragmentSafetyManagementBinding: FragmentSafetyManagementBinding
    lateinit var operationInfoActivity: OperationInfoActivity
    private val viewModel: SafetyManagementViewModel by viewModels()

    private val authRepository by lazy {
        AuthRepository(FirebaseAuthService(), LocalUserDataSource(requireContext()), LocalApartmentDataSource(requireContext()))
    }

    private val userRepository by lazy {
        UserRepository(UserDataSource(), LocalUserDataSource(requireContext()))
    }

    private val apartmentRepository by lazy {
        ApartmentRepository(ApartmentDataSource(), LocalApartmentDataSource(requireContext()))
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSafetyManagementBinding = FragmentSafetyManagementBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        gettingOperationInfoList()
        setRecyclerView()

        return fragmentSafetyManagementBinding.root
    }

    override fun onResume() {
        super.onResume()
        gettingOperationInfoList()
    }

    // 아파트 아이디 가져오기
    suspend fun gettingApartId(): String {
        var apartmentId = ""
        try {
            val authUser = authRepository.getCurrentUser()
            if (authUser != null) {
                val user = userRepository.getUser(authUser.uid)
                if (user != null) {
                    val apartment = apartmentRepository.getApartment(user.apartmentUid)
                    apartmentId = apartment!!.uid
                }
            }
        } catch (e: Exception) {
            //Log.e("SafetyManagementFragment", "Error fetching apartment ID", e)
        }
        //Log.d("SafetyManagementFragment", "Apartment ID: $apartmentId")
        return apartmentId
    }

    // 게시글 리스트 받아오기
    private fun gettingOperationInfoList(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val apartmentId = gettingApartId()
                //Log.d("SafetyManagementFragment", "Fetching list for apartment ID: $apartmentId")
                viewModel.gettingSafetyManagementList(apartmentId)
                //Log.d("SafetyManagementFragment", "Fetched list: ${viewModel.safetyManagementList}")
                fragmentSafetyManagementBinding.recyclerViewSafetyManagement.adapter?.notifyDataSetChanged()
            } catch (e: Exception) {
                //Log.e("SafetyManagementFragment", "Error fetching operation info list", e)
            }
        }
    }


    // 운영정보 안전관리계획 탭 리사이클러뷰 설정
    private fun setRecyclerView() {
        fragmentSafetyManagementBinding.recyclerViewSafetyManagement.apply {
            adapter = SafetyManagementRecyclerView(requireContext(), viewModel)
            layoutManager = LinearLayoutManager(operationInfoActivity)
            addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
        }
    }
}

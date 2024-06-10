package kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.auth.FirebaseAuthService
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentFinancialSystemBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.FinancialSystemRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.FinancialSystemViewModel

class FinancialSystemFragment : Fragment() {

    lateinit var fragmentFinancialSystemBinding: FragmentFinancialSystemBinding
    lateinit var operationInfoActivity: OperationInfoActivity
    private val viewModel: FinancialSystemViewModel by viewModels()

    private val authRepository by lazy {
        AuthRepository(FirebaseAuthService())
    }

    private val userRepository by lazy {
        UserRepository(UserDataSource(), LocalUserDataSource(requireActivity().applicationContext))
    }

    private val apartmentRepository by lazy {
        ApartmentRepository(ApartmentDataSource(), LocalApartmentDataSource(requireActivity().applicationContext))
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentFinancialSystemBinding = FragmentFinancialSystemBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        gettingOperationInfoList()
        setRecyclerView()


        return fragmentFinancialSystemBinding.root
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
            //Log.e("FinancialSystemFragment", "Error fetching apartment ID", e)
        }
        //Log.d("FinancialSystemFragment", "Apartment ID: $apartmentId")
        return apartmentId
    }

    // 게시글 리스트 받아오기
    private fun gettingOperationInfoList(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val apartmentId = gettingApartId()
                //Log.d("FinancialSystemFragment", "Fetching list for apartment ID: $apartmentId")
                viewModel.gettingFinancialSystemList(apartmentId)
                //Log.d("FinancialSystemFragment", "Fetched list: ${viewModel.biddingNoticeList}")
                fragmentFinancialSystemBinding.recyclerViewFinancialSystem.adapter?.notifyDataSetChanged()
            } catch (e: Exception) {
                //Log.e("FinancialSystemFragment", "Error fetching operation info list", e)
            }
        }
    }

    // 운영정보 재무제표 탭 리사이클러뷰 설정
    private fun setRecyclerView() {
        fragmentFinancialSystemBinding.recyclerViewFinancialSystem.apply {
            adapter = FinancialSystemRecyclerView(requireContext(), viewModel)
            layoutManager = LinearLayoutManager(operationInfoActivity)
            addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
        }
    }
}
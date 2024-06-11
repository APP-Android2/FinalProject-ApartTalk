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
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentContractInfoBinding
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.local.LocalUserDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.ApartmentDataSource
import kr.co.lion.application.finalproject_aparttalk.db.remote.UserDataSource
import kr.co.lion.application.finalproject_aparttalk.repository.ApartmentRepository
import kr.co.lion.application.finalproject_aparttalk.repository.AuthRepository
import kr.co.lion.application.finalproject_aparttalk.repository.UserRepository
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.ContractInfoRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.ContractInfoViewModel

class ContractInfoFragment : Fragment() {

    lateinit var fragmentContractInfoBinding : FragmentContractInfoBinding
    lateinit var operationInfoActivity: OperationInfoActivity
    private val viewModel: ContractInfoViewModel by viewModels()

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
        fragmentContractInfoBinding = FragmentContractInfoBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        gettingOperationInfoList()
        setRecyclerView()

        return fragmentContractInfoBinding.root
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
            //Log.e("BiddingNoticeFragment", "Error fetching apartment ID", e)
        }
        //Log.d("BiddingNoticeFragment", "Apartment ID: $apartmentId")
        return apartmentId
    }

    // 게시글 리스트 받아오기
    private fun gettingOperationInfoList(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val apartmentId = gettingApartId()
                //Log.d("BiddingNoticeFragment", "Fetching list for apartment ID: $apartmentId")
                viewModel.gettingContractInfoList(apartmentId)
                //Log.d("BiddingNoticeFragment", "Fetched list: ${viewModel.biddingNoticeList}")
                fragmentContractInfoBinding.recyclerViewContractInfo.adapter?.notifyDataSetChanged()
            } catch (e: Exception) {
                //Log.e("BiddingNoticeFragment", "Error fetching operation info list", e)
            }
        }
    }

    // 운영정보 입찰공고 탭 리사이클러뷰 설정
    private fun setRecyclerView() {
        fragmentContractInfoBinding.recyclerViewContractInfo.apply {
            adapter = ContractInfoRecyclerView(requireContext(), viewModel)
            layoutManager = LinearLayoutManager(operationInfoActivity)
            addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
        }
    }
}
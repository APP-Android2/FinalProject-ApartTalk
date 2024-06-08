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
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentBiddingNoticeBinding
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.OperationInfoActivity
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.adapter.OperationSecondRecyclerView
import kr.co.lion.application.finalproject_aparttalk.ui.entiremenu.OperationInfo.viewmodel.BiddingNoticeViewModel

class BiddingNoticeFragment : Fragment() {

    lateinit var fragmentBiddingNoticeBinding: FragmentBiddingNoticeBinding
    lateinit var operationInfoActivity: OperationInfoActivity
    private val viewModel: BiddingNoticeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBiddingNoticeBinding = FragmentBiddingNoticeBinding.inflate(layoutInflater)
        operationInfoActivity = activity as OperationInfoActivity

        gettingOperationInfoList()
        setRecyclerView()

        return fragmentBiddingNoticeBinding.root
    }

    override fun onResume() {
        super.onResume()
        // 게시글 리스트 받아옹기
    }

    // 아파트 아이디 가져오기
    suspend fun gettingApartId(): String {
        var apartmentId = ""
        val authUser = App.authRepository.getCurrentUser()
        if (authUser != null) {
            val user = App.userRepository.getUser(authUser.uid)
            if (user != null) {
                val apartment = App.apartmentRepository.getApartment(user.apartmentUid)
                apartmentId = apartment!!.uid
            }
        }
        return  apartmentId
    }

    // 게시글 리스트 받아오기
    private fun gettingOperationInfoList(){
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingBiddingNoticeList(gettingApartId())
            fragmentBiddingNoticeBinding.recyclerViewBiddingNotice.adapter?.notifyDataSetChanged()
        }
    }

    // 운영정보 입찰공고 탭 리사이클러뷰 설정
    private fun setRecyclerView(){
        fragmentBiddingNoticeBinding.recyclerViewBiddingNotice.apply {
            adapter = OperationSecondRecyclerView(parentFragmentManager, mutableListOf())
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
        }
    }
}

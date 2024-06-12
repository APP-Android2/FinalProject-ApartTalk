package kr.co.lion.application.finalproject_aparttalk.ui.community.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.App
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.MainActivity
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabQuestionBinding
import kr.co.lion.application.finalproject_aparttalk.ui.community.adapter.CommunityTabQuestionRecyclerViewAdapter
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityNotificationViewModel
import kr.co.lion.application.finalproject_aparttalk.ui.community.viewmodel.CommunityQuestionViewModel
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class TabQuestionFragment : Fragment() {
    lateinit var fragmentTabQuestionBinding: FragmentTabQuestionBinding
    lateinit var mainActivity: MainActivity
    private val viewModel: CommunityQuestionViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentTabQuestionBinding = FragmentTabQuestionBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        gettingCommunityPostList()
        settingRecyclerViewTabQuestion()
        settingFabTabQuestionAdd()
        //settingFloatingButton()

        return fragmentTabQuestionBinding.root
    }

    override fun onResume() {
        super.onResume()
        gettingCommunityPostList()
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
    private fun gettingCommunityPostList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.gettingCommunityQuestionList(gettingApartId())
            fragmentTabQuestionBinding.recyclerViewTabQuestion.adapter?.notifyDataSetChanged()
        }
    }

    // 커뮤니티 질문 탭 플로팅 버튼
    private fun settingFabTabQuestionAdd() {
        CoroutineScope(Dispatchers.Main).launch {
            certificationFab()
        }
    }

    suspend fun certificationFab() {
        val authUser = App.authRepository.getCurrentUser()

        if (authUser != null) {
            val user = App.userRepository.getUser(authUser.uid)
            fragmentTabQuestionBinding.fabTabQuestionAdd.setOnClickListener {
                if (user != null) {
                    if (user.apartCertification == true) {

                        val intent = Intent(mainActivity, CommunityActivity::class.java)
                        intent.putExtra(
                            "fragmentName",
                            CommunityFragmentName.COMMUNITY_ADD_FRAGMENT
                        )
                        startActivity(intent)
                    } else {
                        val toast = Toast.makeText(
                            requireContext(),
                            "인증된 입주민만 작성할 수 있습니다.",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            }
        }
    }

    // 플로팅 버튼 리사이클러뷰 스크롤 시 안 보이게
    fun settingFloatingButton() {
        fragmentTabQuestionBinding.apply {
            recyclerViewTabQuestion.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                var temp: Int = 0

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (temp == 1) {
                        super.onScrolled(recyclerView, dx, dy)
                    }
                    fabTabQuestionAdd.visibility = View.GONE
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    fabTabQuestionAdd.visibility = View.VISIBLE
                    temp = 1
                }
            })
        }
    }

    // 커뮤니티 질문 탭 리사이클러뷰 설정
    private fun settingRecyclerViewTabQuestion() {
        fragmentTabQuestionBinding.apply {
            recyclerViewTabQuestion.apply {
                adapter = CommunityTabQuestionRecyclerViewAdapter(requireContext(), viewModel)
                layoutManager = LinearLayoutManager(mainActivity)
                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }
}
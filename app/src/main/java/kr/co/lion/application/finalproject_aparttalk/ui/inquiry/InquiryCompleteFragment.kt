package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.model.InquiryModel
import kr.co.lion.application.finalproject_aparttalk.repository.InquiryRepository
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiryCompleteFragment : Fragment() {

    private lateinit var binding: FragmentInquiryCompleteBinding
    private lateinit var inquiryActivity: InquiryActivity
    private lateinit var adapter: InquiryCompleteAdapter
    private val repository = InquiryRepository()
    private val inquiryViewModel: InquiryViewModel by activityViewModels()
    private var inquiries: List<InquiryModel> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInquiryCompleteBinding.inflate(inflater, container, false)
        inquiryActivity = activity as InquiryActivity

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = InquiryCompleteAdapter(emptyList()) { inquiry ->
            inquiryViewModel.setSelectedInquiryModel(inquiry)
            (activity as InquiryActivity).replaceFragment(InquiryFragmentName.INQUIRY_FRAGMENT, false, true, null)
        }
        binding.inquiryCompleteRecyclerView.layoutManager = LinearLayoutManager(inquiryActivity)
        binding.inquiryCompleteRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        inquiryViewModel.userModel.observe(viewLifecycleOwner) { userModel ->
            if (userModel != null) {
                loadInquiries(userModel.apartmentUid)
            }
        }

        inquiryViewModel.selectedInquiryModel.observe(viewLifecycleOwner) { inquiryModel ->
            inquiryModel?.let {
                loadInquiries(it.apartmentUid)
            }
        }
    }

    private fun loadInquiries(apartmentUid: String) {
        inquiryViewModel.getCompletedInquiries(apartmentUid) { loadedInquiries ->
            inquiries = loadedInquiries
            adapter.updateList(loadedInquiries)
        }
    }

    fun searchInquiries(query: String) {
        val filteredInquiries = inquiries.filter {
            it.inquiryTitle.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredInquiries)
    }
}

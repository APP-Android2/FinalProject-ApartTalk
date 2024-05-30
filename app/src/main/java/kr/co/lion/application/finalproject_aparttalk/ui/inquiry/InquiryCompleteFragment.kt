package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiryCompleteBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowInquirycompleteBinding


class InquiryCompleteFragment : Fragment() {

    lateinit var fragmentInquiryCompleteBinding: FragmentInquiryCompleteBinding
    lateinit var inquiryActivity: InquiryActivity


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInquiryCompleteBinding = FragmentInquiryCompleteBinding.inflate(inflater)
        inquiryActivity = activity as InquiryActivity

        inquiryCompleteRecyclerView()

        return fragmentInquiryCompleteBinding.root
    }

    fun inquiryCompleteRecyclerView(){
        fragmentInquiryCompleteBinding.apply {
            inquiryCompleteRecyclerView.apply {
                adapter = InquiryCompleteAdapter()
                layoutManager = LinearLayoutManager(inquiryActivity)
            }
        }
    }

    inner class InquiryCompleteAdapter: RecyclerView.Adapter<InquiryCompleteAdapter.InquiryCompleteViewHolder>(){
        inner class InquiryCompleteViewHolder(val rowinquirycompleteBinding: RowInquirycompleteBinding): RecyclerView.ViewHolder(rowinquirycompleteBinding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiryCompleteViewHolder {
            // LayoutInflater를 parent.context로부터 가져옴
            val layoutInflater = LayoutInflater.from(parent.context)
            // RowReviewListBinding 인플레이션
            val binding = RowInquirycompleteBinding.inflate(layoutInflater, parent, false)
            // ViewHolder 인스턴스 생성 및 반환
            return InquiryCompleteViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: InquiryCompleteViewHolder, position: Int) {
            holder.rowinquirycompleteBinding.inquiryCompleteTextViewLabel.text = "비공개"
            holder.rowinquirycompleteBinding.inquiryCompleteTextViewLabel1.text = "답변완료"
            holder.rowinquirycompleteBinding.inquiryCompleteTitle.text = "작성인의 요청으로 내용이 비공개 입니다"
            holder.rowinquirycompleteBinding.inquiryCompleteDate.text = "2024-05-17"
            holder.rowinquirycompleteBinding.inquiryCompleteTime.text = "14:06"
        }

    }

}
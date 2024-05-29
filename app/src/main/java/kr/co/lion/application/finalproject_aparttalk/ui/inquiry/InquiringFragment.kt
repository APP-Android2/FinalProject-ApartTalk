package kr.co.lion.application.finalproject_aparttalk.ui.inquiry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.FragmentInquiringBinding
import kr.co.lion.application.finalproject_aparttalk.databinding.RowInquiringBinding
import kr.co.lion.application.finalproject_aparttalk.util.InquiryFragmentName

class InquiringFragment : Fragment() {

    lateinit var fragmentInquiringBinding: FragmentInquiringBinding
    lateinit var inquiryActivity: InquiryActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentInquiringBinding = FragmentInquiringBinding.inflate(inflater)
        inquiryActivity = activity as InquiryActivity

        inquiringRecyclerView()
        inquiringFloatingButton()

        return fragmentInquiringBinding.root
    }

    // RecyclerView
    fun inquiringRecyclerView(){
        fragmentInquiringBinding.apply {
            inquiringRecyclerView.apply {
                adapter = InquiringAdapter()
                layoutManager = LinearLayoutManager(inquiryActivity)

            }
        }
    }

    //floatingButton
    fun inquiringFloatingButton(){
        fragmentInquiringBinding.apply {
            inquiringFloatingActionButton.setOnClickListener {
                inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_WRITE_FRAGMENT,false,false,null)
            }
        }
    }

    // Adapter
    inner class InquiringAdapter: RecyclerView.Adapter<InquiringAdapter.InquiringViewHolder>(){
        inner class InquiringViewHolder(val rowInquiringBinding: RowInquiringBinding): RecyclerView.ViewHolder(rowInquiringBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InquiringViewHolder {
            // LayoutInflater를 parent.context로부터 가져옴
            val layoutInflater = LayoutInflater.from(parent.context)
            // RowReviewListBinding 인플레이션
            val binding = RowInquiringBinding.inflate(layoutInflater, parent, false)
            // ViewHolder 인스턴스 생성 및 반환
            return InquiringViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: InquiringViewHolder, position: Int) {
            holder.rowInquiringBinding.rowinquiringTextViewLabel.text = "비공개"
            holder.rowInquiringBinding.rowinquiringTextViewLabel1.text = "답변중"
            holder.rowInquiringBinding.rowinquiringTextViewTitle.text = "작성인의 요청으로 내용이 비공개 입니다"
            holder.rowInquiringBinding.rowinquiringTextViewDate.text = "2024-05-17"
            holder.rowInquiringBinding.rowinquiringTextViewTime.text = "14:06"

            holder.itemView.setOnClickListener {
                inquiryActivity.replaceFragment(InquiryFragmentName.INQUIRY_FRAGMENT,false,true,null)
            }
        }
    }
}
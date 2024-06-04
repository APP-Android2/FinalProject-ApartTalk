package kr.co.lion.application.finalproject_aparttalk.ui.community.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.application.finalproject_aparttalk.R
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabEtcBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData
import kr.co.lion.application.finalproject_aparttalk.ui.community.activity.CommunityActivity
import kr.co.lion.application.finalproject_aparttalk.ui.community.fragment.TabEtcFragment
import kr.co.lion.application.finalproject_aparttalk.util.CommunityFragmentName

class CommunityTabEtcRecyclerViewAdapter(val context: Context, var etcList: MutableList<PostData>) : RecyclerView.Adapter<CommunityTabEtcRecyclerViewAdapter.CommunityTabEtcViewHolder>() {
    inner class CommunityTabEtcViewHolder(rowCommunityTabEtcBinding: RowCommunityTabEtcBinding) : RecyclerView.ViewHolder(rowCommunityTabEtcBinding.root) {
        val rowCommunityTabEtcBinding : RowCommunityTabEtcBinding

        init {
            this.rowCommunityTabEtcBinding = rowCommunityTabEtcBinding

            this.rowCommunityTabEtcBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityTabEtcViewHolder {
        val rowCommunityTabEtcBinding = RowCommunityTabEtcBinding.inflate(LayoutInflater.from(parent.context))
        val communityTabEtcViewHolder = CommunityTabEtcViewHolder(rowCommunityTabEtcBinding)

        return communityTabEtcViewHolder
    }

    override fun getItemCount(): Int {
        return etcList.size
    }

    override fun onBindViewHolder(holder: CommunityTabEtcViewHolder, position: Int) {
        holder.rowCommunityTabEtcBinding.apply {
            textViewCommunityListLabelEtc.text = etcList[position].postType
            textViewCommunityListTitleEtc.text = etcList[position].postTitle
            textViewCommunityListLikeCntEtc.text = etcList[position].postLikeCnt.toString()
            textViewCommunityListCommentCntEtc.text = etcList[position].postCommentCnt.toString()
            textViewCommunityListDateEtc.text = etcList[position].postAddDate

            CoroutineScope(Dispatchers.Main).launch {
                if (etcList[position].postImages != null) {
                    // 어떻게 해야 하나...
                } else {
                    imageViewCommunityListEtc.setImageResource(R.color.white)
                }
            }

            linearLayoutCommunityListEtc.setOnClickListener {
                val intent = Intent(context, CommunityActivity::class.java)
                intent.putExtra("fragmentName", CommunityFragmentName.COMMUNITY_DETAIL_FRAGMENT)
                intent.putExtra("postIdx", etcList[position].postIdx)
                context.startActivity(intent)
            }
        }
    }
}
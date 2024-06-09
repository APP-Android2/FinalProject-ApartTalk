package kr.co.lion.application.finalproject_aparttalk.ui.home.adapter

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowCommunityTabNotificationBinding
import kr.co.lion.application.finalproject_aparttalk.model.PostData

class AlarmAdapter : ListAdapter<PostData, AlarmAdapter.AlarmViewHolder>(differ) {

    private lateinit var recyclerviewClick: ItemOnClickListener

    inner class AlarmViewHolder(val binding:RowCommunityTabNotificationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: PostData){
            binding.apply {
                textViewCommunityListLabelNotification.text = item.postType
                textViewCommunityListTitleNotification.text = item.postTitle
                textViewCommunityListLikeCntNotification.text = item.postLikeCnt.toString()
                textViewCommunityListCommentCntNotification.text = item.postCommentCnt.toString()
                textViewCommunityListDateNotification.text = item.postAddDate
                root.setOnClickListener {
                    recyclerviewClick.alarmRecyclerviewClick(item.postIdx, item.postId, item.postApartId)
                }
            }
        }
    }

    fun setRecyclerviewClick(recyclerviewClick : ItemOnClickListener){
        this.recyclerviewClick = recyclerviewClick
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<PostData>(){
            override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return  AlarmViewHolder(RowCommunityTabNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    interface ItemOnClickListener{
        fun alarmRecyclerviewClick(postIdx: Int, postId:String, postApartId:String)
    }
}
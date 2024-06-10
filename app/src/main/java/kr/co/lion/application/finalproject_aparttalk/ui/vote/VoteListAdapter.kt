package kr.co.lion.application.finalproject_aparttalk.ui.vote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.application.finalproject_aparttalk.databinding.RowVoteBinding
import kr.co.lion.application.finalproject_aparttalk.model.VoteResultModel
import kr.co.lion.application.finalproject_aparttalk.util.VoteFragmentName

class VoteListAdapter(
    private val voteActivity: VoteActivity,
    private var voteItems: List<VoteResultModel>,
    private var pastVoteItems: List<VoteResultModel>
) : RecyclerView.Adapter<VoteListAdapter.VoteListViewHolder>() {

    inner class VoteListViewHolder(val binding: RowVoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoteListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowVoteBinding.inflate(layoutInflater, parent, false)
        return VoteListViewHolder(binding)
    }

    override fun getItemCount(): Int = pastVoteItems.size

    override fun onBindViewHolder(holder: VoteListViewHolder, position: Int) {
        val voteItem = pastVoteItems[position]
        holder.binding.rowVoteTextView1.text = voteItem.electionName
        holder.binding.rowVoteTextView2.text = voteItem.electionDate

        holder.itemView.setOnClickListener {
            voteActivity.replaceFragment(VoteFragmentName.VOTE_HISTORY_FRAGMENT, false, true, null)
        }
    }

    fun updateVoteItems(newVoteItems: List<VoteResultModel>, newPastVoteItems: List<VoteResultModel>) {
        voteItems = newVoteItems
        pastVoteItems = newPastVoteItems
        notifyDataSetChanged()
    }
}

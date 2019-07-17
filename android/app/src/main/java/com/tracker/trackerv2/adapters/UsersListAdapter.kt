package com.tracker.trackerv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tracker.trackerv2.R
import com.tracker.trackerv2.configs.Config
import com.tracker.trackerv2.configs.SearchUserConfigBundle
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import java.text.SimpleDateFormat
import java.util.Locale

class UsersListAdapter(private val context: Context, private var searchConfig: SearchUserConfigBundle? = null): RecyclerView.Adapter<UsersListAdapter.Holder>() {
    private var items = emptyList<UserEntity>()
    private var displayedItems = emptyList<UserEntity>()
    private var onItemClickListener: OnItemClickListener? =
        if (context is OnItemClickListener) context
        else null

    fun setItems(items: List<UserEntity>) {
        this.items = items
        filter(searchConfig)
    }

    fun onSearchConfigChanged(newSearchConfig: SearchUserConfigBundle) {
        if(this.searchConfig == null) this.searchConfig = newSearchConfig
        else {
            this.searchConfig!!.searchQuery = newSearchConfig.searchQuery
            this.searchConfig!!.sortOrder = newSearchConfig.sortOrder
        }
        filter(this.searchConfig)
    }

    private fun filter(searchConfig: SearchUserConfigBundle?) {
        displayedItems = if(searchConfig == null) items
        else items
            .filter { it.userId != searchConfig.userId }
            .filter { it.username.contains(searchConfig.searchQuery ?: "") || it.email.contains(searchConfig.searchQuery ?: "") || (it.phone != null && it.phone!!.contains(searchConfig.searchQuery ?: "")) }

        if(searchConfig != null) {
            displayedItems = when(searchConfig.sortOrder) {
                Config.SortOrder.CREATED_ASC -> displayedItems.sortedBy { it.createdAt }
                Config.SortOrder.CREATED_DESC -> displayedItems.sortedByDescending { it.createdAt }
                Config.SortOrder.ALPHA_DESC -> displayedItems.sortedByDescending { it.username }
                Config.SortOrder.ALPHA_ASC -> displayedItems.sortedBy { it.username }
                else -> displayedItems
            }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_card_item, null, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = this.displayedItems.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(displayedItems[position], onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(item: UserEntity)
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById(R.id.userItemAvatarView) as ImageView
        val fullname = itemView.findViewById(R.id.userItemNameView) as TextView
        val username = itemView.findViewById(R.id.userItemUsernameView) as TextView
        val joinedDate = itemView.findViewById(R.id.userItemJoinedOnView) as TextView
        val rightArrow = itemView.findViewById(R.id.userItemArrowRight) as ImageView

        fun bind(item: UserEntity, onItemClickListener: OnItemClickListener?) {
            fullname.text = item.firstname.plus(" ").plus(item.lastname)
            username.text = "@".plus(item.username)
            joinedDate.text = dateFormatter.format(item.createdAt)

            rightArrow.setOnClickListener { onItemClickListener?.onItemClicked(item) }
        }
    }

    companion object {
        internal val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    }
}
package com.lexu.tracking.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lexu.tracking.R
import com.lexu.tracking.custom.CircularProgressBar
import com.lexu.tracking.models.DashboardMembersProgressItem

class TeamMembersDashboardAdapter(private val context: Context): RecyclerView.Adapter<TeamMembersDashboardAdapter.Holder>() {
    private var items = emptyList<DashboardMembersProgressItem>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.team_members_dashboard_item_view, null, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], onItemClickListener)
    }

    fun setItems(items: List<DashboardMembersProgressItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }


    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rootView : View = itemView
        val itemIcon : CircularProgressBar = itemView.findViewById(R.id.itemUserIconView)
        val itemUsername: TextView = itemView.findViewById(R.id.itemUsernameView)
        val itemLoggedTime: TextView = itemView.findViewById(R.id.itemUserLoggedTimeView)


        fun bind(item: DashboardMembersProgressItem, onItemClickListener: OnItemClickListener?) {
            itemUsername.text = item.username
            itemLoggedTime.text = formatLoggedTime(item.loggedTime.loggedTime)
//            itemIcon.setImage(item.userIconUrl)
            itemIcon.setProgress(
                if (item.loggedTime.loggedTime > 8.0) 100F
                else item.loggedTime.loggedTime.div(8.0).times(100.0).toFloat()
            )
            rootView.setOnClickListener { onItemClickListener?.onItemClicked(item) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: DashboardMembersProgressItem)
    }

    companion object {
        private const val HOURS_IN_DAY = 24.0
        private const val HOURS_IN_WEEK = 7 * HOURS_IN_DAY

        private fun formatLoggedTime(value: Double) : String = when {
            value >= HOURS_IN_WEEK -> "${String.format("%.2f", value / HOURS_IN_WEEK + value % HOURS_IN_WEEK)} weeks"
            value >= HOURS_IN_DAY -> "${String.format("%.2f", value / HOURS_IN_DAY + value % HOURS_IN_DAY)} days"
            else -> "${String.format("%.2f", value)} hours"
        }
    }
}
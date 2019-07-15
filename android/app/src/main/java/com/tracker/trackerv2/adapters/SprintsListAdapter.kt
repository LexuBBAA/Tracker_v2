package com.tracker.trackerv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tracker.trackerv2.R
import com.tracker.trackerv2.datasource.providers.local.room.entity.SprintEntity
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class SprintsListAdapter(private val context: Context): RecyclerView.Adapter<SprintsListAdapter.Holder>() {
    private var items = emptyList<SprintEntity>()
    private var onItemClickListener: OnItemClickListener? = null

    fun setItems(items: List<SprintEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.sprint_list_item_view, null, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], onItemClickListener)
    }

    interface OnItemClickListener {
        fun onItemClicked(item: SprintEntity)
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.sprintTitleView) as TextView
        val status = itemView.findViewById(R.id.sprintStatusView) as TextView
        val endDate = itemView.findViewById(R.id.sprintEndDateView) as TextView
        val rightArrow = itemView.findViewById(R.id.sprintItemRightArrow) as ImageView

        fun bind(item: SprintEntity, onItemClickListener: OnItemClickListener?) {
            title.text = "[".plus(item.sprintId).plus("] ").plus(item.title)
            status.text = item.status
            endDate.text = formatDate(item.endDate)

            rightArrow.setOnClickListener { onItemClickListener?.onItemClicked(item) }
        }
    }

    companion object {
        private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        internal fun formatDate(date: Date?): String =
            if (date == null) "---"
            else dateFormatter.format(date)
    }
}
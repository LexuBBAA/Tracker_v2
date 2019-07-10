/*
 * Copyright (c) Bogdan Andrei Alexandru - 2017.
 */

package com.tracker.trackerv2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lexu.tracking.utils.TeamTask
import com.squareup.picasso.Picasso
import com.tracker.trackerv2.R

class TaskListAdapter(private val context: Context) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private var items = emptyList<TeamTask>()
    private var onItemClickListener: OnItemClickListener? =
        if (context is OnItemClickListener) context
        else null

    fun setItems(items: List<TeamTask>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_list_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position, onItemClickListener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailsContainer: RelativeLayout = view.findViewById(R.id.taskItemDetailsContainer)
        val titleLabel: TextView = view.findViewById(R.id.taskItemTitleLabel)
        val description: TextView = view.findViewById(R.id.taskItemDescription)
        val type: TextView = view.findViewById(R.id.taskItemTypeLabel)
        val status: TextView = view.findViewById(R.id.taskItemStatusLabel)
        val priority: TextView = view.findViewById(R.id.taskItemPriorityLabel)
        val assignee: ImageView = view.findViewById(R.id.taskItemAssigneeAvatarIcon)
        val arrowRight: ImageView = view.findViewById(R.id.taskItemArrowRight)

        fun bind(item: TeamTask, position: Int, onItemClickListener: OnItemClickListener?) {
            titleLabel.text = item.title
            description.text = ""
            type.text = item.type.name
            status.text = item.status.name
            priority.text = if (item.type.name == "ISSUE") "Critical"
            else "Normal"

            Picasso.get()
                .load(R.drawable.empty_circle_drawable_shape)
                .into(assignee)

            detailsContainer.setOnClickListener { onItemClickListener?.onItemClicked(item, position) }
            arrowRight.setOnClickListener { onItemClickListener?.onItemDetailsClicked(item, position) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: TeamTask, position: Int)
        fun onItemDetailsClicked(item: TeamTask, position: Int)
    }
}
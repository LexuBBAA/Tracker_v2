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
import com.squareup.picasso.Picasso
import com.tracker.trackerv2.R
import com.tracker.trackerv2.configs.Config
import com.tracker.trackerv2.configs.SearchTaskConfigBundle
import com.tracker.trackerv2.datasource.providers.local.room.entity.TaskEntity

class TaskListAdapter(private val context: Context, private var searchConfig: SearchTaskConfigBundle? = null) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private var items = emptyList<TaskEntity>()
    private var displayedItems = emptyList<TaskEntity>()
    private var onItemClickListener: OnItemClickListener? =
        if (context is OnItemClickListener) context
        else null

    fun setItems(items: List<TaskEntity>) {
        this.items = items
        filter(searchConfig)
        notifyDataSetChanged()
    }

    private fun filter(searchConfig: SearchTaskConfigBundle?) {
        displayedItems = if(searchConfig == null) items
        else items
            .asSequence()
            .filter { if(searchConfig.tasksProject != null) it.project == searchConfig.tasksProject else true }
            .filter { Config.TypeFilter.get(searchConfig.typeFilter.filter) == Config.TypeFilter.FILTER_ALL || it.type == searchConfig.typeFilter.filter!!.name }
            .filter { Config.StatusFilter.get(searchConfig.statusFilter.filter) == Config.StatusFilter.FILTER_ALL || it.status == searchConfig.statusFilter.filter!!.name }
            .filter { Config.PriorityFilter.get(searchConfig.priorityFilter.filter) == Config.PriorityFilter.FILTER_ALL || it.priority == searchConfig.priorityFilter.filter!!.name }
            .filter {
                when(searchConfig.assigneeFilter) {
                    Config.AssigneeFilter.FILTER_PERSONAL -> it.assignedTo == searchConfig.userId
                    Config.AssigneeFilter.FILTER_OWNED -> it.createdBy == searchConfig.userId
                    else -> true
                }
            }
            .filter { it.title.contains(searchConfig.searchQuery ?: "") || it.taskId!!.contains(searchConfig.searchQuery ?: "") }
            .toList()

        if(searchConfig != null) {
            displayedItems = when(searchConfig.sortOrder) {
                Config.SortOrder.CREATED_ASC -> displayedItems.sortedBy { it.createdDate }
                Config.SortOrder.CREATED_DESC -> displayedItems.sortedByDescending { it.createdDate }
                Config.SortOrder.ALPHA_ASC -> displayedItems.sortedBy { it.title }
                Config.SortOrder.ALPHA_DESC -> displayedItems.sortedByDescending { it.title }
                else -> displayedItems
            }
        }

        notifyDataSetChanged()
    }

    fun onSearchConfigChanged(newSearchConfig: SearchTaskConfigBundle) {
        if(this.searchConfig == null) this.searchConfig = newSearchConfig
        else {
            this.searchConfig!!.searchQuery = newSearchConfig.searchQuery
            this.searchConfig!!.statusFilter = newSearchConfig.statusFilter
            this.searchConfig!!.typeFilter = newSearchConfig.typeFilter
            this.searchConfig!!.priorityFilter = newSearchConfig.priorityFilter
            this.searchConfig!!.assigneeFilter = newSearchConfig.assigneeFilter
            this.searchConfig!!.sortOrder = newSearchConfig.sortOrder
        }
        filter(this.searchConfig)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_list_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.displayedItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(displayedItems[position], position, onItemClickListener)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailsContainer: RelativeLayout = view.findViewById(R.id.taskItemDetailsContainer)
        val taskId: TextView = view.findViewById(R.id.taskItemId)
        val titleLabel: TextView = view.findViewById(R.id.taskItemTitleLabel)
        val description: TextView = view.findViewById(R.id.taskItemDescription)
        val type: TextView = view.findViewById(R.id.taskItemTypeLabel)
        val status: TextView = view.findViewById(R.id.taskItemStatusLabel)
        val priority: TextView = view.findViewById(R.id.taskItemPriorityLabel)
        val assignee: ImageView = view.findViewById(R.id.taskItemAssigneeAvatarIcon)
        val arrowRight: ImageView = view.findViewById(R.id.taskItemArrowRight)

        fun bind(item: TaskEntity, position: Int, onItemClickListener: OnItemClickListener?) {
            titleLabel.text = item.title
            taskId.text = "#".plus(item.taskId)
            description.text = item.description ?: ""
            type.text = item.type
            status.text = item.status
            priority.text = item.priority

            Picasso.get()
                .load(R.drawable.empty_circle_drawable_shape)
                .into(assignee)

            detailsContainer.setOnClickListener { onItemClickListener?.onItemClicked(item, position) }
            arrowRight.setOnClickListener { onItemClickListener?.onItemDetailsClicked(item, position) }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: TaskEntity, position: Int)
        fun onItemDetailsClicked(item: TaskEntity, position: Int)
    }
}
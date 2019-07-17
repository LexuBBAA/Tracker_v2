package com.tracker.trackerv2.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.tracker.trackerv2.R

class BackButtonToolbar(context: Context, attributeSet: AttributeSet?): LinearLayout(context, attributeSet) {

    private var backButton: ImageView
    private var title: TextView
    private var addButton: ImageView
    private var editButton: ImageView

    private var titleRes: String
    private var showEdit: Boolean
    private var showAdd: Boolean
    private var onBackClickListener: OnBackClickListener? = null
    private var onAddClickListener: OnAddButtonClickListener? = null
    private var onEditClickListener: OnEditClickListener? = null

    constructor(context: Context): this(context, null)

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.BackButtonToolbar,
            0, 0
        )

        try {
            titleRes = typedArray.getString(R.styleable.BackButtonToolbar_title) ?: "Tracker_v2"
            showEdit = typedArray.getBoolean(R.styleable.BackButtonToolbar_showEdit, false)
            showAdd = typedArray.getBoolean(R.styleable.BackButtonToolbar_showAdd, false)
        } finally {
            typedArray.recycle()
        }

        val layout = LayoutInflater.from(context).inflate(R.layout.back_button_toolbar_layout, rootView as ViewGroup, true)
        backButton = layout.findViewById(R.id.backButtonToolbarBtn)
        title = layout.findViewById(R.id.backButtonToolbarTitle)
        addButton = layout.findViewById(R.id.backButtonToolbarAddBtn)
        editButton = layout.findViewById(R.id.backButtonToolbarEditBtn)

        title.text = titleRes
        backButton.setOnClickListener { onBackClickListener?.onBackClicked() }
        addButton.visibility = if (showAdd) View.VISIBLE
        else View.GONE
        editButton.visibility = if(showEdit) View.VISIBLE
        else View.GONE


        addButton.setOnClickListener { onAddClickListener?.onAddClicked() }
        editButton.setOnClickListener { onEditClickListener?.onEditClicked() }
    }

    fun setTitle(title: String) {
        titleRes = title
        this.title.text = titleRes
    }

    fun setOnBackClickListener(onBackClickListener: OnBackClickListener) {
        this.onBackClickListener = onBackClickListener
    }

    fun setOnEditClickListener(onEditClickListener: OnEditClickListener?) {
        this.onEditClickListener = onEditClickListener
    }

    fun setOnAddClickListener(onAddButtonClickListener: OnAddButtonClickListener?) {
        this.onAddClickListener = onAddButtonClickListener
    }

    fun showEditButton(show: Boolean = true) {
        this.showEdit = show
        this.editButton.visibility = if(this.showEdit) View.VISIBLE
        else View.GONE
    }

    fun showAddButton(show: Boolean = true) {
        this.showAdd = show
        this.addButton.visibility = if(this.showAdd) View.VISIBLE
        else View.GONE
    }

    fun setEditMode(isEdit: Boolean) {
        this.editButton.setImageResource(if(isEdit) R.drawable.ic_cancel_black_24dp else R.drawable.ic_edit_white_24dp)
    }

    interface OnBackClickListener {
        fun onBackClicked()
    }

    interface OnEditClickListener {
        fun onEditClicked()
    }

    interface OnAddButtonClickListener {
        fun onAddClicked()
    }
}
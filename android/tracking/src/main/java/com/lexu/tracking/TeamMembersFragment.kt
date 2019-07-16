package com.lexu.tracking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lexu.tracking.adapters.TeamMembersDashboardAdapter
import com.lexu.tracking.delegates.TeamMembersFragmentDelegate
import com.lexu.tracking.models.DashboardMembersProgressItem

class TeamMembersFragment: Fragment(), TeamMembersDashboardAdapter.OnItemClickListener, ITrackingTask {
    private lateinit var rootView: View
    private lateinit var headerContainer: LinearLayout
    private lateinit var titleView: TextView
    private lateinit var seeMoreView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var loadingContainer: FrameLayout
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var errorMessage: TextView

    private lateinit var adapter: TeamMembersDashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_team_members_progress, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerContainer = rootView.findViewById(R.id.teamMembersProgressHeaderContainer) as LinearLayout
        titleView = rootView.findViewById(R.id.teamMembersProgressTitleView) as TextView
        seeMoreView = rootView.findViewById(R.id.teamMembersProgressSeeMoreView) as TextView
        recyclerView = rootView.findViewById(R.id.teamMembersProgressRecyclerView) as RecyclerView
        loadingContainer = rootView.findViewById(R.id.teamMembersStatusLoadingContainer) as FrameLayout
        loadingProgressBar = rootView.findViewById(R.id.teamMembersStatusProgressBar) as ProgressBar
        errorMessage = rootView.findViewById(R.id.teamMembersStatusErrorMessage) as TextView

        adapter = TeamMembersDashboardAdapter(context!!)
        adapter.setOnItemClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        loadingContainer.visibility = View.VISIBLE
        loadingProgressBar.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE

        seeMoreView.setOnClickListener {
            val delegate = context as TeamMembersFragmentDelegate?
            delegate?.onNavigateToUsersListClicked()
        }
    }

    fun setupData(userStats: List<DashboardMembersProgressItem>) {
        if(userStats.isEmpty()) {
            recyclerView.visibility = View.GONE
            loadingContainer.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            loadingContainer.visibility = View.GONE
            loadingProgressBar.visibility = View.GONE
            errorMessage.visibility = View.GONE
        }
        adapter.setItems(userStats)
    }

    override fun onItemClicked(item: DashboardMembersProgressItem) {
        Log.d(TeamMembersDashboardAdapter::class.simpleName, "> User selected: $item")
        val delegate = context as TeamMembersFragmentDelegate?
        delegate?.onNavigateToUserDetailsClicked(item.userId)
    }

    override fun setLoading() {
        recyclerView.visibility = View.GONE
        loadingContainer.visibility = View.VISIBLE
        loadingProgressBar.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
    }
}
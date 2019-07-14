package com.tracker.trackerv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.tracker.trackerv2.adapters.TaskListAdapter
import com.tracker.trackerv2.adapters.UsersListAdapter
import com.tracker.trackerv2.configs.Config
import com.tracker.trackerv2.configs.SearchTaskConfigBundle
import com.tracker.trackerv2.configs.SearchUserConfigBundle
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import kotlinx.android.synthetic.main.activity_search_users.*
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchUsersActivity : AppCompatActivity(), UsersListAdapter.OnItemClickListener {
    private lateinit var sessionProvider: UserSessionProvider
    private lateinit var appDatabase: AppDatabase

    private lateinit var searchConfig: SearchUserConfigBundle

    private lateinit var adapter: UsersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_users)

        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        val config = intent.getSerializableExtra(KEY_SEARCH_CONFIG_EXTRA) as SearchUserConfigBundle?
        CoroutineScope(Dispatchers.IO).launch {
            searchConfig = config ?:SearchUserConfigBundle(sessionProvider.getUserId() ?: "")
        }

        setupUi()

        CoroutineScope(Dispatchers.IO).launch {
            delay(1500)

            val users = appDatabase.getUsersProvider().getAll()

            runOnUiThread {
                searchUsersRecyclerView.visibility = View.VISIBLE
                searchUsersLoadingView.visibility = View.GONE
                searchUsersErrorMessage.visibility = if(users.isEmpty()) View.VISIBLE
                else View.GONE
                adapter.setItems(users)
                if(searchConfig.userId.isNotEmpty()) adapter.onSearchConfigChanged(searchConfig)
            }
        }
    }

    private fun setupUi() {
        adapter = UsersListAdapter(this)
        searchUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        searchUsersRecyclerView.adapter = adapter

        val textWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchConfig.searchQuery = s?.toString() ?: ""
                adapter.onSearchConfigChanged(searchConfig)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //  not needed
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //  not needed
            }
        }
        searchUsersInputField.addTextChangedListener(textWatcher)
        searchUsersFilterButton.setOnClickListener {
            searchUsersFiltersContainer.visibility = if(searchUsersFiltersContainer.visibility == View.VISIBLE) View.GONE
            else View.VISIBLE
        }

        searchUsersBackButton.setOnClickListener { finish() }

        val sortOrderSpinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Config.SortOrder.values().map { it.value })
        sortOrderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchUsersSortBySpinner.adapter = sortOrderSpinnerAdapter
        searchUsersSortBySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //  not used
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                searchConfig.sortOrder = Config.SortOrder.get(searchUsersSortBySpinner.selectedItem as String)
                adapter.onSearchConfigChanged(searchConfig)
            }
        }

        searchUsersFiltersContainer.visibility = View.GONE
        searchUsersRecyclerView.visibility = View.GONE
        searchUsersNoResultsContainer.visibility = View.VISIBLE
        searchUsersErrorMessage.visibility = View.GONE
        searchUsersLoadingView.visibility = View.VISIBLE
    }

    override fun onItemClicked(item: UserEntity) {
        val intent = Intent(this, PersonalStatusDetailsActivity::class.java)
        intent.putExtra(PersonalStatusDetailsActivity.KEY_USER_ID_EXTRA, item.userId)
        startActivity(intent)
    }

    companion object {
        const val KEY_SEARCH_CONFIG_EXTRA = "search_users_config_extra"
    }
}

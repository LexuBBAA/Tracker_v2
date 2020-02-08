package com.tracker.trackerv2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tracker.trackerv2.custom.BackButtonToolbar
import kotlinx.android.synthetic.main.activity_add_new_entry.*

class AddNewEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_entry)

        addEntryBackButtonToolbar.setOnBackClickListener(object: BackButtonToolbar.OnBackClickListener {
            override fun onBackClicked() {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        })

        addNewTeam.setOnClickListener { navigateToNewTeamActivity() }
        addNewProject.setOnClickListener { navigateToNewProjectActivity() }
        addNewSprint.setOnClickListener { navigateToNewSprintActivity() }
        addNewTask.setOnClickListener { navigateToNewTaskActivity() }
    }

    private fun navigateToNewTeamActivity() {
        val intent = Intent(this, CreateTeamActivity::class.java)
        startActivityForResult(intent, DashboardActivity.REQUEST_CODE_ADD_ENTRY)
    }

    private fun navigateToNewProjectActivity() {
        val intent = Intent(this, CreateProjectActivity::class.java)
        startActivityForResult(intent, DashboardActivity.REQUEST_CODE_ADD_ENTRY)
    }

    private fun navigateToNewSprintActivity() {
        val intent = Intent(this, CreateSprintActivity::class.java)
        startActivityForResult(intent, DashboardActivity.REQUEST_CODE_ADD_ENTRY)
    }

    private fun navigateToNewTaskActivity() {
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivityForResult(intent, DashboardActivity.REQUEST_CODE_ADD_ENTRY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == DashboardActivity.REQUEST_CODE_ADD_ENTRY) {
            if(resultCode == Activity.RESULT_OK) {
                setResult(resultCode)
                finish()
            }
        } else super.onActivityResult(requestCode, resultCode, data)
    }
}

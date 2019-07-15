package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.iid.FirebaseInstanceId
import com.lexu.auth.delegates.NavigationDelegate
import com.lexu.auth.models.LoginBody
import com.lexu.auth.models.RegisterBody
import com.lexu.auth.views.LoginFragment
import com.lexu.auth.views.RegisterFragment
import com.lexu.auth.views.ResetFragment
import com.tracker.trackerv2.datasource.providers.local.UserSessionProvider
import com.tracker.trackerv2.datasource.providers.local.room.database.AppDatabase
import com.tracker.trackerv2.datasource.providers.local.room.entity.TokenEntity
import com.tracker.trackerv2.datasource.providers.local.room.entity.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationDelegate.NavigationPresenter {
    enum class DisplayMode {
        LOGIN, REGISTER, RESET_PASSWORD
    }

    private lateinit var sessionProvider : UserSessionProvider
    private lateinit var appDatabase : AppDatabase

    private val loginFragment = LoginFragment(this)
    private val registerFragment = RegisterFragment(this)
    private val resetPasswordFragment = ResetFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sessionProvider = UserSessionProvider(this)
        appDatabase = AppDatabase.getDatabase(this)

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if(!task.isSuccessful) {
                Log.e(MainActivity::class.java.simpleName, "FCM failed to generate deviceId", task.exception)
                return@addOnCompleteListener
            }

            Log.e(this::class.java.simpleName, ">>> DeviceId: ")
            Log.e(this::class.java.simpleName, task.result?.token ?: "null")
        }
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { result ->
            onTokenReceived(result.token)
        }
    }

    @MainThread
    override fun onNavigateToLogin() {
        showFragment(DisplayMode.LOGIN)
    }

    @MainThread
    override fun onNavigateToRegister() {
        showFragment(DisplayMode.REGISTER)
    }

    @MainThread
    override fun onNavigateToResetPass() {
        showFragment(DisplayMode.RESET_PASSWORD)
    }

    @MainThread
    override fun onLoginClicked(loginBody: LoginBody) {
        CoroutineScope(Dispatchers.IO).async {
            appDatabase.getUsersProvider().getAll().firstOrNull {
                (it.email == loginBody.email || it.username == loginBody.username || it.phone == loginBody.phone) && it.password == loginBody.password
            }?.apply {
                userId?.let {
                    sessionProvider.saveSession(it)
                    runOnUiThread {
                        navigateToDashboard()
                    }
                }
            }
        }
    }

    @MainThread
    override fun onRegisterClicked(registerBody: RegisterBody) {
        CoroutineScope(Dispatchers.IO).launch {
            appDatabase.getUsersProvider().create(
                UserEntity(
                    userId = "usr-2019-asfas${(appDatabase.getUsersProvider().getAll().count() + 1).toString().padEnd(10, 'x')}",
                    username = registerBody.username,
                    email = registerBody.email,
                    password = registerBody.password
                )
            )?.apply {
                if(userId == null) return@apply
                appDatabase.getTokensProvider().create(TokenEntity())
                sessionProvider.saveSession(userId)
                runOnUiThread {
                    navigateToDashboard()
                }
            }
        }
    }

    override fun onResetPassClicked(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = appDatabase.getUsersProvider().getAll().first { it.email == email }
            user.password = "Pass123"
            appDatabase.getUsersProvider().update(user)
        }

        runOnUiThread {
            showFragment(DisplayMode.LOGIN)
        }
    }

    override fun onLoginFailure(exception: Exception) {
        //  TODO
    }

    override fun onRegisterFailure(exception: Exception) {
        //  TODO
    }

    override fun onResetPassFailure(exception: Exception) {
        //  TODO
    }

    private fun onTokenReceived(token: String) {
        Log.i(MainActivity::class.simpleName, "Token: $token")

        showFragment(DisplayMode.LOGIN)
        mainFragmentContainer
    }

    private fun showFragment(mode: DisplayMode) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment: Fragment = when(mode) {
            DisplayMode.LOGIN -> loginFragment
            DisplayMode.REGISTER -> registerFragment
            DisplayMode.RESET_PASSWORD -> resetPasswordFragment
        }

        transaction.replace(mainFragmentContainer.id, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    @MainThread
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

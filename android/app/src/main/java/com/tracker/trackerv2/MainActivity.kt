package com.tracker.trackerv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lexu.auth.delegates.NavigationDelegate
import com.lexu.auth.views.LoginFragment
import com.lexu.auth.views.RegisterFragment
import com.lexu.auth.views.ResetFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationDelegate.NavigationPresenter {
    enum class DisplayMode {
        LOGIN, REGISTER, RESET_PASSWORD
    }
    
    private val loginFragment = LoginFragment(this)
    private val registerFragment = RegisterFragment(this)
    private val resetPasswordFragment = ResetFragment(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onNavigateToLogin() {
        showFragment(DisplayMode.LOGIN)
    }

    override fun onNavigateToRegister() {
        showFragment(DisplayMode.REGISTER)
    }

    override fun onNavigateToResetPass() {
        showFragment(DisplayMode.RESET_PASSWORD)
    }

    override fun onLoginSuccessful() {
        navigateToDashboard()
    }

    override fun onRegisterSuccessful() {
        navigateToDashboard()
    }

    override fun onResetPassSuccessful() {
        showFragment(DisplayMode.LOGIN)
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

    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}

package com.gideonst.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*
import com.facebook.GraphRequest


class SecondActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_sign_in_fb.setOnClickListener({
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
//                            startActivity(Intent(applicationContext, AuthenticatedActivity::class.java))

                            val accessToken = loginResult.accessToken.token
                            Log.i("accessToken", accessToken)

                            val request = GraphRequest.newMeRequest(loginResult.accessToken) { _ , response ->
                                Log.i("LoginActivity", response.toString())
                                // Get facebook data from login
//                                val bFacebookData = getFacebookData(`object`)
                            }
                            bundle = Bundle()
                            bundle.putString("fields", "first_name, last_name, email")
                            request.parameters = bundle
                            request.executeAsync()

                        }

                        override fun onCancel() {
                            Log.d("MainActivity", "Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            Log.d("MainActivity", "Facebook onError.")

                        }
                    })
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}

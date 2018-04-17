package com.gideonst.login

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

/**
 * Created by GideonST on 4/17/2018.
 */
class LoginApp :Application() {


    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }


}
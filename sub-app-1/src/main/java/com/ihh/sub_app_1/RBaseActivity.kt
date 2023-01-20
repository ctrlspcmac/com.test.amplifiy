package com.ihh.sub_app_1

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ihh.sub_app_1.library_interface.RDependency
import com.ihh.sub_app_1.library_interface.RInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

open class RBaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        val applicationContext = this.applicationContext

        RInjector.inject(this, object : RDependency {
            override fun getContext(): Context {
                return applicationContext
            }
        })

        // force using portrait orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

        super.onCreate(savedInstanceState)
    }
}
package com.ihh.amplifytest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihh.sub_app_1.library_interface.RLibraryClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username = "renzy.nanquil"
        val password = "hm3QcC84ms"
        Backend.signIn(username, password)
        //Backend.saveItem()
       // Backend.queryNotes()
        callLibrary()
    }

    private fun callLibrary() {
        RLibraryClient.getInstance().startActivity(
            context = this.applicationContext,
            sampleStr = "PROD"
        )
        //  finish()
    }

}
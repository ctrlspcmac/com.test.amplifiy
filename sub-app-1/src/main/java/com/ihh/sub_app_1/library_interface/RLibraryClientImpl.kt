package com.ihh.sub_app_1.library_interface

import android.content.Context
import android.content.Intent
import com.ihh.sub_app_1.RMainActivity

class RLibraryClientImpl: RLibraryClient {

    override fun startActivity(context: Context, sampleStr: String) {

        val intent = Intent(context, RMainActivity::class.java)
        intent.putExtra("TEST", sampleStr)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)

    }
}
package com.ihh.sub_app_1.library_interface

import android.content.Context

interface RLibraryClient {

    fun startActivity(context: Context, sampleStr: String)

    companion object {

        fun getInstance(): RLibraryClient {

            return RLibraryClientImpl()
        }

    }
}
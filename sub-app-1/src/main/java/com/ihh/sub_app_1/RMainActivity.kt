package com.ihh.sub_app_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.amplifyframework.core.Amplify
import com.ihh.data_utils_lib.data.SampleData1

class RMainActivity : RBaseActivity() {

    private val viewModel: SampleVM1 by viewModels() { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rmain)
        val username = "test123"
        val password = "Password123"

        signIn(username, password)
        val a = viewModel.getText()
        val b = SampleData1()

        Toast.makeText(this, b.greet(), Toast.LENGTH_SHORT).show()
        setViews()
    }

    private fun signIn(username: String, password: String) {
        Amplify.Auth.signIn(username, password,
            { result ->
                if (result.isSignInComplete) {
                    Log.i("AUTH", "Sign in succeeded")
                } else {
                    Log.i("AUTH", "Sign in not complete")
                }
            },
            {
                Log.e("AUTH", "Failed to sign in", it)
            }
        )

    }

    private fun setViews() {
        val btn = findViewById<Button>(R.id.btnTest)
         btn.setOnClickListener {
             val intent = Intent(applicationContext, Sub2Activity::class.java)
             startActivity(intent)
         }
    }
}
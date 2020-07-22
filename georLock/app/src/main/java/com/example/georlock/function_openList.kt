package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_authorization_list.*
import kotlinx.android.synthetic.main.activity_function_open_list.*
import kotlinx.android.synthetic.main.activity_login.*

class function_openList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_open_list)

        back_list1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
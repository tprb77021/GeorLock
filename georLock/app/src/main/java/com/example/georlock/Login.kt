package com.example.georlock

import android.content.Context
import android.content.Intent
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            Log.i("testLog", "click")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

// \( *^_^*)/ 진주님 화이팅!!
// ('')( :)(..)(: ) 데굴데굴
//enable을 쓰지말자 !!
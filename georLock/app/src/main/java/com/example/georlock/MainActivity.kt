package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            Toast.makeText(this, "문이 열렸습니다.", Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener {
            val intent = Intent(this, function_openList::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, AuthorizationList::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}
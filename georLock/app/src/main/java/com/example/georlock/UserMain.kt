package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_authorization_list.*
import kotlinx.android.synthetic.main.activity_function_open_list.*
import kotlinx.android.synthetic.main.activity_user_main.*
import java.net.HttpURLConnection
import java.net.URL

class UserMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        var tmp:String?=""

        if(intent.hasExtra("infos")){
            tmp=intent.getStringExtra("infos")
            val infos:List<String>? =
                tmp?.split("@")
            textView1.setText(infos?.get(0))
            textView3.setText(infos?.get(1))

        }


        edit_user.setOnClickListener {
            val intent = Intent(this, UserEdit::class.java)
            Log.i("testLog", "${tmp}")
            intent.putExtra("infoss", tmp)
            startActivity(intent)
        }





    }



}
package com.example.georlock


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.activity_info.username
import java.net.HttpURLConnection
import java.net.URL

class Accessmodity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var strings: String? = intent.getStringExtra("info")?.replace("\n", "");
        var infos: List<String>? = strings?.split(" ")
        Log.i("testLog", "${infos?.size}")

        if (intent.hasExtra("info")) {
            Log.i("testLog", intent.getStringExtra("info").toString())
            empNum.setText(infos?.get(2))
            username.setText(infos?.get(5))
            intime.setText(infos?.get(9))
            outtime.setText(infos?.get(11))
        }

        button_delete.setOnClickListener {

            Log.i("testLog", "loginclick u:${username.text}")
            Thread() {
                deleteMainLog("${infos?.get(2)}")
                runOnUiThread {
                    val intent = Intent(this, AuthorizationList::class.java)
                    startActivity(intent)
                }
            }.start()
        }

            button_update.setOnClickListener {

                Log.i("testLog", "loginclick u:${username.text}")
                Thread() {
                    UpdateMainLog("${empNum.text}","${intime.text}","${outtime.text}")
                    runOnUiThread {
                        val intent = Intent(this, AuthorizationList::class.java)
                        startActivity(intent)
                    }
                }.start()
            }
        }

        fun deleteMainLog(empNo: String) {
            val url = URL("${Static.server_url}/delete?empNo=${empNo}")
            val conn = url.openConnection() as HttpURLConnection // casting
            Log.i("testLog", "conn.responseCode : ${conn.responseCode}")

            if (conn.responseCode == 200) {
                val txt = url.readText()
                /*val arr = JSONArray(txt)
            var item = arr*/
            }
        }

        fun UpdateMainLog(empNo: String,intime: String,outtime:String) {
            val url = URL("${Static.server_url}/update?empNo=${empNo}&intime=${intime}&outtime=${outtime}")
            val conn = url.openConnection() as HttpURLConnection // casting
            Log.i("testLog", "conn.responseCode : ${conn.responseCode}")

            if (conn.responseCode == 200) {
                val txt = url.readText()
                /*val arr = JSONArray(txt)
            var item = arr*/
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    }


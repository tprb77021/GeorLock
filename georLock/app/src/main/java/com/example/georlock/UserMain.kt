package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_authorization_list.*
import kotlinx.android.synthetic.main.activity_function_open_list.*
import kotlinx.android.synthetic.main.activity_user_edit.*
import kotlinx.android.synthetic.main.activity_user_main.*
import java.net.HttpURLConnection
import java.net.URL

class UserMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
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


        opencall.setOnClickListener {
            Toast.makeText(this, "문이 열림 요청함.", Toast.LENGTH_SHORT).show()
            Thread() {
                val infos:List<String>? =
                    tmp?.split("@")
                var list: String = opencall( "${infos?.get(2)}")
                Log.i("testLog", "opencall : ${infos?.get(2)} ")
                runOnUiThread {
                    Log.i("testLog", "loginedededed : ${list}")
                }
            }.start()


        }


    }

    fun opencall(empNo:String):String{
        val url = URL("http://192.168.0.88:8090/opencall?empNo=${empNo}")
        val conn = url.openConnection() as HttpURLConnection // casting
        Log.i("testLog", "conn.responseCode : ${conn.responseCode}")

        if(conn.responseCode == 200){
            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"
        } else return "null"
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
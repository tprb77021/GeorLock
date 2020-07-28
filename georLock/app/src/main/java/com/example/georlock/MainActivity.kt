package com.example.georlock

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_main.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

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

        open.setOnClickListener {

            Toast.makeText(this, "문이 열렸습니다.", Toast.LENGTH_SHORT).show()
            Thread() {

                var  temp =""
                if(intent.hasExtra("infos")){
                    temp = intent.getStringExtra("infos").toString()
                }
                var list: String = open( "${temp}")
                Log.i("testLog", "open : ${temp} ")

                runOnUiThread {
                    Log.i("testLog", "loginedededed : ${list}")
                    if(list.equals("openSuccess")){
                        open.setBackgroundColor(Color.GREEN)
                    }
                    val handler = Handler()
                    handler.postDelayed({
                        open.setBackgroundColor(Color.RED)

                    }, 3000)
                }
            }.start()
        }

    }



    fun open(empNo:String):String{
        val url = URL("http://192.168.0.88:8090/open?empNo=${empNo}")
        val conn = url.openConnection() as HttpURLConnection // casting
        Log.i("testLog", "conn.responseCode : ${conn.responseCode}")

        if(conn.responseCode == 200){
            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"
        } else return "null"
    }
    fun UpdateMainLog(empNo:String,userPw:String):String{
        val url = URL("http://192.168.0.88:8090/login?empNo=${empNo}&userPw=${userPw}")
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

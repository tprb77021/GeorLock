package com.example.georlock

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var Butts:Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        Thread() {
            var doors: String = Door()
            runOnUiThread {
                if(doors.equals("0")){
                    open.setBackgroundColor(Color.RED)
                }else{
                    open.setBackgroundColor(Color.GREEN)
                }
            }
        }.start()



        button2.setOnClickListener {
            val intent = Intent(this, Function_openList::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(this, AuthorizationList::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener {
            MyApplication.prefs.delete("empNo")
            MyApplication.prefs.delete("userPw")
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
                runOnUiThread {
                    }
            }.start()
        }

    }



    fun open(empNo:String):String{
        val url = URL("${Static.server_url}/open?empNo=${empNo}")

            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"

    }

    fun UpdateMainLog(empNo:String,userPw:String):String{
        val url = URL("${Static.server_url}/login?empNo=${empNo}&userPw=${userPw}")

            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"

    }



    fun Door():String{
        val url = URL("${Static.server_url}/door")

            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"

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

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
import java.net.URLEncoder

class UserEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var tmp:String?= ""
        var infos:List<String>? =null
        if(intent.hasExtra("infoss")){
            tmp=intent.getStringExtra("infoss")
             infos= tmp?.split("@")
        }



        request_ckeck.setOnClickListener {
            val intent = Intent(this, Login::class.java)

            if ("${pwd1.text.toString()}".equals("${pwd2.text.toString()}")) {
                Thread() {


                    var list: String =
                        UpdateMainLog( "${infos?.get(2).toString()}",  "${pwd1.text.toString()}")
                    runOnUiThread {
                        Log.i("testLog", "loginedededed : ${infos?.get(2).toString()}")
                    }
                }.start()
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "비밀번호 확인필요.", Toast.LENGTH_SHORT).show()
            }
        }

        cancel.setOnClickListener {
            val intent = Intent(this, UserMain::class.java)
            Log.i("testLog", "loginedededed : ${tmp}")
            intent.putExtra("infos",tmp)
            startActivity(intent)
        }


    }

    fun UpdateMainLog(empNo:String,userPw:String):String{
        val url = URL("http://192.168.0.88:8090/userupdate?empNo=${empNo}&userPw=${userPw}")
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
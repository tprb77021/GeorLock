package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_authorization_list.*
import kotlinx.android.synthetic.main.activity_function_open_list.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class function_openList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_open_list)

        Thread(){
          var list:ArrayList<String> = UpdateMainLog()
            runOnUiThread{
                Log.i("testLog", "loginclick : ${list}")
                openlistview.adapter = ArrayAdapter<String>(
                    this,
//            android.R.layout.simple_list_item_1,
                    R.layout.layout_list,
                    list)
            }
        }.start()


        back_list1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    fun UpdateMainLog():ArrayList<String>{
        val url = URL("http://192.168.0.88:8090/openlist")
        val conn = url.openConnection() as HttpURLConnection // casting
        Log.i("testLog", "conn.responseCode : ${conn.responseCode}")
        var list:ArrayList<String> = arrayListOf()
        if(conn.responseCode == 200){
            var txt = url.readText()
            var arr:JSONArray = JSONArray(txt)
            for(i in 0 until arr.length()){
                var obj:JSONObject = arr.get(i) as JSONObject
                Log.i("testLog", "cc :${ obj["intime"].toString()}")
                list.add("사번 : ${ obj["empNo"].toString()} 이름 : ${ obj["username"].toString()} \n출입 시간 : ${ obj["intime"].toString()}")
            }
            return list
        } else return list
    }
}
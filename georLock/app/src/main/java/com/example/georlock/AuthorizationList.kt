package com.example.georlock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authorization_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class AuthorizationList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_list)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        Thread(){
            var list:ArrayList<String> = UpdateMainLog()
            runOnUiThread{
                Log.i("testLog", "loginclick : ${list}")
                accesslist.adapter = ArrayAdapter<String>(
                    this,
//            android.R.layout.simple_list_item_1,
                    R.layout.layout_list,
                    list)

                accesslist.setOnItemClickListener { adapterView, view, position, l ->
//            Log.i("testLog", "$position, ${datas[position]}")
                    Log.i("testLogdd", "$position, ${list.get(position)}")
                    Toast.makeText(this,"${list.get(position)}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,Accessmodity::class.java)
                    intent.putExtra("info","${list.get(position)}")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent)
                }

            }
        }.start()

        search_access_button.setOnClickListener {
            Thread(){
                var list:ArrayList<String> = searchMainLog("${search_access.text.toString()}")
                runOnUiThread{
                    Log.i("testLog", "loginclick : ${list}")
                    accesslist.adapter = ArrayAdapter<String>(
                        this,
//            android.R.layout.simple_list_item_1,
                        R.layout.layout_list,
                        list)

                    accesslist.setOnItemClickListener { adapterView, view, position, l ->
//            Log.i("testLog", "$position, ${datas[position]}")
                        Log.i("testLogdd", "$position, ${list.get(position)}")
                        Toast.makeText(this,"${list.get(position)}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,Accessmodity::class.java)
                        intent.putExtra("info","${list.get(position)}")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        startActivity(intent)
                    }

                }
            }.start()
        }


    }

  /*  <th>사번</th>
    <th>이름</th>
    <th>출입가능시간</th>
    <th>수정/삭제</th>*/

    fun searchMainLog(search:String):ArrayList<String>{

        var se=  URLEncoder.encode(search, "UTF-8");
        val url = URL("http://192.168.0.88:8090/accessSearch?search=${se}")
        val conn = url.openConnection() as HttpURLConnection // casting
        Log.i("testLog", "conn.responseCode : ${conn.responseCode}")
        Log.i("testLog", "search : ${se}")
        var list:ArrayList<String> = arrayListOf()
        if(conn.responseCode == 200){
            var txt = url.readText()
            var arr: JSONArray = JSONArray(txt)
            for(i in 0 until arr.length()){
                var obj: JSONObject = arr.get(i) as JSONObject
                list.add("사번 : ${ obj["empNo"].toString()} \n이름 : ${ obj["username"].toString()} \n출입 시간 : ${ obj["intime"].toString()} ~ ${ obj["outtime"].toString()}")
            }
            return list
        } else return list
    }

    fun UpdateMainLog():ArrayList<String>{
        val url = URL("http://192.168.0.88:8090/accesslist")
        val conn = url.openConnection() as HttpURLConnection // casting
        Log.i("testLog", "conn.responseCode : ${conn.responseCode}")
        var list:ArrayList<String> = arrayListOf()
        if(conn.responseCode == 200){
            var txt = url.readText()
            var arr: JSONArray = JSONArray(txt)
            for(i in 0 until arr.length()){
                var obj: JSONObject = arr.get(i) as JSONObject
                list.add("사번 : ${ obj["empNo"].toString()} \n이름 : ${ obj["username"].toString()} \n출입 시간 : ${ obj["intime"].toString()} ~ ${ obj["outtime"].toString()}")
            }
            return list
        } else return list
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
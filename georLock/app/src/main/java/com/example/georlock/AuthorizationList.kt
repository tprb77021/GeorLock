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
import java.net.URL
import java.net.URLEncoder

class AuthorizationList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization_list)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        // 서버에서 리스트를 받아서 리스트뷰로 보여주는 부분
        Thread() {
            var list: ArrayList<String> = getAccessList()
            runOnUiThread {
                Log.i("testLog", "loginclick : ${list}")
                accesslist.adapter = ArrayAdapter<String>(this, R.layout.layout_list, list)
                // 정보 클릭시 회원정보 수정 페이지로 전환
                accesslist.setOnItemClickListener { adapterView, view, position, l ->
                    Toast.makeText(this, "${list.get(position)}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Accessmodify::class.java)
                    intent.putExtra("info", "${list.get(position)}")
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    startActivity(intent)
                }
            }
        }.start()

        // 검색버튼
        search_access_button.setOnClickListener {
            Thread() {
                var list: ArrayList<String> = onGetSearchAccess("${search_access.text.toString()}")
                runOnUiThread {
                    Log.i("testLog", "loginclick : ${list}")
                    accesslist.adapter = ArrayAdapter<String>(
                        this, R.layout.layout_list, list
                    )
                    // 버튼 클릭시 입력된 키워드로 검색된 정보 출력
                    accesslist.setOnItemClickListener { adapterView, view, position, l ->
                        Toast.makeText(this, "${list.get(position)}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Accessmodify::class.java)
                        intent.putExtra("info", "${list.get(position)}")
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        startActivity(intent)
                    }
                }
            }.start()
        }
    }

    // 출입권한 검색 요청
    fun onGetSearchAccess(search: String): ArrayList<String> {
        var se = URLEncoder.encode(search, "UTF-8");
        val url = URL("${Static.server_url}/accessSearch?search=${se}")
        Log.i("testLog", "search : ${se}")
        var list: ArrayList<String> = arrayListOf()
        var txt = url.readText()
        var arr: JSONArray = JSONArray(txt)
        for (i in 0 until arr.length()) {
            var obj: JSONObject = arr.get(i) as JSONObject
            list.add(
                "사번 : ${obj["empNo"]} " +
                        "\n이름 : ${obj["username"]} " +
                        "\n출입 시간 \n${obj["intime"]} ~ ${obj["outtime"]}"
            )
        }
        return list
    }

    // 출입권한 리스트 요청
    fun getAccessList(): ArrayList<String> {
        val url = URL("${Static.server_url}/accesslist")
        var list: ArrayList<String> = arrayListOf()
        var txt = url.readText()
        var arr: JSONArray = JSONArray(txt)
        for (i in 0 until arr.length()) {
            var obj: JSONObject = arr.get(i) as JSONObject
            list.add(
                "사번 : ${obj["empNo"]} " +
                        "\n이름 : ${obj["username"]} " +
                        "\n출입 시간 \n${obj["intime"]} ~ ${obj["outtime"]}"
            )
        }
        return list
    }

    //toolbar의 back키 눌렀을 때 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
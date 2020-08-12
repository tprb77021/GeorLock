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
    lateinit var Butts: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        // 버튼 클릭시 문 개폐 여부에 따른 버튼 색상 변경
        // 닫힌 상태 - Red, 클릭 후 문 개방시 Green으로 변경
        val DOOR_CLOSE = "0" //문이 닫힌 상태
        Thread() {
            var doors: String = getDoor()
            runOnUiThread {
                if (doors.equals(DOOR_CLOSE)) {
                    open.setBackgroundColor(Color.RED)
                } else {
                    open.setBackgroundColor(Color.GREEN)
                }
            }
        }.start()


        // 버튼 클릭시 출입기록 페이지로 전환
        button2.setOnClickListener {
            val intent = Intent(this, Function_openList::class.java)
            startActivity(intent)
        }

        // 버튼 클릭시 출입권한 페이지로 전환
        button3.setOnClickListener {
            val intent = Intent(this, AuthorizationList::class.java)
            startActivity(intent)
        }

        // 버튼 클릭시 로그인 상태 종료 후 로그인 페이지로 전환
        button4.setOnClickListener {
            MyApplication.prefs.delete("empNo")
            MyApplication.prefs.delete("userPw")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        // 문이 열렸을 때 TOAST로 알림 띄워주는 것
        open.setOnClickListener {
            Toast.makeText(this, "문이 열렸습니다.", Toast.LENGTH_SHORT).show()
            Thread() {
                var temp = ""
                if (intent.hasExtra("infos")) {
                    temp = intent.getStringExtra("infos").toString()
                }
                var list: String = setOpen("${temp}")
                Log.i("testlog", list)
            }.start()
        }

    }

    // 출입문 개폐
    fun setOpen(empNo: String): String {
        val url = URL("${Static.server_url}/open?empNo=${empNo}")
        val txt = url.readText()
        return "${txt}"

    }

    // 문 현재 상태 확인
    fun getDoor(): String {
        val url = URL("${Static.server_url}/door")
        val txt = url.readText()
        return "${txt}"

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

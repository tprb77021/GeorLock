package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
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

        //버튼 클릭시 사용자 비밀번호 수정 페이지로 전환
        edit_user.setOnClickListener {
            val intent = Intent(this, UserEdit::class.java)
            Log.i("testLog", "${tmp}")
            intent.putExtra("infoss", tmp)
            startActivity(intent)
        }
        //버튼 클릭시 사용자 로그인 상태 종료
        logout.setOnClickListener {
            MyApplication.prefs.delete("empNo")
            MyApplication.prefs.delete("userPw")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        //버튼 클릭시 관리자에게 개폐요청 알림 전송
        opencall.setOnClickListener {
            Toast.makeText(this, "문이 열림 요청함.", Toast.LENGTH_SHORT).show()
            Thread() {
                val infos:List<String>? =
                    tmp?.split("@")
                var list: String = opencall( "${infos?.get(2)}")
                Log.i("testLog", "opencall : ${infos?.get(2)} ")
                runOnUiThread {
                }
            }.start()
        }
    }

    //사용자의 개폐요청 알림
    fun opencall(empNo:String):String{
        val url = URL("${Static.server_url}/opencall?empNo=${empNo}")
        val txt = url.readText()
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
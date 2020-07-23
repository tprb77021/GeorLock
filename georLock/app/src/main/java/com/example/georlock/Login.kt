package com.example.georlock

import android.content.Context
import android.content.Intent
import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {

            var tmp:String = ""
            Log.i("testLog", "loginclick u:${username.text}p:${password.text}")
            Thread(){
                 tmp = UpdateMainLog("${username.text}","${password.text}")
                var tmps:List<String> = tmp.split("@");
                runOnUiThread{
                    Log.i("testLog", "loginclick : ${tmp}")
                    if("${tmp}".equals("0실패")){
                        Log.i("testLog", "로그인실패")
                        Toast.makeText(this, "로그인 실패.", Toast.LENGTH_SHORT).show()
                    }
                    else if("${tmp}".equals("2성공")){
                        Log.i("testLog", "로그인성공")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else if("${tmps[0]}".equals("1성공")){
                        Log.i("testLog", "로그인성공1 +${tmps}")
                        val intent = Intent(this, UserMain::class.java)
                        intent.putExtra("infos","${tmps[1]}@${tmps[2]}@${tmps[3]}")
                        startActivity(intent)
                    }
                }
            }.start()


            
        }
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

}

// \( *^_^*)/ 진주님 화이팅!!
// ('')( :)(..)(: ) 데굴데굴
//enable을 쓰지말자 !!
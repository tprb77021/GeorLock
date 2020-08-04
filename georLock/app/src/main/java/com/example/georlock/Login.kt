package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.georlock.Static.Companion.server_url
import kotlinx.android.synthetic.main.activity_login.*
import java.net.HttpURLConnection
import java.net.URL

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var empNo=MyApplication.prefs.getString("empNo", "no")
        var userPw=MyApplication.prefs.getString("userPw", "no")
        var temp:String=""
        if(intent.hasExtra("token")){
           temp = intent.getStringExtra("token").toString()
        }
        if(empNo != "no" && userPw != "no" ){
            loginmethod(empNo,userPw,"${temp}")
        }

        login_button.setOnClickListener {

            loginmethod("${username.text}","${password.text}","${temp}")
        }
    }


    fun loginmethod(empNo:String,userPw:String,tokens:String){
        Log.i("testLog", "checkBox u:${checkBox.isChecked}")
        var tmp:String = ""
        Log.i("testLog", "loginclick u:${username.text}p:${password.text}")

        Thread(){
            tmp = UpdateMainLog(empNo,userPw,tokens)
            var tmps:List<String> = tmp.split("@");
            runOnUiThread{
                Log.i("testLog", "loginclick : ${tmp}")
                if("${tmps[0]}".equals("0실패")){
                    Log.i("testLog", "로그인실패")
                    Toast.makeText(this, "로그인 실패.", Toast.LENGTH_SHORT).show()
                }
                else if("${tmps[0]}".equals("2성공")){
                    Log.i("testLog", "로그인성공")
                    val intent = Intent(this, MainActivity::class.java)
                    if(checkBox.isChecked){
                        // 데이터 저장
                        MyApplication.prefs.setString("empNo", "${username.text}")
                        MyApplication.prefs.setString("userPw", "${password.text}")

                    }
                    intent.putExtra("infos","${tmps[3]}")
                    startActivity(intent)
                }
                else if("${tmps[0]}".equals("1성공")){
                    Log.i("testLog", "로그인성공1 +${tmps}")
                    if(checkBox.isChecked){
                        // 데이터 저장
                        MyApplication.prefs.setString("empNo", "${username.text}")
                        MyApplication.prefs.setString("userPw", "${password.text}")
                    }
                    val intent = Intent(this, UserMain::class.java)
                    intent.putExtra("infos","${tmps[1]}@${tmps[2]}@${tmps[3]}")
                    startActivity(intent)
                }
            }
        }.start()


    }

    fun UpdateMainLog(empNo:String,userPw:String,tokens:String):String{
        val url = URL("${server_url}/login?empNo=${empNo}&userPw=${userPw}&tokens=${tokens}")

            val txt = url.readText()
            /*val arr = JSONArray(txt)
            var item = arr*/
            return "${txt}"

    }

}


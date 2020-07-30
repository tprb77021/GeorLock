package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class Loading : AppCompatActivity() {
    internal val app_serverurl = "http://192.168.0.88:8091"
   var token = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loading)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.i("testLog", "getInstanceId failed")
                return@OnCompleteListener
            }
            Log.i("testLog", "getInstanceId")
             token = task.result?.token!!
            Log.i("testLog", "token : $token")
        })

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, Login::class.java)
            intent.putExtra("token","$token")
            startActivity(intent)
            finish()
        }, 3000)

    }
}
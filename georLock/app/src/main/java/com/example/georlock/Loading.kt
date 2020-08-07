package com.example.georlock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class Loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = Intent(this, Login::class.java)
        setContentView(R.layout.activity_loading)

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if(!task.isSuccessful){
                Log.i("testLog", "getInstanceId failed")
                return@OnCompleteListener
            }
            Log.i("testLog", "getInstanceId")
            var token = task.result?.token!!
            intent.putExtra("token","$token")
            Log.i("testLog", "tokenssss : $token")
        })
        val handler = Handler()
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }
}
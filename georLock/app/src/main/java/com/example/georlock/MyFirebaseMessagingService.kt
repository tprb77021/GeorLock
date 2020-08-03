package com.example.georlock


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.android.synthetic.main.activity_login.*


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("testLog", "refreshed token: $token")
//        Toast.makeText(this, "$token", Toast.LENGTH_SHORT).show()
    }

    override fun onMessageReceived(remoteMessage : RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.i("testLog", "message received")
            Log.i("testLog", "${remoteMessage.messageType}")
            if(remoteMessage.data.size > 0){
                Log.i("testLog", "msg data : ${remoteMessage.data}")

            if(true){
            } else{
                handleNow();
            }

            if(remoteMessage.notification != null){
                Log.i("testLog", "msg notificationn Title : ${remoteMessage.notification!!.title}")
                Log.i("testLog", "msg notificationn body : ${remoteMessage.notification!!.body}")
            }
        } else{
                if("${remoteMessage.notification!!.title}".equals("Georlokc!")){
                 Log.i("testLog", "size?")
                Log.i("testLog", "msg notificationn Title : ${remoteMessage.notification!!.title}")
                Log.i("testLog", "msg notificationn body : ${remoteMessage.notification!!.body}")
                sendNotification(" ${remoteMessage.notification!!.title}","${remoteMessage.notification!!.body}")
                }else if("${remoteMessage.notification!!.title}".equals("door")){
                    var tmps:List<String> =  "${remoteMessage.notification!!.body}".split("@");
                    Log.i("testLog", "kl${remoteMessage.notification!!.body}")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("infos","${tmps[1]}")
                        startActivity(intent)
                }
        }

    }
    private fun sendNotification(title: String?, body: String){
        //어떤 모양으로 알림을 할지 설정한 다음 실제 폰 상단에 표시하도록 한다.
        //pendingIntent를 이용 알림을 클릭하면 열 앱의 액티비티를 설정해 준다.
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(title)
            .setContentText(body)
            .setSound(defaultSound)
            .setColor(Color.parseColor("#2794F4"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun handleNow() {
        Log.i("testLog", "short lived task is done.")
    }
}
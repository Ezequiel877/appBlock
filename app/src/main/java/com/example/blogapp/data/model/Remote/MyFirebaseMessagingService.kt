package com.example.blogapp.data.model.Remote

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.edit
import com.google.firebase.messaging.FirebaseMessagingService
import com.example.blogapp.MainActivity
import com.example.blogapp.R
import com.example.blogapp.data.model.constantes
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        usuarioId(newToken)
    }
    private fun usuarioId(newToken:String){
        val preferce= PreferenceManager.getDefaultSharedPreferences(this)
        preferce.edit {
            putString(constantes.TOKEN_ID, newToken)
                .apply()
        }

        Log.d("new token", newToken)

    }
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        p0.notification?.let {
            sendNotification(it)
        }
    }
    private fun sendNotification(remoteMessage: RemoteMessage.Notification){
        val intent= Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pedingIntent= PendingIntent.getActivity(this, 0 , intent, PendingIntent.FLAG_ONE_SHOT)
        val chanelid=getString(R.string.default_notification_channel_id)
        val defulRington= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder= NotificationCompat.Builder(this,chanelid)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(remoteMessage.title)
            .setContentText(remoteMessage.body)
            .setAutoCancel(true)
            .setSound(defulRington)
            .setContentIntent(pedingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(remoteMessage.title))
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel= NotificationChannel(chanelid, "Generales", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }
}
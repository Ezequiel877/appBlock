package com.example.blogapp.data.model.notificaciones

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class UsesProvider {
    fun createToken(id: String) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "TAGFIREBASEMEESAGIN",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.w("TAGFIREBASEMEESAGIN", "Fetching FCM registration token failed $token")
        })
    }
}
package com.example.ispravno

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessageReceived : FirebaseMessagingService () {

    override fun onMessageReceived(p0: RemoteMessage) {
        if (p0?.data != null) {
            Log.d(TAG," Data : " + p0.data.toString())
        }
        if(p0.notification != null) {
            Log.d(TAG,"Notification : " + p0.notification.toString())
        }
    }
}
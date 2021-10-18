package com.example.ispravno

import android.util.Log
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG,"Refreshed token: $token")

        //sendRegistrationToServer(token)
    }
}
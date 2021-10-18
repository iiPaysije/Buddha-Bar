package com.example.ispravno

import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
const val TAG = "MyActivity"

class CustomFirebase : FirebaseMessagingService()  {


   public fun onTokenRefresh(token: String?) {
        // Get updated InstanceID token.
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)

       FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
           if (!task.isSuccessful) {
               Log.w(TAG, "Fetching FCM registration token failed", task.exception)
               return@OnCompleteListener
           }

           // Get new FCM registration token
           val token2 = task.result

           Log.e("newToken", token2.toString());
           

       })

    }
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
    }




}

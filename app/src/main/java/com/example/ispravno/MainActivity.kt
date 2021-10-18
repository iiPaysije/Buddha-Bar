package com.example.ispravno


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Window
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity(){





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.d(TAG,"TOKEN token: $token")
//             Log and toast
//            val msg = getString(R.tring.msg_token_fmt, token)
//            Log.d(TAG, msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        //klik na NavBar
        val bottomBar : BottomNavigationView = findViewById(R.id.mybottombar)

        val myWebView: WebView = findViewById(R.id.ViewBrowserID)
        val webSettings = myWebView.settings
        //webSettings.setUserAgentString("XY Android App" );
        webSettings.javaScriptEnabled = true
        //myWebView.loadUrl("https://www.kosovo-online.com/")
        myWebView.loadUrl("https://buddhabarbeograd.rs/sr/")
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return false
            }
        }

        val gmmIntentUri = Uri.parse("geo:0,0?q=1600 Buddha-Bar Beograd  Херцеговачка, Београд")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

        val bottomBarlistener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    myWebView.loadUrl("https://buddhabarbeograd.rs/sr/")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.jelovnik -> {
                    myWebView.loadUrl("https://buddhabarbeograd.rs/sr/jelovnik")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.rezervacija -> {
                    myWebView.loadUrl("https://buddhabarbeograd.rs/sr/rezervacije/")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.mapa -> {

                        startActivity(mapIntent)

                }

            }
            false
        }

        bottomBar.setOnNavigationItemSelectedListener(bottomBarlistener)

        myWebView.setOnKeyListener { v, keyCode, event ->

            when {

                //Check if it is the Enter-Key,      Check if the Enter Key was pressed down
                ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack() &&(event.action == KeyEvent.ACTION_DOWN)) -> {


                   myWebView.goBack()

                    //return true
                    return@setOnKeyListener true
                }
                else -> false
            }


        }

     fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
            if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
                myWebView.goBack()

            }
            return super.onKeyDown(keyCode, event)
        }



    }



}

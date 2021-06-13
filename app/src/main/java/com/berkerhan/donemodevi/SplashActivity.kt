package com.berkerhan.donemodevi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.view.postDelayed
import com.berkerhan.donemodevi.utils.UiHelper
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (UiHelper.hasInternetConnection(this)) {
            loadSplashScreen()
        }
        else {
            Toast.makeText(this,"Lütfen internet bağlantınızı kontrol ediniz",Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun loadSplashScreen() {
        delayedImage()

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }

    private fun delayedImage() {
        splash_image.postDelayed({}, 1000)
    }
}
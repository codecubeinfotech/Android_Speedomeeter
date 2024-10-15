package com.vrushank.android.demo

import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.IOException

class MainActivity : AppCompatActivity() {

//    private lateinit var speedometerView: SpeedometerView
    private lateinit var speedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        speedometerView = findViewById(R.id.speedometerView)
        speedTextView = findViewById(R.id.speedTextView)

//        speedometerView.setSpeedTextView(speedTextView)
//        speedometerView.startUpdatingSpeed()
    }

    override fun onDestroy() {
        super.onDestroy()
//        speedometerView.stopUpdatingSpeed()
    }

}
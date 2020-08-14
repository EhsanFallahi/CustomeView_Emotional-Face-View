package com.example.emotionalfacecustomeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1
        happyButton.setOnClickListener {
            emotional_face_view.happinessState=EmotionalFaceView.HAPPY
        }
        // 2
        sadButton.setOnClickListener {
            emotional_face_view.happinessState = EmotionalFaceView.SAD
        }

    }
}
package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ScoreViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_viewer)

        val correctCount = findViewById<TextView>(R.id.correctCount)
        val wrongCount = findViewById<TextView>(R.id.wrongCount)

        val correct = intent.getStringExtra("correct")
        val wrong = intent.getStringExtra("wrong")

        correctCount.text = correct
        correctCount.setTextColor(Color.GREEN)
        wrongCount.text = wrong
        wrongCount.setTextColor(Color.RED)
    }
}
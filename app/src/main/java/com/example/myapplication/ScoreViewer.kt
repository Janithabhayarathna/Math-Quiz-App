package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ScoreViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_viewer)

        val correctCount = findViewById<TextView>(R.id.correctCount)
        val wrongCount = findViewById<TextView>(R.id.wrongCount)

        //Getting the data from the previous window
        val correct = intent.getStringExtra("correct")
        val wrong = intent.getStringExtra("wrong")

        correctCount.text = correct
        correctCount.setTextColor(Color.GREEN)
        wrongCount.text = wrong
        wrongCount.setTextColor(Color.RED)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val back = Intent(this , MainActivity::class.java)
        startActivity(back)
        //When the user clicks the back button program goes to the main window
    }
}
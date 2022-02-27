package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val expression01 = findViewById<TextView>(R.id.exp1)
        val expression02 = findViewById<TextView>(R.id.exp2)

        val grtBtn = findViewById<Button>(R.id.greater)
        val eqlBtn = findViewById<Button>(R.id.equal)
        val lesBtn = findViewById<Button>(R.id.less)
    }
}
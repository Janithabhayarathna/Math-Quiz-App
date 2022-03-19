package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

/**
 * Name: Janith Chanaka Abhayarathna.
 * Student ID: w1830253 / 20200571
 * Mobile Application Development Course Work 01.
 */

class MainActivity : AppCompatActivity() {

    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing the buttons
        val aboutBtn = findViewById<Button>(R.id.abtBtn)
        val nextGameBtn = findViewById<Button>(R.id.gameBtn)

        //Setting up the on click action listener for the about button
        var selected = false
        aboutBtn.setOnClickListener {

            if (!selected) {
                //Setting the popup window

                selected = true
                val popupFrame = PopupWindow(this)
                popupFrame.isFocusable
                val view = layoutInflater.inflate(R.layout.popup, null)
                popupFrame.contentView = view
                popupFrame.showAtLocation(view, Gravity.CENTER, 0, 0)

                view.setOnClickListener {
                    popupFrame.dismiss()
                    selected = false
                }

            } else {
                Toast.makeText(applicationContext, "Already in the about window.", Toast.LENGTH_SHORT).show()
            }
        }

        //Setting up the on click action listener for the next button
        nextGameBtn.setOnClickListener {
            val newGamer = Intent(this, Game::class.java)
            startActivity(newGamer)
        }
    }
}
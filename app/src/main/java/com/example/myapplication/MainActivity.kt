package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView

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
                val popup = PopupWindow(this)
                val view = layoutInflater.inflate(R.layout.popup, null)
                popup.contentView = view

                val dp = view.findViewById<ImageView>(R.id.creator)
                val text = view.findViewById<TextView>(R.id.para)

                dp.setImageResource(
                    resources.getIdentifier(
                        "dp",
                        "drawable",
                        "com.example.myApplication"
                    )
                )
                text.text =
                    "Author; \nStudent id: w1820253 / 20200571 \nName: J C Abhayarathna \n\n\t I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the EssentialInformation for Students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged."
                selected = true
                text.setOnClickListener {
                    popup.dismiss()
                    selected = false
                }
                popup.showAtLocation(view, Gravity.CENTER, 0, 0)
            }
        }

        //Setting up the on click action listener for the next button
        nextGameBtn.setOnClickListener {
            val newGamer = Intent(this, Game::class.java)
            startActivity(newGamer)
        }
    }
}
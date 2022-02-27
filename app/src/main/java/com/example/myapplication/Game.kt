package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class Game : AppCompatActivity() {

    val gen = Random()
    var expression1 = " "

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val expression01 = findViewById<TextView>(R.id.exp1)
        val expression02 = findViewById<TextView>(R.id.exp2)

        val grtBtn = findViewById<Button>(R.id.greater)
        val eqlBtn = findViewById<Button>(R.id.equal)
        val lesBtn = findViewById<Button>(R.id.less)

        var firstNumber = 1 + gen.nextInt(20)
        var numOfTerms = 1 + gen.nextInt(4)

        generateExpression1(firstNumber.toString(), numOfTerms, expression01)

    }

    fun generateExpression1(firstNumber: String, numOfTerms: Int, expression01: TextView) {

        var numOfTerms = numOfTerms
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = gen.nextInt(4)

        val secondNumber = 1+ gen.nextInt(20)

        expression1 = firstNumber + operators[operatorIndex] + secondNumber
        numOfTerms--

        if (numOfTerms > 0) {
            generateExpression1("("+expression1+")",numOfTerms, expression01)
        } else {
            expression01.text = expression1
        }
    }
}
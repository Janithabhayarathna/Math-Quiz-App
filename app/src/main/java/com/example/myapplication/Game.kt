package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class Game : AppCompatActivity() {

    var expression1 = "Expression 01"
    var expression2 = "Expression 02"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val expression01 = findViewById<TextView>(R.id.exp1)
        val expression02 = findViewById<TextView>(R.id.exp2)

        val grtBtn = findViewById<Button>(R.id.greater)
        val eqlBtn = findViewById<Button>(R.id.equal)
        val lesBtn = findViewById<Button>(R.id.less)

        var firstNumber1 = 1 + Random().nextInt(20)
        var firstNumber2 = 1 + Random().nextInt(20)

        var numOfTerms1 = 1 + Random().nextInt(4)
        var numOfTerms2 = 1 + Random().nextInt(4)

        generateExpression1(firstNumber1.toString(), numOfTerms1, expression01)
        generateExpression2(firstNumber2.toString(), numOfTerms2, expression02)

    }

    fun generateExpression1(firstNumber: String, numOfTerms1: Int, expression01: TextView) {

        var numOfTerms = numOfTerms1
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        val secondNumber = 1+ Random().nextInt(20)

        expression1 = firstNumber + operators[operatorIndex] + secondNumber
        numOfTerms--

        if (numOfTerms > 0) {
            generateExpression1("("+expression1+")",numOfTerms, expression01)
        } else {
            expression01.text = expression1
        }
    }

    fun generateExpression2(firstNumber2: String, numOfTerms2: Int, expression02: TextView) {

        var numOfTerms = numOfTerms2
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        val secondNumber = 1+ Random().nextInt(20)

        expression2 = firstNumber2 + operators[operatorIndex] + secondNumber
        numOfTerms--

        if (numOfTerms > 0) {
            generateExpression2("("+expression2+")",numOfTerms, expression02)
        } else {
            expression02.text = expression2
        }
    }
}
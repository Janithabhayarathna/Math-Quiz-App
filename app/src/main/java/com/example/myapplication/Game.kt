package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.concurrent.schedule


class Game : AppCompatActivity() {

    var expression1 = "Expression 01"
    var expression2 = "Expression 02"

    var value1 = 0
    var value2 = 0

    @SuppressLint("SetTextI18n")
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

        val correctStatus = findViewById<TextView>(R.id.correctness)

        generateExpression1(firstNumber1.toString(), numOfTerms1, expression01,0)
        generateExpression2(firstNumber2.toString(), numOfTerms2, expression02,0)

        grtBtn.setOnClickListener {

            if(value1 > value2) {
                correctStatus.text = "CORRECT!"
                correctStatus.setTextColor(Color.parseColor("green"))

//                Timer().schedule(5000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, 0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }
               
            } else {
                correctStatus.text = "WRONG!"
                correctStatus.setTextColor(Color.parseColor("red"))

//                Timer().schedule(5000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, 0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }
            }
        }

        eqlBtn.setOnClickListener {
            if(value1 == value2) {
                correctStatus.text = "CORRECT!"
                correctStatus.setTextColor(Color.parseColor("green"))

//                Timer().schedule(2000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, 0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }

            } else {
                correctStatus.text = "WRONG!"
                correctStatus.setTextColor(Color.parseColor("red"))
                //need to put a delay
//                Timer().schedule(2000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, 0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }
            }
        }

        lesBtn.setOnClickListener {
            if(value1 < value2) {
                correctStatus.text = "CORRECT!"
                correctStatus.setTextColor(Color.parseColor("green"))

//                Timer().schedule(2000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, 0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }

            } else {
                correctStatus.text = "WRONG!"
                correctStatus.setTextColor(Color.parseColor("red"))

//                Timer().schedule(2000) {
                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01,0)
                    generateExpression2(number2.toString(), terms2, expression02, 0)
//                }
            }
        }

    }

    fun generateExpression1(firstNumber1: String, numOfTerms1: Int, expression01: TextView, tot: Int) {

        var total = tot
        var numOfTerms = numOfTerms1
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        val secondNumber = 1+ Random().nextInt(20)

        expression1 = firstNumber1 + operators[operatorIndex] + secondNumber

        when(operatorIndex) {
            0 -> total += total + secondNumber
            1 -> total += total - secondNumber
            2 -> total += total * secondNumber
            else -> {total += total / secondNumber}
        }

        numOfTerms--

        if (numOfTerms > 0) {

            generateExpression1("("+expression1+")",numOfTerms, expression01,total)
        } else {
            value1 = total
            expression01.text = expression1
        }
    }

    fun generateExpression2(firstNumber2: String, numOfTerms2: Int, expression02: TextView, tot: Int) {

        var total = tot
        var numOfTerms = numOfTerms2
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        val secondNumber = 1+ Random().nextInt(20)

        expression2 = firstNumber2 + operators[operatorIndex] + secondNumber

        when(operatorIndex) {
            0 -> total = total + secondNumber
            1 -> total = total - secondNumber
            2 -> total = total * secondNumber
            else -> {total = total / secondNumber}
        }

        numOfTerms--

        if (numOfTerms > 0) {
            generateExpression2("("+expression2+")",numOfTerms, expression02, total)
        } else {
            value2 = total
            expression02.text = expression2
        }
    }

    fun firstNoGenarator(): Int {
        val number1 = 1 + Random().nextInt(20)
        return number1
    }

    fun noOfTermsGenerator(): Int {
        val terms = 1 + Random().nextInt(4)
        return terms
    }
}
package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.os.CountDownTimer
import android.widget.Toast
import java.util.Timer
import kotlin.concurrent.schedule

class Game : AppCompatActivity() {

    var expression1 = "Expression 01"
    var expression2 = "Expression 02"

    var value1 = 0
    var value2 = 0

    var noOfCorrectAnswers = 0
    var noOfWrongAnswers = 0

    var correctValue = 0

    var counter = 50
    var finish = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        startTimer()

        val expression01 = findViewById<TextView>(R.id.exp1)
        val expression02 = findViewById<TextView>(R.id.exp2)

        val grtBtn = findViewById<Button>(R.id.greater)
        val eqlBtn = findViewById<Button>(R.id.equal)
        val lesBtn = findViewById<Button>(R.id.less)

        var firstNumber1 = 1 + Random().nextInt(20)
        var firstNumber2 = 1 + Random().nextInt(20)

        var numOfTerms1 = 1 + Random().nextInt(3)
        var numOfTerms2 = 1 + Random().nextInt(3)

        val correctStatus = findViewById<TextView>(R.id.correctness)

        generateExpression1(firstNumber1.toString(), numOfTerms1, expression01, firstNumber1)
        generateExpression2(firstNumber2.toString(), numOfTerms2, expression02,firstNumber2)

        grtBtn.setOnClickListener {

            if (!finish) {
                if (value1 > value2) {
                    correctStatus.text = "CORRECT!"
                    correctStatus.setTextColor(Color.parseColor("green"))

                    noOfCorrectAnswers++
                    correctValue++

                    if (correctValue == 5) {
                        Toast.makeText(applicationContext, "10 seconds added.", Toast.LENGTH_LONG).show()
                        counter += 10
                        correctValue -= 5
                    }

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)

                } else {
                    correctStatus.text = "WRONG!"
                    noOfWrongAnswers++
                    correctStatus.setTextColor(Color.parseColor("red"))

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)
                }
            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }
        }

        eqlBtn.setOnClickListener {

            if (!finish) {
                if (value1 == value2) {
                    correctStatus.text = "CORRECT!"
                    correctStatus.setTextColor(Color.parseColor("green"))
                    noOfCorrectAnswers++
                    correctValue++

                    if (correctValue == 5) {
                        Toast.makeText(applicationContext, "10 seconds added.", Toast.LENGTH_LONG).show()
                        counter += 10
                        correctValue -= 5
                    }

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)

                } else {
                    correctStatus.text = "WRONG!"
                    noOfWrongAnswers++
                    correctStatus.setTextColor(Color.parseColor("red"))

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)

                }
            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }
        }

        lesBtn.setOnClickListener {

            if (!finish) {
                if (value1 < value2) {
                    correctStatus.text = "CORRECT!"
                    correctStatus.setTextColor(Color.parseColor("green"))
                    noOfCorrectAnswers++
                    correctValue++

                    if (correctValue == 5) {
                        Toast.makeText(applicationContext, "10 seconds added.", Toast.LENGTH_LONG).show()
                        counter += 10
                        correctValue -= 5
                    }

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)

                } else {
                    correctStatus.text = "WRONG!"
                    noOfWrongAnswers++
                    correctStatus.setTextColor(Color.parseColor("red"))

                    val number1 = firstNoGenarator()
                    val number2 = firstNoGenarator()
                    val terms1 = noOfTermsGenerator()
                    val terms2 = noOfTermsGenerator()
                    generateExpression1(number1.toString(), terms1, expression01, number1)
                    generateExpression2(number2.toString(), terms2, expression02, number2)
                }

            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }
        }

    }

    fun startTimer() {
        //Referenced from: https://www.tutorialspoint.com/how-to-set-a-timer-in-android-using-kotlin

        val timer = findViewById<TextView>(R.id.timer)
        object : CountDownTimer(500000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = counter.toString()
                if (counter == 0) {
                    cancel()
                    onFinish()
                }
                timer.setTextColor(Color.BLUE)
                counter--
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timer.text = "Time Over!"
                timer.setTextColor(Color.RED)
                finish = true

                Timer("Delay", false).schedule(2000) {
                    val score = Intent(this@Game, ScoreViewer::class.java)
                    score.putExtra("correct", "Number of correct answers - $noOfCorrectAnswers")
                    score.putExtra("wrong", "Number of wrong answers - $noOfWrongAnswers")
                    startActivity(score)
                }
            }
        }.start()
    }

    fun generateExpression1(firstNumber1: String, numOfTerms1: Int, expression01: TextView, tot: Int) {

        var total = tot
        var numOfTerms = numOfTerms1

        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        var secondNumber = 0
        var flag = true
        while (flag) {
            secondNumber = 1 + Random().nextInt(20)

            when (operatorIndex) {
                0 -> {
                    total += secondNumber
                    flag = false
                }
                1 -> {
                    total -= secondNumber
                    flag = false
                }
                2 -> {
                    total *= secondNumber
                    flag = false
                }
                else -> {
                    if (total % secondNumber == 0) {
                        flag = false
                        total /= secondNumber
                    } else {
                        flag = true
                        continue
                    }
                }
            }
            expression1 = firstNumber1 + operators[operatorIndex] + secondNumber
        }

        if (total > 100) {
            val number1 = firstNoGenarator()
            val terms1 = noOfTermsGenerator()
            generateExpression1(number1.toString(), terms1, expression01, number1)
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

        var secondNumber = 0
        var flag = true
        while (flag) {
            secondNumber = 1+ Random().nextInt(20)
            when (operatorIndex) {
                0 -> {
                    total += secondNumber
                    flag = false
                }
                1 -> {
                    total -= secondNumber
                    flag = false
                }
                2 -> {
                    total *= secondNumber
                    flag = false
                }
                else -> {
                    if (total % secondNumber == 0) {
                        total /= secondNumber
                        flag = false
                    } else {
                        flag = true
                        continue
                    }
                }
            }
            expression2 = firstNumber2 + operators[operatorIndex] + secondNumber
        }
        if (total > 100) {
            val number2 = firstNoGenarator()
            val terms2 = noOfTermsGenerator()
            generateExpression2(number2.toString(), terms2, expression02, number2)
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
        return 1 + Random().nextInt(20)
    }

    fun noOfTermsGenerator(): Int {
        return 1 + Random().nextInt(3)
    }
}
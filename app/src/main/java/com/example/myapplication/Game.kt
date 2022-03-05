package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.os.CountDownTimer
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import java.util.*
import java.util.Timer
import kotlin.concurrent.schedule

class Game : AppCompatActivity() {

    //Initializing the variables

    var expression1 = "Expression 01"
    var expression2 = "Expression 02"

    var value1 = 0
    var value2 = 0

    var noOfCorrectAnswers = 0
    var noOfWrongAnswers = 0

    var correctValue = 0

    var counter = 50
    var finish = false

    var questionNumber = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        startTimer()    //starting the timer

        //Initializing the text views
        val expression01 = findViewById<TextView>(R.id.exp1)
        val expression02 = findViewById<TextView>(R.id.exp2)
        val correctStatus = findViewById<TextView>(R.id.correctness)

        //Initializing the buttons
        val grtBtn = findViewById<Button>(R.id.greater)
        val eqlBtn = findViewById<Button>(R.id.equal)
        val lesBtn = findViewById<Button>(R.id.less)

        grtBtn.isEnabled = true
        eqlBtn.isEnabled = true
        lesBtn.isEnabled = true

        val timerImage = findViewById<ImageView>(R.id.timerImage)
        timerImage.setImageResource(resources.getIdentifier("timer", "drawable", "com.example.myapplication"))

        //Generating the required random numbers

        val firstNumber1 = 1 + Random().nextInt(20)
        val firstNumber2 = 1 + Random().nextInt(20)

        val numOfTerms1 = 1 + Random().nextInt(3)
        val numOfTerms2 = 1 + Random().nextInt(3)

        //Calling the methods to generate the two expressions
        generateExpression1(firstNumber1.toString(), numOfTerms1, expression01, firstNumber1)
        generateExpression2(firstNumber2.toString(), numOfTerms2, expression02,firstNumber2)

        //Setting the on click action listener to the 'greater' button
        grtBtn.setOnClickListener {

            questionNumber++
            eqlBtn.isEnabled = false
            lesBtn.isEnabled = false

            if (!finish) {  //Checking whether the time is over or not
                if (value1 > value2) {

                    ifCorrect(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                } else {
                    ifWrong(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                }

            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }

            fun enablingButtons() {

            }
        }

        //Setting the on click action listener to the '==' button
        eqlBtn.setOnClickListener {

            questionNumber++
            grtBtn.isEnabled = false
            lesBtn.isEnabled = false

            if (!finish) {  //Checking whether the time is over or not
                if (value1 == value2) {
                    ifCorrect(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                } else {
                    ifWrong(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                }
            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }
        }

        //Setting the on click action listener to the 'less' button
        lesBtn.setOnClickListener {

            questionNumber++
            grtBtn.isEnabled = false
            eqlBtn.isEnabled = false

            if (!finish) {  //Checking whether the time is over or not
                if (value1 < value2) {
                    ifCorrect(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                } else {
                    ifWrong(expression01, expression02, correctStatus)
                    Handler().postDelayed({
                        grtBtn.isEnabled = true
                        eqlBtn.isEnabled = true
                        lesBtn.isEnabled = true
                    }, 1500)
                }

            } else {
                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_LONG).show()
                correctStatus.text = " "
            }
        }

    }

    fun startTimer() {
        //Method used to setup the timer
        //Referenced from: https://www.tutorialspoint.com/how-to-set-a-timer-in-android-using-kotlin

        val timer = findViewById<TextView>(R.id.timer)

        object : CountDownTimer(500000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timer.text = counter.toString()
                timer.setTextColor(Color.DKGRAY)

                if (counter == 0) {
                    cancel()
                    onFinish()
                }

                if (counter <= 10) {
                    timer.setTextColor(Color.RED)
                }

                counter--
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                //After the timer reach to 0, this part will execute

                Toast.makeText(applicationContext, "Time over!", Toast.LENGTH_SHORT).show()

                timer.text = "0!"
                timer.setTextColor(Color.RED)
                finish = true

                Timer("Delay", false).schedule(2000) {

                    val score = Intent(this@Game, ScoreViewer::class.java)
                    //Passing the values to the score viewer window
                    score.putExtra("correct", "✔Number of correct answers - $noOfCorrectAnswers")
                    score.putExtra("wrong", "⛔Number of wrong answers - $noOfWrongAnswers")
                    startActivity(score)
                }
            }
        }.start()
    }

    fun generateExpression1(firstNumber1: String, numOfTerms1: Int, expression01: TextView, tot: Int) {
        //Method used to generate the expression 1

        var total = tot
        var numOfTerms = numOfTerms1

        val operators: List<String> = listOf("+", "-", "*", "/")    //List of operators
        val operatorIndex = Random().nextInt(operators.size)    //Generating a number to get the operator randomly

        var secondNumber: Int
        var flag = true

        while (flag) {
            //Used a while loop to generate the second term until it is a factor of first number
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
            expression1 = firstNumber1 + operators[operatorIndex] + secondNumber
        }

        if (total > 100) {
            //Used to check whether the each part of the expression less than or equal to 100
            val number1 = firstNoGenerator()
            val terms1 = noOfTermsGenerator()
            generateExpression1(number1.toString(), terms1, expression01, number1)
        }

        numOfTerms--

        if (numOfTerms > 0) {
            //Used a recursive until the number of terms become 0
            generateExpression1("($expression1)",numOfTerms, expression01, total)
        } else {
            value1 = total
            expression01.text = expression1
            expression01.setTextColor(Color.BLUE)
        }
    }

    fun generateExpression2(firstNumber2: String, numOfTerms2: Int, expression02: TextView, tot: Int) {
        //Method used to generate the expression 2

        var total = tot
        var numOfTerms = numOfTerms2
        val operators: List<String> = listOf("+", "-", "*", "/")
        val operatorIndex = Random().nextInt(4)

        var secondNumber: Int
        var flag = true
        while (flag) {
            //Used a while loop to generate the second term until it is a factor of first number
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
            //Used to check whether the each part of the expression less than or equal to 100
            val number2 = firstNoGenerator()
            val terms2 = noOfTermsGenerator()
            generateExpression2(number2.toString(), terms2, expression02, number2)
        }

        numOfTerms--

        if (numOfTerms > 0) {
            //Used a recursive until the number of terms become 0
            generateExpression2("($expression2)",numOfTerms, expression02, total)
        } else {
            value2 = total
            expression02.text = expression2
            expression02.setTextColor(Color.BLUE)
        }
    }

    private fun firstNoGenerator(): Int {
        return 1 + Random().nextInt(20)
    }

    private fun noOfTermsGenerator(): Int {
        return 1 + Random().nextInt(3)
    }

    @SuppressLint("SetTextI18n")
    fun ifCorrect(expression01: TextView, expression02: TextView, correctStatus: TextView) {

        correctStatus.text = "CORRECT!"
        correctStatus.setTextColor(Color.parseColor("green"))
        noOfCorrectAnswers++
        correctValue++

        if (correctValue == 5) {
            Toast.makeText(applicationContext, "10 seconds added.", Toast.LENGTH_LONG).show()
            counter += 10
            correctValue -= 5   //Resetting the value
        }

        Handler().postDelayed({
            correctStatus.text = " "
            Toast.makeText(applicationContext, "Qusetion $questionNumber", Toast.LENGTH_SHORT).show()
            // Generating the 2 expressions
            val number1 = firstNoGenerator()
            val number2 = firstNoGenerator()
            val terms1 = noOfTermsGenerator()
            val terms2 = noOfTermsGenerator()
            generateExpression1(number1.toString(), terms1, expression01, number1)
            generateExpression2(number2.toString(), terms2, expression02, number2)
        }, 1500)
    }

    @SuppressLint("SetTextI18n")
    fun ifWrong(expression01: TextView, expression02: TextView, correctStatus: TextView) {
        correctStatus.text = "WRONG!"
        noOfWrongAnswers++
        correctStatus.setTextColor(Color.parseColor("red"))

        Handler().postDelayed({
            correctStatus.text = " "

            Toast.makeText(applicationContext, "Qusetion $questionNumber", Toast.LENGTH_SHORT).show()
            // Generating the 2 expressions
            val number1 = firstNoGenerator()
            val number2 = firstNoGenerator()
            val terms1 = noOfTermsGenerator()
            val terms2 = noOfTermsGenerator()
            generateExpression1(number1.toString(), terms1, expression01, number1)
            generateExpression2(number2.toString(), terms2, expression02, number2)
        }, 1500)
    }
}
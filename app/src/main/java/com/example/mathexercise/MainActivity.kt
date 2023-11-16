package com.example.mathexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var quizActivity: QuizActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizActivity = QuizActivity()

        val question = quizActivity.createQuestion()

        val questionTextView: TextView = findViewById(R.id.questionView)
        questionTextView.text = question.question

        val optionButtons: List<Button> = listOf(
            findViewById(R.id.option_button1),
            findViewById(R.id.option_button2),
            findViewById(R.id.option_button3)
        )

        for (i in optionButtons.indices) {
            optionButtons[i].text = question.options[i].toString()
            optionButtons[i].setOnClickListener {
                val chosenOption = (it as Button).text.toString().toInt()
                if (chosenOption == question.correctAnswer) {
                    Toast.makeText(this, "Right answer! \uD83C\uDF89", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Wrong answer, loser \uD83D\uDCA5", Toast.LENGTH_LONG).show()

                }

            }
        }
    }
}
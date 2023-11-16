package com.example.mathexercise

import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {

    data class Question(
        val question: String,
        val options: List<Int>,
        val correctAnswer: Int
    )

    fun createQuestion(): Question {
        val num1: Int = Random.nextInt(1, 51)
        val num2: Int = Random.nextInt(1, 51)

        val operations: List<String> = listOf("+", "-", "*", "/")
        val operation: String = operations[Random.nextInt(operations.size)]

        val question: String = "$num1 $operation $num2 = ?"

        val correctAnswer: Int = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw IllegalArgumentException("Unknown operation: $operation")
        }

        var wrongAnswer1: Int
        do {
            wrongAnswer1 = correctAnswer + Random.nextInt(-3, 3)
        } while (wrongAnswer1 == correctAnswer || wrongAnswer1 <= 0)

        var wrongAnswer2: Int
        do {
            wrongAnswer2 = correctAnswer + Random.nextInt(-3, 3)
        } while (wrongAnswer2 == correctAnswer || wrongAnswer2 == wrongAnswer1 || wrongAnswer2 <= 0)

        fun generateWrongAnswer (correctAnswer: Int, otherWrongAnswers: List<Int>): Int {
            var wrongAnswer: Int
            do {
                wrongAnswer = correctAnswer + Random.nextInt(-3, 3)
            } while (wrongAnswer == correctAnswer || wrongAnswer <= 0 || otherWrongAnswers.contains(wrongAnswer))
            return wrongAnswer
        }

        val options: List<Int> = listOf(correctAnswer, wrongAnswer1, wrongAnswer2).shuffled()

        return Question(question, options, correctAnswer)
    }
}
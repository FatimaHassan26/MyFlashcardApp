package com.example.thatflashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    var currCardDisplayedIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
       val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)


        if(allFlashcards.size > 0) {
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }


        findViewById<TextView>(R.id.flashcard_question).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.INVISIBLE
        }
        Log.i("Fatima", "Question tab was clicked")

        findViewById<TextView>(R.id.flashcard_answer).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.VISIBLE
        }
        Log.i("Fatima", "Question tab was clicked")
        findViewById<View>(R.id.flashcard_option1).setOnClickListener {
            findViewById<View>(R.id.flashcard_option1).setBackgroundColor(
                getResources().getColor(
                    R.color.red,
                    null
                )
            )
        }
        findViewById<View>(R.id.flashcard_option2).setOnClickListener {
            findViewById<View>(R.id.flashcard_option2).setBackgroundColor(
                getResources().getColor(
                    R.color.red,
                    null
                )
            )
        }
        findViewById<View>(R.id.flashcard_option3).setOnClickListener {
            findViewById<View>(R.id.flashcard_option3).setBackgroundColor(
                getResources().getColor(
                    R.color.green,
                    null
                )
            )
        }





        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->


                val data: Intent? = result.data
                if (data != null) {
                    val questionString = data.getStringExtra("QUESTION_KEY")
                    val answerString = data.getStringExtra("ANSWER_KEY")

                    findViewById<TextView>(R.id.flashcard_question).text = questionString
                    findViewById<TextView>(R.id.flashcard_answer).text = answerString

                    Log.i("MainActivity", "question: $questionString")

                    Log.i("MainActivity", "answer: $answerString")

                    if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                        flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

                    }
                }
                else {
                    Log.i("MainActivity", "Returned null data from AddCardActivity")
                }

            }

        findViewById<View>(R.id.addButton).setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)

        }
        val nextButton = findViewById<ImageView>(R.id.flashcardnextbutton)
        nextButton.setOnClickListener{
            if (allFlashcards.isEmpty()) {
                return@setOnClickListener
            }
            currCardDisplayedIndex++

            if(currCardDisplayedIndex >= allFlashcards.size) {
                currCardDisplayedIndex = 0
            }
            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currCardDisplayedIndex].question
            val answer = allFlashcards[currCardDisplayedIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer

            }
        }
}










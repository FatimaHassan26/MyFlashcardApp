package com.example.thatflashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
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
            val answerSideView = findViewById<View>(R.id.flashcard_answer)



// get the center for the clipping circle
            val cx = answerSideView.width / 2
            val cy = answerSideView.height / 2

// get the final radius for the clipping circle

// get the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

// create the animator for this view (the start radius is zero)

// create the animator for this view (the start radius is zero)
            val anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius)

            findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.INVISIBLE

            anim.duration = 3000
            anim.start()
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
            overridePendingTransition(R.anim.leftin, R.anim.right_in)

        }
        val leftOutAnim = AnimationUtils.loadAnimation(this, R.anim.leftin)
        val rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right_in)
        leftOutAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // this method is called when the animation first starts
                findViewById<View>(R.id.flashcard_question).startAnimation(leftOutAnim)
            }

            override fun onAnimationEnd(animation: Animation?) {
                // this method is called when the animation is finished playing
                findViewById<View>(R.id.flashcard_question).startAnimation(rightInAnim)

            }


            override fun onAnimationRepeat(animation: Animation) {
                // we don't need to worry about this method
            }

        })
        val nextButton = findViewById<ImageView>(R.id.flashcardnextbutton)
        nextButton.setOnClickListener{
            findViewById<View>(R.id.flashcard_question).startAnimation(leftOutAnim)
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










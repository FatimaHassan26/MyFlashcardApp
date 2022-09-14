package com.example.thatflashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.flashcard_question).setOnClickListener{
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.INVISIBLE
        }
        Log.i("Fatima", "Question tab was clicked")

        findViewById<View>(R.id.flashcard_answer).setOnClickListener{
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.VISIBLE
        }
        Log.i("Fatima", "Question tab was clicked")
    }
}
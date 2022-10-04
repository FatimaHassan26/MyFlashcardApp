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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.flashcard_question).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility = View.INVISIBLE
        }
        Log.i("Fatima", "Question tab was clicked")

        findViewById<View>(R.id.flashcard_answer).setOnClickListener {
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

                // This code is executed in StartingActivity after we come back from EndingActivity

                // This extracts any data that was passed back from EndingActivity
                val data: Intent? = result.data
                if (data != null) { // Check that we have data returned
                    val string1 =
                        data.getStringExtra("question") // 'string1' needs to match the key we used when we put the string in the Intent
                    val string2 = data.getStringExtra("answer")


                    // Log the value of the strings for easier debugging
                    Log.i("MainActivity", "question: $string1")

                    Log.i("MainActivity", "answer: $string2")
                } else {
                    Log.i("MainActivity", "Returned null data from AddCardActivity")
                }

            }




        findViewById<ImageView>(R.id.addButton).setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            // Launch EndingActivity wi the resultLauncher so we can execute more code
            // once we come back here from EndingActivity
            resultLauncher.launch(intent)
        }
                }



            }




package com.example.thatflashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)




        findViewById<ImageView>(R.id.SaveButton).setOnClickListener {
            val data = Intent()// create a new Intent, this is where we will put our data

            findViewById<EditText>(R.id.FieldQuestion).text.toString()
            findViewById<EditText>(R.id.FieldAnswer).text.toString()

            data.putExtra(
                "QUESTION_KEY",
                findViewById<EditText>(R.id.FieldQuestion).text.toString()
            ) // puts one string into the Intent, with the key as 'string1'

            data.putExtra(
                "ANSWER_KEY",
                findViewById<EditText>(R.id.FieldAnswer).text.toString()
            ) // puts another string into the Intent, with the key as 'string2

            setResult(RESULT_OK, data) // set result code and bundle data for response

            finish()
        }

        findViewById<ImageView>(R.id.cancelButton).setOnClickListener {
            finish()
        }
    }
    }


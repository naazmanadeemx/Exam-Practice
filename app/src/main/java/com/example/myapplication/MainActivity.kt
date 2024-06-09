package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val dates = mutableListOf<String>()
    private val morningTimes = mutableListOf<Int>()
    private val afternoonTimes = mutableListOf<Int>()
    private val notes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //testing
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            addScreenTime()
        }

        findViewById<Button>(R.id.buttonViewDetails).setOnClickListener {
            viewDetails()
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            clearData()
        }
    }

    private fun addScreenTime() {
        // Get references to the input views
        val editTextDate = findViewById<EditText>(R.id.editTextDate)
        val editTextMorning = findViewById<EditText>(R.id.editTextMorning)
        val editTextAfternoon = findViewById<EditText>(R.id.editTextAfternoon)
        val editTextNotes = findViewById<EditText>(R.id.editTextNotes)

        // Retrieve the input values
        val date = editTextDate.text.toString()
        val morningTime = editTextMorning.text.toString().toIntOrNull()
        val afternoonTime = editTextAfternoon.text.toString().toIntOrNull()
        val note = editTextNotes.text.toString()

        // Validate the input values
        if (date.isBlank() || morningTime == null || afternoonTime == null) {
            Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
        } else {

            dates.add(date)
            morningTimes.add(morningTime)
            afternoonTimes.add(afternoonTime)
            notes.add(note)

            editTextDate.text.clear()
            editTextMorning.text.clear()
            editTextAfternoon.text.clear()
            editTextNotes.text.clear()

            Toast.makeText(this, "Data added and fields cleared.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearData() {
        // Clear the parallel arrays
        dates.clear()
        morningTimes.clear()
        afternoonTimes.clear()
        notes.clear()

        // Clear the input fields
        findViewById<EditText>(R.id.editTextDate).text.clear()
        findViewById<EditText>(R.id.editTextMorning).text.clear()
        findViewById<EditText>(R.id.editTextAfternoon).text.clear()
        findViewById<EditText>(R.id.editTextNotes).text.clear()

        // Show a toast message indicating that the data has been cleared
        Toast.makeText(this, "Data cleared.", Toast.LENGTH_SHORT).show()
    }

    private fun viewDetails() {
        val intent = Intent(this, DetailedViewActivity::class.java)
        intent.putExtra("dates", dates.toTypedArray())
        intent.putExtra("morningTimes", morningTimes.toIntArray())
        intent.putExtra("afternoonTimes", afternoonTimes.toIntArray())
        intent.putExtra("notes", notes.toTypedArray())
        startActivity(intent)
    }
}

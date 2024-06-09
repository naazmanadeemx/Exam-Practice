package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class DetailedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)

        val dates = intent.getStringArrayExtra("dates") ?: emptyArray()
        val morningTimes = intent.getIntArrayExtra("morningTimes") ?: intArrayOf()
        val afternoonTimes = intent.getIntArrayExtra("afternoonTimes") ?: intArrayOf()
        val notes = intent.getStringArrayExtra("notes") ?: emptyArray()

        displayDetails(dates, morningTimes, afternoonTimes, notes)
    }

    private fun displayDetails(dates: Array<String>, morningTimes: IntArray, afternoonTimes: IntArray, notes: Array<String>) {
        val details = StringBuilder()
        var totalScreenTime = 0

        for (i in dates.indices) {
            val dailyTotal = morningTimes[i] + afternoonTimes[i]
            totalScreenTime += dailyTotal
            details.append("${dates[i]}: Morning: ${morningTimes[i]} mins, Afternoon: ${afternoonTimes[i]} mins, Total: $dailyTotal mins, Notes: ${notes[i]}\n")
        }

        val averageScreenTime = if (dates.isNotEmpty()) totalScreenTime / dates.size else 0

        findViewById<TextView>(R.id.textViewDetails).text = details.toString()
        findViewById<TextView>(R.id.textViewAverage).text = "Average Screen Time: $averageScreenTime minutes"

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}
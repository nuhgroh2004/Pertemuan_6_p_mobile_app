package com.example.pertemuan6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan6.databinding.ActivitySecondBinding
import java.util.Calendar

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the spinner by ID
        val spinnerRepeat = findViewById<Spinner>(R.id.spinnerRepeat)

        // Find the text view by ID tanggal
        val tvSelectDate = findViewById<TextView>(R.id.tvSelectDate)

        // Find the edit text by ID time
        val editTextHour = findViewById<EditText>(R.id.editTextHour)
        val editTextMinute = findViewById<EditText>(R.id.editTextMinute)
        val editTextSecond = findViewById<EditText>(R.id.editTextSecond)
        // Listener untuk EditText Hour, Minute, dan Second
        val calendar = Calendar.getInstance()

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.option, // Make sure 'option' matches the name in your strings.xml
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerRepeat.adapter = adapter
        }

        // Set an on item selected listener for the spinner tanggal
        tvSelectDate.setOnClickListener {
            // Get the current date that will be used as the default in picker
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog and show it
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Format the selected date and set it as the text of tvSelectDate
                val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                tvSelectDate.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }

        // Listener untuk EditText Hour, Minute, dan Second Time
        val timePickerListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val second = calendar.get(Calendar.SECOND)
            editTextHour.setText(String.format("%02d", hourOfDay))
            editTextMinute.setText(String.format("%02d", minute))
            editTextSecond.setText(String.format("%02d", second))
        }

        editTextHour.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(this, timePickerListener, hour, minute, true).show()
        }

        editTextMinute.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(this, timePickerListener, hour, minute, true).show()
        }

        editTextSecond.setOnClickListener {
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(this, timePickerListener, hour, minute, true).show()
        }

        // Listener untuk Button Add Task untuk mengirim data ke ThirdActivity
        binding.btnAddTask.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val repeat = binding.spinnerRepeat.selectedItem.toString()
            val date = binding.tvSelectDate.text.toString()
            val time = "${binding.editTextHour.text}:${binding.editTextMinute.text}:${binding.editTextSecond.text}"

            // Kirim data ke ThirdActivity
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("EXTRA_TITLE", title)
            intent.putExtra("EXTRA_REPEAT", repeat)
            intent.putExtra("EXTRA_DATE", date)
            intent.putExtra("EXTRA_TIME", time)
            startActivity(intent)
        }
    }
}
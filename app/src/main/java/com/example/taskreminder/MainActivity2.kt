package com.example.taskreminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.taskreminder.databinding.ActivityMain2Binding
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Spinner untuk Repeat Options
        val repeatOptions = resources.getStringArray(R.array.repeat_option)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, repeatOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.repeat.adapter = adapter

        // Date picker setup
        binding.tvSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                binding.tvSelectDate.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            }, year, month, day).show()
        }

        // Time picker setup on TextView click
        binding.timePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            // Show TimePickerDialog
            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                binding.timePicker.text = String.format("%02d:%02d", selectedHour, selectedMinute)
            }, hour, minute, true).show()  // true for 24-hour format
        }

        // Add task button setup
        binding.btnAddTask.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        // Ambil data dari tampilan
        val title = binding.etInsertTitle.text.toString().trim()
        val date = binding.tvSelectDate.text.toString().trim()
        val time = binding.timePicker.text.toString().trim()  // Waktu dari TextView TimePicker
        val repeat = binding.repeat.selectedItem.toString().trim()

        // Cek apakah semua input sudah diisi
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter the task title", Toast.LENGTH_SHORT).show()
            return
        }

        if (date.isEmpty() || date == "Select Date") {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            return
        }

        if (time.isEmpty() || time == "Select Time") {
            Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
            return
        }

        if (repeat.isEmpty()) {
            Toast.makeText(this, "Please select a repeat option", Toast.LENGTH_SHORT).show()
            return
        }

        // Jika semua data sudah lengkap, tampilkan dialog konfirmasi
        AlertDialog.Builder(this)
            .setTitle("SimpliRemind")
            .setMessage("Do you want to add this as a new task?")
            .setPositiveButton("Yes") { _, _ ->
                // Intent untuk pindah ke MainActivity3 dan mengirimkan data
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("TASK_TITLE", title)
                intent.putExtra("TASK_DATE", date)
                intent.putExtra("TASK_TIME", time)
                intent.putExtra("TASK_REPEAT", repeat)
                startActivity(intent)

                Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }
}

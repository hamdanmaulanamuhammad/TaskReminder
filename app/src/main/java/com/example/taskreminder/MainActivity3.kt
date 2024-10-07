package com.example.taskreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskreminder.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val title = intent.getStringExtra("TASK_TITLE")
        val date = intent.getStringExtra("TASK_DATE")
        val time = intent.getStringExtra("TASK_TIME")
        val repeat = intent.getStringExtra("TASK_REPEAT")

        // Set data ke tampilan
        binding.tvTaskTitle.text = title
        binding.tvDate.text = date
        binding.tvTime.text = time
        binding.tvRepeat.text = repeat

        // Fungsi untuk tombol "Add Task" (kembali ke MainActivity2)
        binding.btnAddTask.setOnClickListener {
            finish() // Tutup MainActivity3 untuk kembali ke MainActivity2
        }
    }
}

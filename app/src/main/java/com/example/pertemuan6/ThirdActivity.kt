package com.example.pertemuan6

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan6.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val title = intent.getStringExtra("EXTRA_TITLE")
        val repeat = intent.getStringExtra("EXTRA_REPEAT")
        val date = intent.getStringExtra("EXTRA_DATE")
        val time = intent.getStringExtra("EXTRA_TIME")

        // Menampilkan data ke layout menggunakan binding
        binding.tvTitle.text = title
        binding.tvDate.text = date
        binding.tvTime.text = time
        binding.tvRepeat.text = repeat

        // Set OnClickListener untuk tombol Add Task
        binding.btnAddTask.setOnClickListener {
            // Hapus data dari TextView
            binding.tvTitle.text = ""
            binding.tvDate.text = ""
            binding.tvTime.text = ""
            binding.tvRepeat.text = ""

            // Kembali ke SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

            // Opsional: Menutup ThirdActivity
            finish()
        }
    }
}
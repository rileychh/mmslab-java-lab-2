package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Connect XML components
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.buttonPlay.setOnClickListener { _ ->
            if (binding.editTextName.length() < 1) {
                binding.textViewStatus.text = "請輸入玩家姓名"
                return@setOnClickListener
            }

            val playerName = binding.editTextName.text.toString()
            binding.textViewName.text = "名字\n$playerName"

            if (binding.radioButtonScissors.isChecked) {
                binding.textViewPlayerPlayed.text = "我方出拳\n剪刀"
            } else if (binding.radioButtonRock.isChecked) {
                binding.textViewPlayerPlayed.text = "我方出拳\n石頭"
            } else {
                binding.textViewPlayerPlayed.text = "我方出拳\n布"
            }

            val computerRandom = (Math.random() * 3).toInt()
            if (computerRandom == 0) {
                binding.textViewComputerPlayed.text = "電腦出拳\n剪刀"
            } else if (computerRandom == 1) {
                binding.textViewComputerPlayed.text = "電腦出拳\n石頭"
            } else {
                binding.textViewComputerPlayed.text = "電腦出拳\n布"
            }

            if (binding.radioButtonScissors.isChecked && computerRandom == 2 ||
                binding.radioButtonRock.isChecked && computerRandom == 0 ||
                binding.radioButtonPaper.isChecked && computerRandom == 1
            ) {
                binding.textViewWinner.text = "勝利者\n$binding.editTextName.text"
                binding.textViewStatus.text = "恭喜您獲勝了！！！"
            } else if (binding.radioButtonScissors.isChecked && computerRandom == 1 ||
                binding.radioButtonRock.isChecked && computerRandom == 2 ||
                binding.radioButtonPaper.isChecked && computerRandom == 0
            ) {
                binding.textViewWinner.text = "勝利者\n電腦"
                binding.textViewStatus.text = "可惜，電腦獲勝了！"
            } else {
                binding.textViewWinner.text = "勝利者\n平手"
                binding.textViewStatus.text = "平局，請再試一次！"
            }
        }
    }
}

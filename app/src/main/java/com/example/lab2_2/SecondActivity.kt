package com.example.lab2_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab2_2.databinding.ActivityMainBinding
import com.example.lab2_2.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding
    var n = 20
    var argument = 0
    var a = 0
    var b = 0
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectNumber.visibility = View.VISIBLE
        binding.answerText.visibility = View.VISIBLE
        binding.answerButton.text = "Ответить"

        argument = intent.extras?.getInt("number") ?: 0
        n = 20
        a = if (argument == 0) Random.nextInt(2, 10) else argument
        b = Random.nextInt(2, 10)
        binding.questionText.text = "$a * $b"

        binding.answerButton.setOnClickListener {
            val ans = if (binding.selectNumber.text.toString().isEmpty()) 0 else binding.selectNumber.text.toString().toInt()
            if (ans == (a * b)) {
                count++
                val toast = Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val toast = Toast.makeText(this, "Неверный ответ", Toast.LENGTH_SHORT)
                toast.show()
            }
            when {
                n == 0 -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                n == 1 -> {
                    binding.selectNumber.visibility = View.GONE
                    binding.answerText.visibility = View.GONE

                    binding.questionText.text = "${count.toDouble() / 20.0 * 100}%"
                    binding.answerButton.text = "Меню"
                }
                else -> {
                    a = if (argument == 0) Random.nextInt(2, 10) else argument
                    b = Random.nextInt(2, 10)
                    binding.questionText.text = "$a * $b"
                }
            }
            n--
        }
    }
}
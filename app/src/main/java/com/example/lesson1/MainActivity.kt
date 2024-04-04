package com.example.lesson1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.image.setImageURI(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openGallery()
        plus()
        minus()
        savedInstanceState?.let {
            count = it.getInt(NUMBER, 0)
            binding.zero.text = count.toString()
        }
    }

    private fun openGallery() {
        binding.image.setOnClickListener {
            getContent.launch("image/*")
        }
    }

    private fun plus() {
        binding.plus.setOnClickListener {
            binding.zero.text = count++.toString()
            if (count == 10){
                val intent = Intent(this@MainActivity,SecondActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun minus() {
        binding.minus.setOnClickListener {
            if (count <= 0) {
                binding.minus.isEnabled
            } else {
                count--
                binding.zero .text= count.toString()
            }
        }
        }

    companion object{
        const val NUMBER = "count"
    }
}
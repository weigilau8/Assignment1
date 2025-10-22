package com.example.assignment1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Login button
        // Once click it will get the entered username and password
        // if else statement will check if the entered username or password are correct
        binding.btnLogin.setOnClickListener {

            val entered_Username = binding.etLoginUsername.text.toString()
            val entered_Password = binding.etLoginPassword.text.toString()

            // this will call the local storage
            val call_storage : SharedPreferences = getSharedPreferences("Sub_storage_SP", MODE_PRIVATE)

            val saved_username = call_storage.getString("USERNAME", null)
            val saved_password = call_storage.getString("PASSWORD", null)

            if (entered_Username == saved_username && entered_Password == saved_password) {
                Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT)

                val intent_home_page = Intent(this, HomePage::class.java)
                startActivity(intent_home_page)
            }
            else {
                Toast.makeText(this, "Invalid Username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button
        binding.btnBackToMain.setOnClickListener {
            val back_btn_intent = Intent(this, MainActivity::class.java)
            startActivity(back_btn_intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
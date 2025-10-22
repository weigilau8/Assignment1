package com.example.assignment1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivitySignUpPageBinding

class SignUpPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Create local Account
        binding.btnCreateAccount.setOnClickListener {
            val user = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()
            val cpass = binding.etConfirmPassword.text.toString()

            if( pass != cpass) {
                Toast.makeText(this, "Password don't match", Toast.LENGTH_LONG).show()
            } else {

                val shared_Preferences: SharedPreferences = getSharedPreferences("Sub_storage_SP", MODE_PRIVATE)

                val edit : SharedPreferences.Editor = shared_Preferences.edit()

                edit.putString("USERNAME", user)
                edit.putString("PASSWORD", pass)
                edit.apply()

                Toast.makeText(this, "Account Created!!", Toast.LENGTH_SHORT).show()

                val successLogin = Intent(this, HomePage::class.java)
                startActivity(successLogin)

            }
        }

        // Back button
        binding.btnBack.setOnClickListener {
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
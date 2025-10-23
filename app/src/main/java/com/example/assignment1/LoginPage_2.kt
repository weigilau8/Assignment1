package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivityLoginPage2Binding
import com.google.firebase.firestore.FirebaseFirestore

class LoginPage_2 : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPage2Binding
    private val TAG = "LoginPage_2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginPage2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val email = binding.etLoginEmail.text.toString()
            val pass = binding.etLoginPassword.text.toString()

            val ride_to_db = FirebaseFirestore.getInstance()

            // Check if both is not empty
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Stop execution
            }

            // Query and check email and password
            ride_to_db.collection("CoffeeClient")
                .whereEqualTo("email", email)
                .whereEqualTo("pass", pass)
                .get()
                .addOnSuccessListener { data ->

                    // if data is empty
                    if (data.isEmpty) {
                        Toast.makeText(this, "Login Failed: Invalid email or password.", Toast.LENGTH_LONG).show()
                        Log.d("Login_attempt", "Login failed for email: $email")
                    } else {
                        // data is not empty and correct
                        Toast.makeText(this, "Login successful! Welcome.", Toast.LENGTH_LONG).show()
                        Log.d("Login_attempt", "Login successful for user: ${data.documents.first().id}")

                        // Redirect to homepage
                        val successLogin = Intent(this, HomePage::class.java)
                        startActivity(successLogin)
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error connecting to the database: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.w("Login_attempt", "Error querying for user data", e)
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
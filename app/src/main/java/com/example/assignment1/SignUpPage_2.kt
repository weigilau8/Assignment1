package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivitySignUpPage2Binding
import com.google.firebase.firestore.FirebaseFirestore


class SignUpPage_2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPage2Binding
    private val TAG = "SignUpPage_2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpPage2Binding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val pass = binding.etPassword.text.toString()
            val cpass = binding.etConfirmPassword.text.toString()

            if( pass != cpass) {
                Toast.makeText(this, "Password don't match", Toast.LENGTH_LONG).show()
            } else {
                val firebase_connect = FirebaseFirestore.getInstance()

                val users = hashMapOf(
                    "email" to email,
                    "pass" to pass
                )

//                val connect_to_firebase = ride_to_db.collection("CoffeeClient").document("client_1")
                val collectPath = firebase_connect.collection("CoffeeClient").document("$email")

                // End up coding here.
                collectPath.set(users)
                    .addOnSuccessListener {
                        Log.d(TAG, "User was added successfully!")
                        Toast.makeText(this, "$email has been added", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error while adding User")
                        Toast.makeText(this, "$email has been used", Toast.LENGTH_LONG).show()
                    }
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


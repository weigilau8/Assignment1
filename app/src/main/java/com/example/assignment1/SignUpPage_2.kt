package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivitySignUpPage2Binding


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
//                val ride_to_db = FirebaseFirestore.getInstance()

                val users = hashMapOf(
                    "email" to email,
                    "pass" to pass
                )

//                val connect_to_firebase = ride_to_db.collection("CoffeeClient").document("client_1")

                // End up coding here.
//                connect_to_firebase.set(users)
//                    .addOnSuccessListener {
//                        Log.d(TAG, "User A was added successfully!")
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w(TAG, "Error while adding User A")
//                    }
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


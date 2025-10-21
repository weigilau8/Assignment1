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


            val users = hashMapOf(
                "email" to email,
                "pass" to pass
            )

            val connect_to_firebase = ride_to_db.collection("CoffeeClient").document("client_1")

            connect_to_firebase.get()
                .addOnSuccessListener { report ->
                    Log.d(TAG, "${report.id} => ${report.data}")
                }
                .addOnFailureListener { error_created ->
                    Log.w(TAG, "Error extracting the document", error_created)
                }

            Toast.makeText(this, "$ride_to_db", Toast.LENGTH_LONG).show()
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
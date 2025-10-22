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


            //check if email is valid. Needs @ and .
            var emailValid = false
            if (email.contains("@") && email.contains(".")) {
                emailValid = true
            } else {
                Toast.makeText(this, "Please enter a valid email address!", Toast.LENGTH_LONG)
                    .show()
            }

            //check password (uppercase, special, lenght, and confirm password)
            var hasUpperCase = false
            var hasSpecialChar = false
            var lengthValid = false
            var matchValid = false



            //loop through to check for the conditions
            for (char in pass){
                if (char in 'A'..'Z'){
                    hasUpperCase=true
                }
                if (!char.isLetterOrDigit()){
                    hasSpecialChar=true
                }
            }

            //check the length
            if (pass.length >= 8 && pass.length <= 15){
                lengthValid=true
            }

            //check if passwords matchs
            if (pass == cpass){
                matchValid=true

            }

            //message in case the conditions are not match
            if (!lengthValid) {
                Toast.makeText(this, "Your password must have between 8 and 15 characters", Toast.LENGTH_LONG).show()
            }
            if (!hasUpperCase) {
                Toast.makeText(this, "Your password must have an CAPITAL LETTER", Toast.LENGTH_LONG).show()
            }
            if (!hasSpecialChar) {
                Toast.makeText(this, "Your password must have an SPECIAL CHARACTER (!@#$%*)", Toast.LENGTH_LONG).show()
            }
            
            //check if password matches
            if (!matchValid){
                Toast.makeText(this,"Password don't match!!!", Toast.LENGTH_LONG).show()

            }

            //check if all conditions match
            if (emailValid&& lengthValid && hasUpperCase && hasSpecialChar && matchValid)
                {
                    val firebase_connect = FirebaseFirestore.getInstance()

                    val users = hashMapOf(
                        "email" to email,
                        "pass" to pass
                    )


                    val collectionPath = firebase_connect.collection("CoffeeClient")


                    // Query to check if the email already exists as a value in any document's field
                    // using whereEqualto to check the email has been registered
                    collectionPath.whereEqualTo("email", users["email"]).get()
                        .addOnSuccessListener { data ->
                            if (data.isEmpty) {
                                // If email is NEW
                                // Use .add() function to create unique document ID
                                // collectionPath.document("users").add(users)
                                collectionPath.add(users)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(
                                            TAG,
                                            "User was added successfully with unique ID: ${documentReference.id}"
                                        )
                                        Toast.makeText(
                                            this,
                                            "$email has been added",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        // Redirect to homepage
                                        val successLogin = Intent(this, HomePage::class.java)
                                        startActivity(successLogin)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(TAG, "Error while adding User to Firestore", e)
                                        Toast.makeText(
                                            this,
                                            "Error adding user $email",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                            } else {
                                // If Email is USED
                                // it will prompt it was taken
                                Log.w(TAG, "Registration Error: Email already in use.")
                                Toast.makeText(
                                    this,
                                    "$email has been used, Please use different Email",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        .addOnFailureListener { e ->
                            // Check for errors
                            Log.e(TAG, "Error checking email uniqueness", e)
                            Toast.makeText(this, "Database error", Toast.LENGTH_LONG).show()
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


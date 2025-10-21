package com.example.assignment1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Local
        // signup button
        binding.btnSignup.setOnClickListener {
            val go_to_signup = Intent(this,SignUpPage::class.java)
            startActivity(go_to_signup)
        }

        // btn Login
        binding.btnLogin.setOnClickListener {
            val got_to_login = Intent(this, LoginPage::class.java )
            startActivity(got_to_login)
        }

        // Firebase
        // Login button
        binding.btnLogin2.setOnClickListener {
            val got_to_login = Intent(this, LoginPage::class.java )
            startActivity(got_to_login)
        }
        // Signup2 button
        binding.btnSignup2.setOnClickListener {
            val go_to_signup_2 = Intent(this, SignUpPage_2::class.java)
            startActivity(go_to_signup_2)
        }

        binding.btnUpdates.setOnClickListener {
            val update_url = "https://codepen.io/simeydotme/pen/PwwYZOe"
            val intent_open_site = Intent(Intent.ACTION_VIEW, Uri.parse(update_url))
            startActivity(intent_open_site)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
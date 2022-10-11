package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button: Button = findViewById(R.id.buttonContinuarLogin)
        button.setOnClickListener {
            val intent = Intent(this@Login, Login::class.java)
            startActivity(intent)
        }

        val button1: Button = findViewById(R.id.buttonRegistrarse)
        button1.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }
    }
}
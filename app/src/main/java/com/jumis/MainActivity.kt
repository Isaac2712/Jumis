package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)



        val button: Button = findViewById(R.id.btnLogin)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }

        val button1: Button = findViewById(R.id.buttonRegister)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }


        /*
        Intents para las actividades

        Login --> Register
        val button: Button = findViewById(R.id.buttonRegister)
        button.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }

        Register --> Login
        val button: Button = findViewById(R.id.buttonRegister)
        button.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
        }






         */










    }
}
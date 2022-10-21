package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)



        val buttonHomeUser: Button = findViewById(R.id.buttonHomeUser)
        buttonHomeUser.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSettingsUser: Button = findViewById(R.id.buttonSettingsUser)
        buttonSettingsUser.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        val buttonUserUser: Button = findViewById(R.id.buttonUserUser)
        buttonUserUser.setOnClickListener {
            val intent = Intent(this, User::class.java)
            startActivity(intent)
        }




    }
}
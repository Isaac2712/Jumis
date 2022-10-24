package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class User : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val intent : Intent = intent
        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")

        var emailUser: String? = userNameData
        var passwordUser: String? = passwordData

        var textViewUser : TextView = findViewById(R.id.textViewUserEmail)
        var textViewPassword : TextView = findViewById(R.id.textViewUserPassword)
        var textInputLayoutEmail : TextInputLayout = findViewById(R.id.textInputLayoutEmail)

        textViewUser.text = emailUser
        textViewPassword.text = passwordUser

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

        val buttonCerrarSesion: Button = findViewById(R.id.buttonCerrarSesionUsuario)
        buttonCerrarSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonEditarUsuario: Button = findViewById(R.id.buttonEditarUsuario)
        buttonEditarUsuario.setOnClickListener {
            textViewUser.setText("")
            textViewPassword.setText("")


        }
    }
}
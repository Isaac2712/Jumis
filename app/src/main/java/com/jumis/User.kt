package com.jumis

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class User : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val intent : Intent = intent
        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase

        var emailUser: String? = userNameData
        var passwordUser: String? = passwordData

        var textViewUser : TextView = findViewById(R.id.textViewUserEmail)
        var textViewPassword : TextView = findViewById(R.id.textViewUserPassword)
        var editTextEmail : EditText = findViewById(R.id.EditTextEmail)
        var editTextPassword : EditText = findViewById(R.id.EditTextPassword)
        var buttonCancelar : Button = findViewById(R.id.buttonCancelar)

        textViewUser.text = emailUser
        textViewPassword.text = passwordUser
        editTextEmail.visibility = View.INVISIBLE
        editTextPassword.visibility = View.INVISIBLE
        buttonCancelar.visibility = View.INVISIBLE

        val buttonHomeUser: Button = findViewById(R.id.buttonHomeUser)
        buttonHomeUser.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Username", userNameData.toString())
            intent.putExtra("Password", passwordData.toString())
            startActivity(intent)
        }

        val buttonSettingsUser: Button = findViewById(R.id.buttonSettingsUser)
        buttonSettingsUser.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.putExtra("Username", userNameData.toString())
            intent.putExtra("Password", passwordData.toString())
            startActivity(intent)
        }

        val buttonUserUser: Button = findViewById(R.id.buttonUserUser)
        buttonUserUser.setOnClickListener {
            val intent = Intent(this, User::class.java)
            intent.putExtra("Username", userNameData.toString())
            intent.putExtra("Password", passwordData.toString())
            startActivity(intent)
        }

        val buttonCerrarSesion: Button = findViewById(R.id.buttonCerrarSesionUsuario)
        buttonCerrarSesion.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonEditarUsuario: Button = findViewById(R.id.buttonEditarUsuario)
        buttonEditarUsuario.setOnClickListener {
            textViewUser.visibility = View.INVISIBLE
            textViewPassword.visibility = View.INVISIBLE
            editTextEmail.visibility = View.VISIBLE
            editTextPassword.visibility = View.VISIBLE

            buttonEditarUsuario.visibility = View.INVISIBLE
            buttonCancelar.visibility = View.VISIBLE
        }

        buttonCancelar.setOnClickListener {
            textViewUser.visibility = View.VISIBLE
            textViewPassword.visibility = View.VISIBLE
            editTextEmail.visibility = View.INVISIBLE
            editTextPassword.visibility = View.INVISIBLE
            buttonEditarUsuario.visibility = View.VISIBLE
            buttonCancelar.visibility = View.INVISIBLE
        }

        val buttonGuardarUsuario: Button = findViewById(R.id.buttonGuardarUsuario)
        buttonGuardarUsuario.setOnClickListener {
            if(editTextEmail.text.isNotBlank() && editTextPassword.text.isNotBlank())
            {
                var values = ContentValues().apply {
                    put("email", editTextEmail.text.toString())
                    put("password", editTextPassword.text.toString())
                }
                val updatedRows = db_writer.update("User", values,"email LIKE ?", arrayOf(textViewUser.text.toString()))
                if(updatedRows > 0)
                {
                    val intent = Intent(this, User::class.java)
                    intent.putExtra("Username", editTextEmail.text.toString())
                    intent.putExtra("Password", editTextPassword.text.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Algo ha ido mal", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this, "No puedes dejar los campos vacios", Toast.LENGTH_LONG).show()
            }
        }
    }
}
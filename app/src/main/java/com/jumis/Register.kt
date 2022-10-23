package com.jumis

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase

        val buttonContinuar: Button = findViewById(R.id.buttonContinuarRegistro)
        val inputEmail: EditText = findViewById(R.id.inputEmailRegistro)
        val inputPassword: EditText = findViewById(R.id.inputPasswordRegistro)
        buttonContinuar.setOnClickListener {
            if(inputEmail.text.isNotBlank() && inputPassword.text.isNotBlank()) {
                var values = ContentValues().apply {
                    put("email", inputEmail.text.toString())
                    put("password", inputPassword.text.toString())
                }

                val newRowId = db_writer?.insert("User", null, values)
                println("INSERT--" + newRowId)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "No  pueden estar los campos vacios", Toast.LENGTH_LONG).show()
            }
        }
    }
}
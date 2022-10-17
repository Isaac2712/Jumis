package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*Codigo comentario: #004 */
        val email_usu: TextInputEditText = findViewById(R.id.inputEmailLogin)
        val pass_usu: TextInputEditText = findViewById(R.id.inputPasswordLogin)
        val buttonL: Button = findViewById(R.id.buttonContinuarLogin)
        buttonL.setOnClickListener {
            if(!email_usu.text.toString().equals("") && !pass_usu.text.toString().equals(""))
            {
                val intent = Intent(this@Login, MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "No puedes dejar los campos vacios", Toast.LENGTH_LONG).show()
            }
        }
        /*FIN 004*/

        val buttonR: Button = findViewById(R.id.buttonRegistrarse)
        buttonR.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            Toast.makeText(this, "Has hecho click en registro" , Toast.LENGTH_LONG).show()
        }
    }
}
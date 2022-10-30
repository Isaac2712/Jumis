package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase

        /*Codigo comentario: #004 */
        var usuarioEncontrado: Boolean = false;
        var mensaje = ""
        var itemEmail: String?
        var itemPassword: String?
        val email_usu: TextInputEditText = findViewById(R.id.inputEmailLogin)
        val pass_usu: TextInputEditText = findViewById(R.id.inputPasswordLogin)
        val buttonL: Button = findViewById(R.id.buttonContinuarLogin)
        buttonL.setOnClickListener {
            if(email_usu.text.toString().isNotBlank() && pass_usu.text.toString().isNotBlank())
            {
                val cursor = db_reader.query(
                    "User", // The table to query
                    null, // The array of columns to return (pass null to get all)
                    null, // The columns for the WHERE clause
                    null, // The values for the WHERE clause
                    null, // don't group the rows
                    null, // don't filter by row groups
                    null // The sort order
                )
                // Store all recovered data
                with(cursor) {
                    while (moveToNext() && !usuarioEncontrado) {
                        itemEmail = getString(getColumnIndexOrThrow("email"))
                        itemPassword = getString(getColumnIndexOrThrow("password"))
                        if(email_usu.text.toString() == itemEmail.toString() && pass_usu.text.toString() == itemPassword.toString()) {
                            usuarioEncontrado = true
                        } else {
                             mensaje = "El usuario no existe o la contraseña no es valida."
                        }
                    }
                }
                cursor.close()
                if(usuarioEncontrado)
                {
                    /*Codigo comentario: #104 */

                    var editTextUser : EditText = findViewById(R.id.inputEmailLogin)
                    var editTextPasswd : EditText = findViewById(R.id.inputPasswordLogin)

                    var intent : Intent = Intent(this@Login, MainActivity::class.java)
                    intent.putExtra("Username", email_usu.text.toString())
                    intent.putExtra("Password", pass_usu.text.toString())

                    /*FIN 104*/
                    startActivity(intent)
                } else
                {
                    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this, "Rellene los campos vacios", Toast.LENGTH_LONG).show()
            }
        }
        /*FIN 004*/

        val buttonR: Button = findViewById(R.id.buttonRegistrarse)
        buttonR.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }
    }
}
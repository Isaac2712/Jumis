package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class newTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        val intent : Intent = intent
        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")

        println("Paso newTask de email: " + userNameData)
        println("Paso newTask de contrasena: " + passwordData)



        val buttonCrearTarea: Button = findViewById(R.id.buttonCrearTarea)
        buttonCrearTarea.setOnClickListener {

            //


            val nombreTarea: TextInputEditText = findViewById(R.id.inputNombreTarea)
            val descripcion: TextInputEditText = findViewById(R.id.descripcion)
            val fecha: TextInputEditText = findViewById(R.id.inputFecha)
            val hora: TextInputEditText = findViewById(R.id.inputHora)



            var intent : Intent = Intent(this, MainActivity::class.java)

            if(nombreTarea.text.toString().isNotBlank() && descripcion.text.toString().isNotBlank() && fecha.text.toString().isNotBlank() && hora.text.toString().isNotBlank()){
                intent.putExtra("Username", userNameData)
                intent.putExtra("Password", passwordData)

                intent.putExtra("nameTask", nombreTarea.text.toString())
                intent.putExtra("description", descripcion.text.toString())
                intent.putExtra("date", fecha.text.toString())
                intent.putExtra("hour", hora.text.toString())

                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Rellene los campos vacios", Toast.LENGTH_LONG).show()
            }


            //

        }



        val buttonHome: Button = findViewById(R.id.buttonHome2)
        buttonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Username", userNameData)
            intent.putExtra("Password", passwordData)
            startActivity(intent)
        }

        val buttonSettings: Button = findViewById(R.id.buttonSettings2)
        buttonSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.putExtra("Username", userNameData)
            intent.putExtra("Password", passwordData)
            startActivity(intent)
        }

        val buttonUser: Button = findViewById(R.id.buttonUser2)
        buttonUser.setOnClickListener {
            var intent : Intent = Intent(this, User::class.java)
            intent.putExtra("Username", userNameData)
            intent.putExtra("Password", passwordData)
            startActivity(intent)
        }


    }
}
package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Codigo comentario: #002 */
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.logo)
        /*FIN 002*/

        setContentView(R.layout.activity_settings)

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
        } */
    }

    /* Codigo de comentario: #001*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nueva_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.añadirNuevaLista -> {
                Toast.makeText(this, "Lista", Toast.LENGTH_LONG)
                    .show()
                return true
            }
            R.id.añadirNuevaTarea -> {
                Toast.makeText(this, "Tarea", Toast.LENGTH_LONG)
                    .show()
                return true
            }
            R.id.btnMore -> {
                Toast.makeText(this, "More", Toast.LENGTH_LONG)
                    .show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /*FIN 001*/
}
package com.jumis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class Menubottom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_con_filtro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when(item.itemId) {
                R.id.paginaHome -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Has hecho click en home" , Toast.LENGTH_LONG).show()
                    return true
                }
                R.id.paginaUser -> {
                    val intent = Intent(this, User::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Has hecho click en user" , Toast.LENGTH_LONG).show()
                    return true
                }
                R.id.paginaAjustes -> {
                    val intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Has hecho click en ajustes" , Toast.LENGTH_LONG).show()
                    return true
                }
                R.id.filtro -> {
                    Toast.makeText(this, "Has hecho click en filtro" , Toast.LENGTH_LONG).show()
                    return true
                }
                else -> super.onOptionsItemSelected(item)
            }
    }
}
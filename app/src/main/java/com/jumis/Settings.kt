package com.jumis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class Settings : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        /* Codigo comentario: #003 */
        val switch = findViewById<Switch>(R.id.switchTemaOscuro)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            } else {
                // Acciones al estar inactivo
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
        /* FIN 003 */

        val buttonHomeSettings: Button = findViewById(R.id.buttonHomeSettings)
        buttonHomeSettings.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSettingsSettings: Button = findViewById(R.id.buttonSettingsSettings)
        buttonSettingsSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        val buttonUserSettings: Button = findViewById(R.id.buttonUserSettings)
        buttonUserSettings.setOnClickListener {
            val intent = Intent(this, User::class.java)
            startActivity(intent)
        }



    }
}

package com.jumis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class Settings : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")

        println("Settings Paso de email: " + userNameData.toString())

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

        val tipografia = resources.getStringArray(R.array.tipografia)
        val spinnerTipografia: Spinner = findViewById(R.id.spinnerTypography)
        if (spinnerTipografia != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipografia)
            spinnerTipografia.adapter = adapter
            spinnerTipografia.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


            val buttonHomeSettings: Button = findViewById(R.id.buttonHomeSettings)
            buttonHomeSettings.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Username", userNameData.toString())
                intent.putExtra("Password", passwordData.toString())
                startActivity(intent)
            }

            val buttonSettingsSettings: Button = findViewById(R.id.buttonSettingsSettings)
            buttonSettingsSettings.setOnClickListener {
                val intent = Intent(this, Settings::class.java)
                intent.putExtra("Username", userNameData.toString())
                intent.putExtra("Password", passwordData.toString())
                startActivity(intent)
            }

            val buttonUserSettings: Button = findViewById(R.id.buttonUserSettings)
            buttonUserSettings.setOnClickListener {
                val intent = Intent(this, User::class.java)
                intent.putExtra("Username", userNameData.toString())
                intent.putExtra("Password", passwordData.toString())
                startActivity(intent)
            }

        }
    }
}

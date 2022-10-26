package com.jumis

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var emailUser: String? = ""
        var passwordUser: String? = ""



        /* Codigo comentario: #002 */
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.logo)
        /*FIN 002*/


        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase

        /*// Create a new map of values, where column names are the keys
        var values = ContentValues().apply {
            put("email", "juan@gmail.com")
            put("password", "123")
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db_writer?.insert("User", null, values)
        println("INSERT--" + newRowId)*/

        // Update rows, return the number of updated rows
        //val updatedRows = db_writer.update("Usuario", values,"email LIKE ?",
        //arrayOf("juan@gmail.com"))

        // Issue SQL statement, return the number of deleted rows
        //val deletedRows = db_writer?.delete("User", "email LIKE ?", arrayOf("AS@gmail.com"))

         // Do a query for reading data, return a cursor with all the recovered data
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
            while (moveToNext()) {
                val itemUid = getLong(getColumnIndexOrThrow("USERID"))
                val itemEmail = getString(getColumnIndexOrThrow("email"))
                val itemPassword = getString(getColumnIndexOrThrow("password"))
                //val deletedRows = db_writer?.delete("User", "email LIKE ?", arrayOf(itemEmail.toString()))
                println("Email " + itemEmail)
                println("Password " + itemPassword)
            }
        }
        cursor.close()

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val buttonHome: Button = findViewById(R.id.buttonHome)
        buttonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSettings: Button = findViewById(R.id.buttonSettings)
        buttonSettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        val buttonUser: Button = findViewById(R.id.buttonUser)
        buttonUser.setOnClickListener {

                var intent : Intent = Intent(this, User::class.java)
                intent.putExtra("Username", emailUser)
                intent.putExtra("Password", passwordUser)

                startActivity(intent)



        }

        val buttonTask: Button = findViewById(R.id.buttonTask)
        buttonTask.setOnClickListener {
            val intent = Intent(this, Task::class.java)
            startActivity(intent)
        }



        val intent : Intent = intent
        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")
        println("Paso de email: " + userNameData)
        println("Paso de contrasena: " + passwordData)

        emailUser = userNameData
        passwordUser = passwordData



        /* Profesor
        Paso 1

        var buttonLogin : Button = findViewById(R.id.buttonLogin)
        var buttonRegister : Button = findViewById(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            var intent = Intent(this, User::class.java)
            startActivity(intent)
        }
        --Al login--

        --Paso 2
        buttonLogin.setOnClickListener {
            var editTextUser : EditText = findViewById(R.id.editTextUser)
            var editTextPasswd : EditText = findViewById(R.id.editTextPasswd)

            if(editTextUser.text.toString().equals("Juan") && editTextPasswd.text.toString().equals("1234")){
                var intent : Intent = Intent(this, Home::class.java)
                intent.putExtra("Username", editTextUser.text.toString())
            }
            --Le manda el contenido de editTextUser al home
            * Ir a home

            var intent = Intent(this, User::class.java)
            startActivity(intent)
        }

        --Para que la app acceda a la cámara
        buttonFoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
        *** Importante ***: hay un intent para alarmas y calendario, para las tareas

        --Para abrir un enlace de internet
        buttonGoogle.setOnClickListener {
            val i = Intent(intent.ACTION_VIEW)
            i.data = Uri.parse("https://www.google.es")
            startActivity(i)
            startActivity(intent)
        }
        */
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
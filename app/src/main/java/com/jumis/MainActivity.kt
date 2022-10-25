package com.jumis

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailUser: String? = ""
        var passwordUser: String? = ""
        val intent : Intent = intent
        val userNameData = intent.getStringExtra("Username")
        val passwordData = intent.getStringExtra("Password")
        println("Paso de email: " + userNameData)
        println("Paso de contrasena: " + passwordData)

        emailUser = userNameData
        passwordUser = passwordData

        /* Codigo comentario: #002 */
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.logo)
        /*FIN 002*/

        var listView: ListView = findViewById(R.id.listViewMain)
        var tarea = ArrayList<String>()
        tarea.add("hola");

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tarea)
        listView.adapter = adapter;
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Click", Toast.LENGTH_LONG).show()
        }

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase

        // CREAMOS TAREA
        var valuesTask = ContentValues().apply {
            put("nameTask", "Tarea 1 Android")
            put("description", "Tarea")
            put("nameList", "Lista ucam")
            put("date", "2022-10-21")
            put("hour", "10:30:00")
        }

        var valuesTask2 = ContentValues().apply {
            put("nameTask", "Tarea 2 Sistemas")
            put("description", "Tarea de sistema")
            put("nameList", "Lista ucam")
            put("date", "2022-10-25")
            put("hour", "11:08:00")
        }

        // INSERTAMOS TAREA
        var newRowIdTarea = db_writer?.insert("Task", null, valuesTask)
        println("INSERT--" + newRowIdTarea)
        var newRowIdTarea2 = db_writer?.insert("Task", null, valuesTask2)
        println("INSERT--" + newRowIdTarea2)

        // RELACIONAMOS TAREA CON USUARIO
       var values = ContentValues().apply {
            put("USERTASKID", "1")
            put("TASKUSERID", "1")
        }

        var values2 = ContentValues().apply {
            put("USERTASKID", "1")
            put("TASKUSERID", "2")
        }

        // INSERTAMOS RELACION TAREA CON USUARIO
        val newRowIdUserTarea = db_writer?.insert("UserTask", null, values)
        println("INSERT--" + newRowIdUserTarea)
        val newRowIdUserTarea2 = db_writer?.insert("UserTask", null, values2)
        println("INSERT--" + newRowIdUserTarea)

        // Update rows, return the number of updated rows
        //val updatedRows = db_writer.update("Usuario", values,"email LIKE ?",
        //arrayOf("juan@gmail.com"))
        //SELECT *  FROM User, Task, UserTask WHERE UserTask.USERTASKID == User.USERID AND UserTask.TASKUSERID == Task.TASKID

        // Issue SQL statement, return the number of deleted rows
        //val deletedRows = db_writer?.delete("User", "email LIKE ?", arrayOf("AS@gmail.com"))

         // Do a query for reading data, return a cursor with all the recovered data
        val cursor = db_reader.query(
        "User, Task, UserTask", // The table to query
        null, // The array of columns to return (pass null to get all)
            "UserTask.USERTASKID == User.USERID AND UserTask.TASKUSERID == Task.TASKID AND User.email == '" + emailUser + "'", // The columns for the WHERE clause
        null, // The values for the WHERE clause
        null, // don't group the rows
        null, // don't filter by row groups
        null // The sort order
        )

        // Store all recovered data
        with(cursor) {
            while (moveToNext()) {
                val itemNameTask = getString(getColumnIndexOrThrow("nameTask"))
                val itemNameList = getString(getColumnIndexOrThrow("nameList"))
                val itemDescription = getString(getColumnIndexOrThrow("description"))
                val itemDate = getString(getColumnIndexOrThrow("date"))
                val itemHour = getString(getColumnIndexOrThrow("hour"))
                println("Tarea " + itemNameTask)
                println("Lista " + itemNameList)
                println("Descripcion " + itemDescription)
            }
        }
        cursor.close()

        val buttonHome: Button = findViewById(R.id.buttonHome)
        buttonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
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
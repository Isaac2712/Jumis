package com.jumis

import CustomAdapter
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var emailUser: String? = ""
    var passwordUser: String? = ""
    var itemIdUser: String? = ""
    var itemIdTask: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerViewMain)
        val datos = ArrayList<ItemsViewModel>()

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase
        val adapter = CustomAdapter(datos)

        var userNameData: String? = null
        var passwordData: String? = null


        val intent : Intent = intent
        val idUserName = intent.getStringExtra("idUsername")
        userNameData = intent.getStringExtra("Username")
        passwordData = intent.getStringExtra("Password")

        println("---Work Main Id User: " + idUserName)

        println("---Worky Main Id User: " + userNameData.toString())
        println("---Worky Main Password User: " + passwordData.toString())

        itemIdUser = idUserName
        val IDTareaEliminada = intent.getStringExtra("IDTareaEliminada")

        if(idUserName != null){

            val cursorUser = db_reader.query("User",null,
                "User.USERID == '" + idUserName + "'", // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
            )
            with(cursorUser) {
                while (moveToNext()) {
                    userNameData = getString(getColumnIndexOrThrow("email"))
                    passwordData = getString(getColumnIndexOrThrow("password"))
                }
            }
            cursorUser.close()

            println("---Work if")

        }

        println("Paso Main de email: " + userNameData)
        println("Paso Main de contrasena: " + passwordData)

        itemIdUser = idUserName
        emailUser = userNameData
        passwordUser = passwordData

        // Pedir a la BBDD id del usuario y de la tarea
        val cursorUsuario = db_reader.query(
            "User",null,
            "User.email == '" + emailUser + "'", // The columns for the WHERE clause
            null, // The values for the WHERE clause
            null, // don't group the rows
            null, // don't filter by row groups
            null // The sort order
        )
        with(cursorUsuario) {
            while (moveToNext()) {
                itemIdUser = getString(getColumnIndexOrThrow("USERID"))
            }
        }
        cursorUsuario.close()

        if(IDTareaEliminada != null){
            println("Id tarea eliminada en Main: " + IDTareaEliminada)
        }
        /* Codigo comentario: #002 */
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        getSupportActionBar()?.setIcon(R.drawable.logo)
        /*FIN 002*/

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

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

        //val intent : Intent = intent
        val nameTask = intent.getStringExtra("nameTask")
        val description = intent.getStringExtra("description")
        val date = intent.getStringExtra("date")
        val hour = intent.getStringExtra("hour")
        if(nameTask != null && description != null && date != null && hour != null) {
            var valuesNewTask = ContentValues().apply {
                put("nameTask", nameTask)
                put("description", description)
                put("nameList", "Lista ucam")
                put("date", date)
                put("hour", hour)
            }

            var newRowIdTarea = db_writer?.insert("Task", null, valuesNewTask)
            println("INSERT Table Task:--" + newRowIdTarea)

            val cursorTarea = db_reader.query(
                "Task", null,
                "Task.NameTask == '" + nameTask + "' AND Task.description == '" + description + "'", // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
            )
            with(cursorTarea) {
                while (moveToNext()) {
                    itemIdTask = getString(getColumnIndexOrThrow("TASKID"))
                }
            }
            cursorTarea.close()

            var valuesNewTaskRelation = ContentValues().apply {
                put("USERTASKID", itemIdUser)
                put("TASKUSERID", itemIdTask)
            }

            val newRowIdUserTarea = db_writer?.insert("UserTask", null, valuesNewTaskRelation)
            println("INSERT Table UserTask--" + newRowIdUserTarea)
            println("itemIdUser--" + itemIdUser)
            println("itemIdTask--" + itemIdTask)
        }

        /* INSERTAMOS TAREA
        var newRowIdTarea = db_writer?.insert("Task", null, valuesTask)
        println("INSERT--" + newRowIdTarea)
        var newRowIdTarea2 = db_writer?.insert("Task", null, valuesTask2)
        println("INSERT--" + newRowIdTarea2)*/

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
        /*
        val newRowIdUserTarea = db_writer?.insert("UserTask", null, values)
        println("INSERT--" + newRowIdUserTarea)
        val newRowIdUserTarea2 = db_writer?.insert("UserTask", null, values2)
        println("INSERT--" + newRowIdUserTarea)
        */
        // Update rows, return the number of updated rows
        //val updatedRows = db_writer.update("Usuario", values,"email LIKE ?",
        //arrayOf("juan@gmail.com"))
        //SELECT *  FROM User, Task, UserTask WHERE UserTask.USERTASKID == User.USERID AND UserTask.TASKUSERID == Task.TASKID
        //SELECT nameList FROM User, Task WHERE  User.email == "isaac" GROUP BY nameList HAVING count(*)>1

        // Issue SQL statement, return the number of deleted rows
        //val deletedRows = db_writer?.delete("User", "email LIKE ?", arrayOf("AS@gmail.com"))

        var nombre_lista: String? = ""

        val cursorSoloLista = db_reader.query(
            "User, Task", null, "User.email == '" + emailUser + "'", null,
            "nameList", "count(*)>1", null
        )

        with(cursorSoloLista) {
            while (moveToNext()) {
                val itemNameList = getString(getColumnIndexOrThrow("nameList"))
                nombre_lista = itemNameList
            }
        }
        cursorSoloLista.close()

        val textViewListaMain: TextView = findViewById(R.id.textViewListaMain)
        textViewListaMain.setText(nombre_lista)

        println("---Work emailUser: " + emailUser)

        val cursor = db_reader.query(
            "User, Task, UserTask",null,
            "UserTask.USERTASKID == User.USERID AND UserTask.TASKUSERID == Task.TASKID AND User.email == '" + emailUser + "'", // The columns for the WHERE clause
            null, // The values for the WHERE clause
            null, // don't group the rows
            null, // don't filter by row groups
            null // The sort order
        )

        with(cursor) {
            while (moveToNext()) {

                val itemIdTask = getString(getColumnIndexOrThrow("TASKID"))
                val itemNameTask = getString(getColumnIndexOrThrow("nameTask"))
                val itemNameList = getString(getColumnIndexOrThrow("nameList"))
                val itemDescription = getString(getColumnIndexOrThrow("description"))
                val itemDate = getString(getColumnIndexOrThrow("date"))
                val itemHour = getString(getColumnIndexOrThrow("hour"))
                datos.add(ItemsViewModel(R.drawable.ic_outline_coffee_24, itemIdUser, itemIdTask, itemNameTask, itemDescription,itemDate , itemHour))
            }
        }
        cursor.close()

        // Añadir datos con el adapter a recyclerview
        recyclerview.adapter = adapter

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
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            startActivity(intent)
        }

        val buttonUser: Button = findViewById(R.id.buttonUser)
        buttonUser.setOnClickListener {
            var intent : Intent = Intent(this, User::class.java)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            startActivity(intent)
        }
    }

    /* Codigo de comentario: #001*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nueva_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            /*R.id.añadirNuevaLista -> {
                Toast.makeText(this, "Lista", Toast.LENGTH_LONG)
                    .show()
                return true
            }*/
            R.id.añadirNuevaTarea -> {
                //Toast.makeText(this, "Tarea", Toast.LENGTH_LONG).show()
                var intent : Intent = Intent(this, newTask::class.java)
                intent.putExtra("Username", emailUser)
                intent.putExtra("Password", passwordUser)
                startActivity(intent)
                return true
            }
            R.id.btnMore -> {
                //Toast.makeText(this, "More", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /*FIN 001*/
}
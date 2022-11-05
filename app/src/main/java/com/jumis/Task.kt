package com.jumis

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button
import android.widget.TextView

class Task : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val db_writer = dataBaseHelper.writableDatabase
        val itemIdUser = intent.getStringExtra("textViewIdUser")
        val itemIdTask = intent.getStringExtra("textViewIdTarea")

        println("---Work Id tarea: " + itemIdTask)
        println("---Work Id User: " + itemIdUser)

        var itemIdUserTask: String? = ""
        var emailUser: String? = ""
        var passwordUser: String? = ""
        var userId: String? = ""
        val intent : Intent = intent
        val textViewTarea = intent.getStringExtra("textViewTareaCard")
        val textViewDescription = intent.getStringExtra("textViewDescriptionCard")
        val textViewFecha = intent.getStringExtra("textViewFechaCard")
        val textViewHora = intent.getStringExtra("textViewHoraCard")
        val nameTask: TextView = findViewById(R.id.nameTask)
        val textViewDescriptionTask: TextView = findViewById(R.id.textViewDescriptionTask)
        val textViewFechaTask: TextView = findViewById(R.id.textViewFechaTask)
        val textViewHoraTask: TextView = findViewById(R.id.textViewHoraTask)

        nameTask.setText(textViewTarea)
        textViewDescriptionTask.setText(textViewDescription)
        textViewFechaTask.setText(textViewFecha)
        textViewHoraTask.setText(textViewHora)

        val buttonEliminar: Button = findViewById(R.id.buttonEliminarTarea)
        buttonEliminar.setOnClickListener {
            val cursorUserTarea = db_reader.query("UserTask",null,
                "UserTask.TASKUSERID == '" + itemIdTask + "'", // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
            )
            with(cursorUserTarea) {
                while (moveToNext()) {
                    userId = getString(getColumnIndexOrThrow("USERTASKID"))
                    //val passwordUser = getString(getColumnIndexOrThrow("password"))
                }
            }
            cursorUserTarea.close()

            val cursorUser = db_reader.query("User",null,
                "User.USERID == '" + userId + "'", // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
            )
            with(cursorUser) {
                while (moveToNext()) {
                    val emailUser = getString(getColumnIndexOrThrow("email"))
                    val passwordUser = getString(getColumnIndexOrThrow("password"))

                    println("---Funciona Email Task: " + emailUser)
                    println("---Funciona Password Task: " + passwordUser)
                }
            }
            cursorUser.close()

            // Issue SQL statement, return the number of deleted rows
            val deletedRowsTask = db_writer?.delete("Task", "TASKID LIKE ?", arrayOf(itemIdTask))
            val deletedRowsUserTask = db_writer?.delete("UserTask", "TASKUSERID LIKE ?", arrayOf(itemIdTask))

            println("itemIdTask -->" + itemIdTask)

            println("deletedRowsUserTask -->" + deletedRowsUserTask)
            println("deletedRowsTask -->" + deletedRowsTask)

            val intent = Intent(this, MainActivity::class.java)

            println("---Work Task Intent Id User: " + itemIdUser)
            intent.putExtra("idUsername", itemIdUser)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            intent.putExtra("IDTareaEliminada", itemIdTask)

            println("---emailUser en Eliminar Task: " + emailUser)

            startActivity(intent)
        }

        println("---emailUser en Task: " + emailUser)

        val buttonHome3: Button = findViewById(R.id.buttonHome3)
        buttonHome3.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            println("---Work Task Intent Id User: " + itemIdUser)
            intent.putExtra("idUsername", itemIdUser)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            startActivity(intent)
        }

        val buttonSettings3: Button = findViewById(R.id.buttonSettings3)
        buttonSettings3.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            startActivity(intent)
        }

        val buttonUser3: Button = findViewById(R.id.buttonUser3)
        buttonUser3.setOnClickListener {
            var intent : Intent = Intent(this, User::class.java)
            intent.putExtra("Username", emailUser)
            intent.putExtra("Password", passwordUser)
            startActivity(intent)
        }
    }
}

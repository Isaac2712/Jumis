package com.jumis

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView

class Task : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader_task = dataBaseHelper.readableDatabase
        val db_writer_task = dataBaseHelper.writableDatabase

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

    }
}

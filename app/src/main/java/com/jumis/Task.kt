package com.jumis

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Task : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val dataBaseHelper = DatabaseHelper(applicationContext)
        val db_reader_task = dataBaseHelper.readableDatabase
        val db_writer_task = dataBaseHelper.writableDatabase

        // Create a new map of values, where column names are the keys
        var valuesTask = ContentValues().apply {
            put("nameTask", "Tarea 1 Android")
            put("description", "Tarea en la cual hay que realizar ...")
            put("nameList", "lista ucam")
            put("date", "2022-10-21")
            put("hour", "10:30:00")
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db_writer_task?.insert("Task", null, valuesTask)
        println("INSERT--" + newRowId)

        // Do a query for reading data, return a cursor with all the recovered data
        val cursorTask = db_reader_task.query(
            "Task", // The table to query
            null, // The array of columns to return (pass null to get all)
            null, // The columns for the WHERE clause
            null, // The values for the WHERE clause
            null, // don't group the rows
            null, // don't filter by row groups
            null // The sort order
        )

        with(cursorTask) {
            while (moveToNext()) {
                val itemTaskID = getLong(getColumnIndexOrThrow("TASKID"))
                val itemNameTask = getString(getColumnIndexOrThrow("nameTask"))
                val itemDescription = getString(getColumnIndexOrThrow("description"))
                val itemNameList = getString(getColumnIndexOrThrow("nameList"))
                val itemDate = getString(getColumnIndexOrThrow("date"))
                val itemHour = getString(getColumnIndexOrThrow("hour"))
                println("Task Id: " + itemTaskID)
                println("Name Task: " + itemNameTask)
                println("Description: " + itemDescription)
                println("Name List: " + itemNameList)
                println("Date: " + itemDate)
                println("Hour: " + itemHour)

            }
        }
        cursorTask.close()




    }
}
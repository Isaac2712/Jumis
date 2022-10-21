package com.jumis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,
    DATABASE_VERSION) {
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MySQLDataBase"
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE User (" +
                "USERID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL)")

        db.execSQL("CREATE TABLE UserTask (" +
                "USERTASKID INTEGER NOT NULL, " +
                "TASKUSERID TEXT NOT NULL, " +
                "FOREIGN KEY(TASKUSERID) REFERENCES Task(TASKID)," +
                "FOREIGN KEY(USERTASKID) REFERENCES User(USERID))")



        db.execSQL("CREATE TABLE Task (" +
                "TASKID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nameTask TEXT NOT NULL, " +
                "description TEXT NOT NULL, " +
                "nameList TEXT NOT NULL," +
                "date DATE NOT NULL," +
                "hour TIME NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    // This database is only a cache for online data, so its upgrade policy is to simply to discard the data and start over
        //db.execSQL("DROP TABLE IF EXISTS User")
        //db.execSQL("DROP TABLE IF EXISTS Task")
        //db.execSQL("DROP TABLE IF EXISTS UserTask")
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
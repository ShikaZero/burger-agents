package com

import android.app.Application
import androidx.room.Room
import bdd.AppDatabase
import bdd.DATABASE_NAME

class App : Application() {

    companion object{
        lateinit var db : AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, AppDatabase::class.java,  DATABASE_NAME).build()


    }
}
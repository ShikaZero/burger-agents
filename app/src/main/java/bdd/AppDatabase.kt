package bdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import model.Burger

const val DATABASE_NAME = "burgerAgentDataBase.db"
const val DATABASE_VERSION = 1

@Database(entities = [Burger::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun BurgerListDao() : BurgerListDao

    // Design pattern Singleton
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }
    }
}
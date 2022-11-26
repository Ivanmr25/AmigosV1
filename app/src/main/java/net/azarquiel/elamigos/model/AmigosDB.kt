package net.azarquiel.elamigos.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Grupos::class, Amigos::class], version = 1)
abstract class AmigosDB: RoomDatabase() {
    abstract fun GruposDao(): GruposDao
    abstract fun AmigosDao(): AmigosDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  AmigosDB? = null

        fun getDatabase(context: Context): AmigosDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AmigosDB::class.java,   "carroDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

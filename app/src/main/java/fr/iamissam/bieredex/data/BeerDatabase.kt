package fr.iamissam.bieredex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iamissam.bieredex.data.models.BeerData

@Database(entities = [BeerData::class], version = 2, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {

        @Volatile
        private var INSTANCE: BeerDatabase? = null

        fun getDatabase(context: Context): BeerDatabase {

            INSTANCE?.let {
                return it
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BeerDatabase::class.java,
                    "beer_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

}
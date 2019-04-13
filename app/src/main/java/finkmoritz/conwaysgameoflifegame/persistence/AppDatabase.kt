package finkmoritz.conwaysgameoflifegame.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import finkmoritz.conwaysgameoflifegame.config.ConfigDAO
import finkmoritz.conwaysgameoflifegame.config.ConfigDO

@Database(entities = [ConfigDO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDatabase::class.java, "App.db")
                .allowMainThreadQueries() //TODO: replace, not recommended
                .build()
    })
}

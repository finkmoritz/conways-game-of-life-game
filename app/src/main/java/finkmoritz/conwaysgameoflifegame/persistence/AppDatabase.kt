package finkmoritz.conwaysgameoflifegame.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import finkmoritz.conwaysgameoflifegame.config.ConfigDAO
import finkmoritz.conwaysgameoflifegame.config.ConfigDO

@Database(entities = arrayOf(ConfigDO::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun configDao(): ConfigDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDatabase::class.java, "App.db").allowMainThreadQueries() //TODO: replace, not recommended
                .build()
    })
}

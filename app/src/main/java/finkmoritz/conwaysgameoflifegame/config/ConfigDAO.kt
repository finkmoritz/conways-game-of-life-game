package finkmoritz.conwaysgameoflifegame.config

import androidx.room.*

@Dao
interface ConfigDAO {
    @Query("SELECT * FROM config WHERE id=0")
    fun load() : ConfigDO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg config : ConfigDO)

    @Query("DELETE FROM config")
    fun delete()
}

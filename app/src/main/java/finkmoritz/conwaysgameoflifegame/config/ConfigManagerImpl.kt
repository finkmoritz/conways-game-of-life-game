package finkmoritz.conwaysgameoflifegame.config

import android.content.Context
import finkmoritz.conwaysgameoflifegame.persistence.AppDatabase

class ConfigManagerImpl(private val context: Context) : ConfigManager {
    override fun save(configDO: ConfigDO) {
        if(configDO == null) {
            AppDatabase.getInstance(context).configDao().save(ConfigDO())
        } else {
            AppDatabase.getInstance(context).configDao().save(configDO)
        }
    }

    override fun load() : ConfigDO {
        var config = AppDatabase.getInstance(context).configDao().load()
        if(config == null) {
            config = ConfigDO()
        }
        return config
    }

    override fun delete() {
        AppDatabase.getInstance(context).configDao().delete()
    }
}
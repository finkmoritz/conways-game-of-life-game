package finkmoritz.conwaysgameoflifegame.config

interface ConfigManager {
    fun save(configDO: ConfigDO)
    fun load() : ConfigDO
    fun delete()
}
package finkmoritz.conwaysgameoflifegame.persistence

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import java.io.Serializable

class AppSharedPreferences(val context: Context) {

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun save(key: String, value: Serializable) {
        val editor = prefs.edit()
        editor.putString(key, Gson().toJson(value))
        editor.apply()
    }

    fun <T: Any> load(key: String, defaultValue: T) : T? {
        val value = prefs.getString(key,Gson().toJson(defaultValue))
        return Gson().fromJson(value,defaultValue::class.java)
    }
}
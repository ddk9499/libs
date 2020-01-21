package libs.toothpick.fileproperties

import android.content.Context
import android.util.Log
import toothpick.config.Module
import java.util.*

/**
 * Created at November 2019
 * @project libs
 * @author Dostonbek Kamalov (a.k.a @ddk9499)
 */

@Suppress("UNCHECKED_CAST")
class FilePropertiesModule(
    propertyFileName: String,
    context: Context
) : Module() {

    private val props = Properties()

    init {
        try {
            val hasFile = context.assets.list("")?.contains(propertyFileName) ?: false
            if (hasFile) {
                context.assets.open(propertyFileName).use { props.load(it) }
                saveProperties(props)
            } else {
                Log.d("FilePropertiesModule", "Properties file was not found")
            }
        } catch (e: Exception) {
            Log.e("FilePropertiesModule", "Error while loading properties", e)
        }
    }

    private fun saveProperties(properties: Properties) {
        val propertiesMapValues = properties.toMap() as Map<String, String>
        propertiesMapValues.forEach { (k: String, v: String) ->
            when {
                v.isInt -> saveProperty(k, v.toInt())
                v.isFloat -> saveProperty(k, v.toFloat())
                else -> saveProperty(k, v.quoted)
            }
        }
    }

    private inline fun <reified T> saveProperty(key: String, value: T) {
        Log.d("FilePropertiesModule", "saved value: $value with key: $key")
        bind(T::class.java).withName(key).toInstance(value)
    }
}

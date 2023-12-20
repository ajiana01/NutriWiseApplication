package ch2ps299.ajiananta.nutriwise.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun ageInMonths(dateString: String?): Int {
    if (dateString == null) {
        return -1 // Atau nilai default lain yang menandakan data tidak valid
    }

    return try {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val birthDate = ZonedDateTime.parse(dateString, formatter).toLocalDate()
        val currentDate = LocalDate.now()

        val period = Period.between(birthDate, currentDate)
        period.years * 12 + period.months
    } catch (e: Exception) {
        -1 // Gagal parsing tanggal
    }
}

fun toJson(data: Any): String {
    val gson = Gson()
    return gson.toJson(data)
}

fun reformatDate(dateStr: String, fromFormat: String, toFormat: String): String {
    val fromSimpleDateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
    val toSimpleDateFormat = SimpleDateFormat(toFormat, Locale.getDefault())

    return try {
        val date = fromSimpleDateFormat.parse(dateStr)
        if (date != null) toSimpleDateFormat.format(date) else ""
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}


object Endpoints {
    const val CHILD_URL: String = "https://y-rdgluxudda-et.a.run.app/"
    const val STUNTING_URL: String = "https://flask-api-model-rdgluxudda-et.a.run.app/"
}


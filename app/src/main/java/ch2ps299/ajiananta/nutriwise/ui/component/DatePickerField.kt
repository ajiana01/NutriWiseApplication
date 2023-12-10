package ch2ps299.ajiananta.nutriwise.ui.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_surfaceVariant
import java.util.Calendar
import java.util.Date

@Composable
fun DatePicker(labelText: String,
               context: Context){
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, day: Int ->
        date.value = "$day/$month/$year"
    }, year, month, day)

    Column {
        Text(text = labelText, fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = md_theme_light_primary)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .clickable(onClick = { datePickerDialog.show() })
                .fillMaxWidth()
                .border(0.8.dp, md_theme_light_surfaceVariant, shape = RoundedCornerShape(8.dp))

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Icon(Icons.Default.DateRange, contentDescription = "Date Picker", tint = md_theme_light_surfaceVariant)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = date.value,  fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = md_theme_light_primary)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DatePickerFieldPreview() {
    val contxt = LocalContext.current
    DatePicker(labelText = "Tanggal Lahir", context = contxt)
}
package ch2ps299.ajiananta.nutriwise.ui.screen.adddatachild

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.DatePicker
import ch2ps299.ajiananta.nutriwise.ui.component.InputNumberField
import ch2ps299.ajiananta.nutriwise.ui.component.InputTextField
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun AddDataChildScreen(
    modifier: Modifier = Modifier
) {
    var childName by remember { mutableStateOf(TextFieldValue()) }
    var weightText by remember { mutableStateOf(TextFieldValue()) }
    var heightText by remember { mutableStateOf(TextFieldValue()) }
    var circleText by remember { mutableStateOf(TextFieldValue()) }
    val contxt = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        TopBar(labelText = "Tambah Data Anak", onBackClick = { /*TODO*/ })
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                InputTextField(labelText = "Nama Anak ", text = childName , onTextChange = {childName = it} , placeholder = "Masukkan nama anak")
                Spacer(modifier = modifier.padding(8.dp))
                DatePicker(labelText = "Tanggal Lahir", context = contxt )
                Spacer(modifier = modifier.padding(8.dp))
                Text(text = "Data Kelahiran",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp,
                    color = md_theme_light_primary
                )
                Spacer(modifier = modifier.padding(4.dp))
                InputNumberField(labelText = "Berat Badan (kg)", text = weightText, onTextChange = { weightText = it} , placeholder = "Contoh: 3.5")
                Spacer(modifier = modifier.height(8.dp))
                InputNumberField(labelText = "Tinggi Badan (cm)", text = heightText, onTextChange = { heightText = it} , placeholder = "Contoh: 49.5")
                Spacer(modifier = modifier.height(8.dp))
                InputNumberField(labelText = "Lingkar Kepala (cm)", text = circleText, onTextChange = { circleText = it} , placeholder = "Contoh: 35.5")
                Spacer(modifier = modifier.height(16.dp))
            }
            ButtonComponent(text = "Simpan", onClick = {})
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddDataChildScreenPreview() {
    NutriWiseApplicationTheme {
        AddDataChildScreen()
    }
}
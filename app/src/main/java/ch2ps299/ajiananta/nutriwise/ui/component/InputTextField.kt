package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onSecondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_surfaceVariant

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    labelText: String,
    text: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    placeholder: String
) {
    Column {
        Text(text = labelText, fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = md_theme_light_primary)
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            textStyle = TextStyle(color = md_theme_light_onSecondaryContainer, fontSize = 14.sp, fontFamily = NunitoFontFamily, fontWeight = FontWeight.Medium),
            modifier = modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = placeholder, fontFamily = NunitoFontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = md_theme_light_primary,
                unfocusedBorderColor = md_theme_light_surfaceVariant,
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun InputTextFieldPreview() {
    NutriWiseApplicationTheme {
        var textValue by remember { mutableStateOf(TextFieldValue()) }
        InputTextField(
            text = textValue,
            labelText = "Nama Anak",
            onTextChange = { textValue = it },
            placeholder = "Masukan Nama Anak"
        )
    }
}

package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun ButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = md_theme_light_primary
        ),
        shape = RoundedCornerShape(8.dp)

    ) {
        Text(
            text = text,
            modifier = modifier.align(Alignment.CenterVertically),
            fontSize = 14.sp,
            fontFamily = NunitoFontFamily,
            fontWeight =  FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonComponentPreview() {
    NutriWiseApplicationTheme {
        ButtonComponent(
            text = "Stunting Cek",
            onClick = {}
        )
    }
}
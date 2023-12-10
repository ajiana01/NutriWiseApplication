package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun TopBar(
    labelText: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back Button", tint = md_theme_light_primary)
        }
        Spacer(modifier = modifier.width(8.dp))
        Text(text = labelText,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            color = md_theme_light_primary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview() {
    TopBar(
        labelText = "NutriWise",
        onBackClick = {}
    )
}
package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipComponent() {
    val chipData = listOf("Nasi", "Bubur", "Kentang","Ayam", "Ikan", "Telur", "Susu", "Tahu", "Tempe", "Sayur", "Buah")
    val selectedChips = remember { mutableStateMapOf<String, Boolean>() }

    chipData.forEach { chipName ->
        selectedChips[chipName] = selectedChips[chipName] ?: false
    }


    LazyRow {
        items(chipData) { chipName ->
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                onClick = { selectedChips[chipName] = !selectedChips.getValue(chipName) },
                label = {
                    Text(
                        text = chipName,
                        fontFamily = NunitoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = md_theme_light_onPrimaryContainer
                    )
                },
                selected = selectedChips[chipName] == true,
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = md_theme_light_primaryContainer,
                    selectedLabelColor = md_theme_light_primary,
                    containerColor = Color.White,
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = md_theme_light_outlineVariant
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

}

@Composable
@Preview(showBackground = true)
fun FilterChipComponentPreview() {
    FilterChipComponent()
}
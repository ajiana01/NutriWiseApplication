package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.data.WiseRepository
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primaryContainer
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.RecipesViewModel

@Composable
fun FilterChipComponent(viewModel: RecipesViewModel) {
    val chipData = listOf("Menu Utama", "Cemilan", "Ayam","Bubur", "Kentang", "Daging", "Pisang", "Telur", "Ubi", "Nasi", "Tempe", "Bayam","Kacang Hijau")
    val selectedChips = viewModel.selectedTags.value

    /*chipData.forEach { chipName ->
        selectedChips[chipName] = selectedChips[chipName] ?: false
    }*/

    LazyRow {
        items(chipData) { chipName ->
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                onClick = { viewModel.onTagSelected(chipName, !selectedChips.contains(chipName)) },
                label = {
                    Text(
                        text = chipName,
                        fontFamily = NunitoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = md_theme_light_onPrimaryContainer
                    )
                },
                selected = selectedChips.contains(chipName),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = md_theme_light_primaryContainer,
                    selectedLabelColor = md_theme_light_primary,
                    containerColor = Color.White,
                ),
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

}

@Composable
@Preview(showBackground = true)
fun FilterChipComponentPreview() {
    /*FilterChipComponent(
        viewModel = RecipesViewModel(WiseRepository(//TODO))
    )*/
}
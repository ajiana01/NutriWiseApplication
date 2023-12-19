package ch2ps299.ajiananta.nutriwise.ui.screen.nutritionresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_error
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onSecondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun NutritionResultScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(labelText = "Hasil Kebutuhan Gizi", onBackClick = { navController.popBackStack() })
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            NutritionResultItem(nutrition = "nutritionNeed", isEnough = false)
        }
    }
}

@Composable
fun NutritionResultItem(
    nutrition: String,
    isEnough: Boolean,
) {
    Column {
        Image(painterResource(if (isEnough) R.drawable.nutrisi_cukup else R.drawable.nutrisi_kurang) , contentDescription = "Nutrition Image",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = if (isEnough) "Nutrisi Harian Si Kecil Terpenuhi!" else "Nutrisi Harian Si Kecil Belum Lengkap!",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 18.sp,
            color = if (isEnough) md_theme_light_primary else md_theme_light_error,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = if (isEnough) "Halo Parent! Nutrisi si kecil terpenuhi dengan baik hari ini. Ingat, pola makan seimbang adalah kunci tumbuh kembang optimal. Yuk, cek fitur olahan masakan untuk membantu kebutuhan nutrisi harian mereka esok hari!" else "Nutrisi si kecil belum lengkap hari ini:\n $nutrition\n\nCek Rekomendasi Makanan dibawah ini sesuai nutrisi yang dibutuhkan si Kecil.",
            fontWeight = FontWeight.Medium,
            fontFamily = NunitoFontFamily,
            fontSize = 11.sp,
            color = md_theme_light_onSecondaryContainer,
            style = TextStyle(textAlign = if (isEnough) TextAlign.Center else TextAlign.Start),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun RecommendFoodByNutrition() {

}

@Composable
@Preview(showBackground = true)
fun NutritionResultScreenPreview() {
    NutritionResultScreen(
        navController = NavController(LocalContext.current)
    )
}

@Composable
@Preview(showBackground = true)
fun NutritionResultScreenPreview2() {
    NutritionResultScreen(
        navController = NavController(LocalContext.current)
    )
}
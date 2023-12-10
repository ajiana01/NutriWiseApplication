package ch2ps299.ajiananta.nutriwise.ui.screen.stuntingcheckresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.FoodItem
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_error
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onSecondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun StuntingCheckResultScreen(
    onClickHome: () -> Unit,
    stunting: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Column{
                Image(
                    painterResource(if (stunting) R.drawable.hasil_stunting else R.drawable.onboarding_1),
                    contentDescription = "Stunting Check Result",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop

                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = if (stunting) "Si Kecil Terindikasi Stunting!" else "Si Kecil Tumbuh Dengan Baik!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 18.sp,
                    color = if (stunting) md_theme_light_error else md_theme_light_primary,
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = if (stunting) "Halo Parent! Nutrisi yang baik adalah kunci pertumbuhan. Yuk, perhatikan asupan gizi si kecil dengan menu seimbang dan bergizi." else "Halo Parent! Kabar baik, pertumbuhan si kecil sesuai dengan tahapan usianya. Untuk membantu Anda terus memberikan asupan gizi yang lengkap dan seimbang. Yuk, cek Fitur Olahan Masakan kami untuk ide menu sehat dan bergizi!",
                    fontWeight = FontWeight.Medium,
                    fontFamily = NunitoFontFamily,
                    fontSize = 11.sp,
                    color = md_theme_light_onSecondaryContainer,
                    maxLines = 4,
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                RecommendFoodAdditional()
            }
            ButtonComponent(text = "Kembali ke Beranda", onClick = onClickHome)
        }
    }
}

@Composable
fun RecommendFoodAdditional() {
    Text(text = "Rekomendasi Makanan",
        fontWeight = FontWeight.Bold,
        fontFamily = NunitoFontFamily,
        fontSize = 16.sp,
        color = md_theme_light_primary
    )
    Spacer(modifier = Modifier.height(16.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        //TODO Change Item Recommend Food
        items(2) {
            FoodItem(image = R.drawable.food_image, titlefood = "Nasi Goreng", tag = "Nasi" , time = "5 Menit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StuntingCheckResultScreenPreview() {
    StuntingCheckResultScreen(onClickHome = {}, stunting = false)
}

@Composable
@Preview(showBackground = true)
fun StuntingCheckResultScreenPreview2() {
    StuntingCheckResultScreen(onClickHome = {}, stunting = true)
}
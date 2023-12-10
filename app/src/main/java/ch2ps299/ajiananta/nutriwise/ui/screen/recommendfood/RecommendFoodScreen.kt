package ch2ps299.ajiananta.nutriwise.ui.screen.recommendfood

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.screen.stuntingcheckresult.RecommendFoodAdditional
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer

@Composable
fun RecommendFoodScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        RecommendFoodBanner(image = R.drawable.banner_recommend)
        Spacer(modifier = Modifier.height(16.dp))
        NutritionChecker()
        Spacer(modifier = Modifier.height(16.dp))
        RecommendFoodAdditional()
    }

}

@Composable
fun RecommendFoodBanner(
    image: Int,
) {
    Box(modifier = Modifier
        .height(128.dp)
        .fillMaxWidth()
        .background(color = md_theme_light_secondaryContainer, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painterResource(image),
            contentDescription = "Banner Rekomendasi Makanan",
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = md_theme_light_secondaryContainer,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(250.dp)
                .padding(16.dp)
        ) {
            Text(text = "Selalu Penuhi Kebutuhan Nutrisi si Balita",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 16.sp,
                color = md_theme_light_primary,
                maxLines = 2
            )
            Text(text = "dukung tumbuh kembangnya yang optimal dan cegah stunting.",
                fontWeight = FontWeight.Medium,
                fontFamily = NunitoFontFamily,
                fontSize = 11.sp,
                color = md_theme_light_onPrimaryContainer,
                maxLines = 2
            )
        }
    }
}

@Composable
fun NutritionChecker() {
    Text(text = "Makanan Harian Anak",
        fontWeight = FontWeight.Bold,
        fontFamily = NunitoFontFamily,
        fontSize = 16.sp,
        color = md_theme_light_primary)
}


@Composable
@Preview(showBackground = true)
fun PreviewRecommendFoodScreen() {
    RecommendFoodScreen()
}
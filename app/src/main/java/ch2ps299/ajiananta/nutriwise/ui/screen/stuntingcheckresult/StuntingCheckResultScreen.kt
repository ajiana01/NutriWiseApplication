package ch2ps299.ajiananta.nutriwise.ui.screen.stuntingcheckresult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.di.RetrofitClient
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.FoodItem
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_error
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onSecondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.StuntingCheckResultViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory

@Composable
fun StuntingCheckResultScreen(
    viewModel: StuntingCheckResultViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
    navController: NavController,
    navToDetail: (Long) -> Unit,
    childId: String,
    isStunting: Boolean
) {
    when (val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value) {
        is UiState.Loading -> {
            viewModel.getRandomRecipes()
        }
        is UiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                StuntingCheckResultContent(
                    onClickHome = { navController.navigate("home/$childId") },
                    stunting = isStunting ,
                    recipesRandom = uiState.data,
                    navToDetail = navToDetail
                )
            }

        }
        is UiState.Error -> {}
    }
}

@Composable
fun StuntingCheckResultContent(
    onClickHome: () -> Unit,
    stunting: Boolean,
    recipesRandom: List<Recipe>,
    navToDetail: (Long) -> Unit
){
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            RecommendFoodAdditional(recipesRandom, navToDetail)
        }
        ButtonComponent(text = "Kembali ke Beranda", onClick = onClickHome)
    }
}

@Composable
fun RecommendFoodAdditional(
    randomRecipe: List<Recipe>,
    navToDetail: (Long) -> Unit
) {
    Column (
        modifier = Modifier
            .height(200.dp)

    ) {
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
            items(randomRecipe) { recipe ->
                FoodItem(
                    image = recipe.image,
                    titlefood = recipe.name,
                    tag = recipe.tag[0],
                    modifier = Modifier.clickable { navToDetail(recipe.id) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StuntingCheckResultScreenPreview() {
    StuntingCheckResultScreen(
        navController = NavController(
            context = LocalContext.current
        ),
        navToDetail = {},
        childId = "1",
        isStunting = true
    )
}
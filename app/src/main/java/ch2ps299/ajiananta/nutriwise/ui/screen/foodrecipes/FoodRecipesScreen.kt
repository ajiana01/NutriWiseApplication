package ch2ps299.ajiananta.nutriwise.ui.screen.foodrecipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.component.FilterChipComponent
import ch2ps299.ajiananta.nutriwise.ui.component.FoodItem
import ch2ps299.ajiananta.nutriwise.ui.component.SearchBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun FoodRecipesScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            FoodRecipesContent()
        }
    }
}

@Composable
fun FoodRecipesContent() {
    var text: String by remember { mutableStateOf("") }
    Column {
        Text(text = "Rekomendasi Makanan",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            color = md_theme_light_primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(query = text, onQueryChange = {text = it} )
        Spacer(modifier = Modifier.height(8.dp))
        FilterChipComponent()
        Spacer(modifier = Modifier.height(8.dp))
        FoodRecipesList()
    }

}

@Composable
fun FoodRecipesList() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 16.dp)
    ) {
        //TODO Change Recipe List
        items(8) {
            FoodItem(image = R.drawable.food_image, titlefood = "Nasi Goreng", tag = "Nasi" , time = "5 Menit")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FoodRecipesScreenPreview() {
    FoodRecipesScreen(
        navController = NavController(LocalContext.current)
    )
}
package ch2ps299.ajiananta.nutriwise.ui.screen.foodrecipes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.model.Recipe
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.FilterChipComponent
import ch2ps299.ajiananta.nutriwise.ui.component.FoodItem
import ch2ps299.ajiananta.nutriwise.ui.component.SearchBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.RecipesViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory

@Composable
fun FoodRecipesScreen(
    viewModel: RecipesViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository()
        )
    ),
    navToDetail: (Long) -> Unit
) {
    val querySearch = viewModel.query.value
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllRecipes()
                }
                is UiState.Success -> {
                    FoodRecipesContent(
                        searchRecipes = querySearch,
                        queryChange = viewModel::searchRecipes,
                        viewModels = viewModel
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodRecipesList(
                        recipe = uiState.data,
                        navToDetail = navToDetail
                    )
                }
                is UiState.Error -> {
                    Toast.makeText(
                        LocalContext.current,
                        "Resep Tidak Dapat Ditemukan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

@Composable
fun FoodRecipesContent(
    searchRecipes: String,
    queryChange: (String) -> Unit,
    viewModels: RecipesViewModel
) {
    Column {
        Text(text = "Rekomendasi Makanan",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            color = md_theme_light_primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(query = searchRecipes, onQueryChange = queryChange)
        Spacer(modifier = Modifier.height(8.dp))
        FilterChipComponent(viewModels)
    }

}

@Composable
fun FoodRecipesList(
    recipe: List<Recipe>,
    navToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 16.dp),
    ){
        items(recipe) {
            FoodItem(image = it.image, titlefood = it.name, tag = it.tag[0],
                modifier = Modifier.clickable { navToDetail(it.id) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FoodRecipesScreenPreview() {
    FoodRecipesScreen(
        navToDetail = {}
    )
}
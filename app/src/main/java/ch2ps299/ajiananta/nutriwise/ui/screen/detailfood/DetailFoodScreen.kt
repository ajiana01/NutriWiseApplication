package ch2ps299.ajiananta.nutriwise.ui.screen.detailfood

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primaryContainer
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.DetailFoodViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory


@Composable
fun DetailFoodScreen(
    id: Long,
    viewModel: DetailFoodViewModel = viewModel(
        factory = ViewModelFactory(DataInjection.provideRepository())),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getRecipeById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                Column {
                    TopBar(labelText = "Detail Masakan", onBackClick = { navigateBack() })
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ){
                        DetailFoodContent(
                            imageFood = data.image,
                            titleFood = data.name,
                            type = data.tag[0],
                            porsi = data.portion,
                            forAge = data.age,
                            ingredients = data.ingredients,
                            howToCook = data.steps,
                            nutrition = data.nutrition,
                        )
                    }
                }
            }
            is UiState.Error -> {
                // TODO
            }
        }
    }
}

@Composable
fun DetailFoodContent(
    imageFood: Int,
    titleFood: String,
    type: String,
    porsi: Int,
    forAge: String,
    ingredients: String,
    howToCook: String,
    nutrition: List<Pair<String, String>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(imageFood), contentDescription = "Food Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier  = Modifier
                .padding(16.dp)
        ) {
            Text(text = titleFood,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 16.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .border(1.dp,md_theme_light_outlineVariant, shape = RoundedCornerShape(8.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painterResource(R.drawable.ic_jenis_makanan), contentDescription = "Calories Icon", tint = md_theme_light_outlineVariant, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Jenis",
                                fontWeight = FontWeight.Bold,
                                fontFamily = NunitoFontFamily,
                                fontSize = 11.sp,
                                color = md_theme_light_outlineVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = type,
                            fontWeight = FontWeight.Bold,
                            fontFamily = NunitoFontFamily,
                            fontSize = 12.sp,
                            color = md_theme_light_onPrimaryContainer
                        )
                    }
                    VerticalDivider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )  {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painterResource(R.drawable.ic_porsi), contentDescription = "Duration Icon", tint = md_theme_light_outlineVariant, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Porsi",
                                fontWeight = FontWeight.Bold,
                                fontFamily = NunitoFontFamily,
                                fontSize = 11.sp,
                                color = md_theme_light_outlineVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "$porsi porsi makan",
                            fontWeight = FontWeight.Bold,
                            fontFamily = NunitoFontFamily,
                            fontSize = 12.sp,
                            color = md_theme_light_onPrimaryContainer
                        )
                    }
                    VerticalDivider(modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )  {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.Face, contentDescription = "Age Icon", tint = md_theme_light_outlineVariant, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Usia",
                                fontWeight = FontWeight.Bold,
                                fontFamily = NunitoFontFamily,
                                fontSize = 11.sp,
                                color = md_theme_light_outlineVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = forAge,
                            fontWeight = FontWeight.Bold,
                            fontFamily = NunitoFontFamily,
                            fontSize = 12.sp,
                            color = md_theme_light_onPrimaryContainer
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Bahan - bahan",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = ingredients,
                fontWeight = FontWeight.Medium,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Langkah Penyajian",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = howToCook,
                fontWeight = FontWeight.Medium,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .height(320.dp)
            ) {
                Text(text = "Informasi Nilai Gizi per Porsi",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 14.sp,
                    color = md_theme_light_onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                DynamicTable(data = nutrition)
            }
        }
    }
}

@Composable
fun DynamicTable(data: List<Pair<String, String>>) {
    LazyColumn {
        this.items(data) { item ->
            TableRow(item = item, isOdd = data.indexOf(item) % 2 != 0)
        }
    }
}

@Composable
fun TableRow(item: Pair<String, String>, isOdd: Boolean) {
    val backgroundColor = if (isOdd) md_theme_light_primaryContainer else Color.Transparent
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Box(modifier = Modifier
            .border(1.dp, md_theme_light_outlineVariant)
            .weight(1f)
        )   {
            Text(
                text = item.first,
                modifier = Modifier
                    .padding(4.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = NunitoFontFamily,
                fontSize = 12.sp,
                color = md_theme_light_onPrimaryContainer
            )
        }
        Box(modifier = Modifier
            .border(1.dp, md_theme_light_outlineVariant)
            .weight(1f)
        ) {
            Text(
                text = item.second,
                modifier = Modifier
                    .padding(4.dp),
                fontWeight = FontWeight.Medium,
                fontFamily = NunitoFontFamily,
                fontSize = 12.sp,
                color = md_theme_light_onPrimaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailFoodScreenPreview() {
    val data = listOf(
        "Energi" to "200 kkal",
        "Kalori" to "50 gr",
        "Kewarasan" to "Hilang",
        "Kesadaran" to "Lumpuh")

    Column {
        DetailFoodContent(
            imageFood = R.drawable.food_image,
            titleFood = "Nasi Mak Limah Biadab",
            type = "150 kkal",
            porsi = 2,
            forAge = "10 bulan",
            ingredients = "banyak",
            howToCook = "masak",
            nutrition = data
        )
    }
}

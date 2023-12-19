package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primaryContainer

@Composable
fun FoodItem(
    image: Int,
    titlefood: String,
    tag: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(bottom = 4.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
    ){
        BoxWithConstraints {
            val imageHeight = maxHeight / 2

            Column {
                Image(
                    painter = painterResource(image),
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = titlefood,
                        fontWeight = FontWeight.Bold,
                        fontFamily = NunitoFontFamily,
                        fontSize = 12.sp,
                        color = md_theme_light_onPrimaryContainer,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = md_theme_light_primaryContainer,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(vertical = 2.dp, horizontal = 8.dp)
                    ) {
                        Text(text = tag,
                            fontWeight = FontWeight.Medium,
                            fontFamily = NunitoFontFamily,
                            fontSize = 11.sp,
                            color = md_theme_light_onPrimaryContainer
                        )
                    }
                }
            }


        }

    }
}



@Composable
@Preview(showBackground = true)
fun FoodItemPreview() {
    FoodItem(
        image = R.drawable.food_image,
        titlefood = "Nasi Putih",
        tag = "Nasi",
    )
}
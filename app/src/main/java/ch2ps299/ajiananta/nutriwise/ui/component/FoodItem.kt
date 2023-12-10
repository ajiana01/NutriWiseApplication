package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primaryContainer

@Composable
fun FoodItem(
    image: Int,
    titlefood: String,
    tag: String,
    time: String,
) {
    Box(modifier = Modifier
        .shadow(4.dp, RoundedCornerShape(8.dp))
    ){

        Box(
            modifier = Modifier
                .height(156.dp)
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        )
        {
            Column {
                Image(
                    painter = painterResource(image),
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = titlefood,
                        fontWeight = FontWeight.Bold,
                        fontFamily = NunitoFontFamily,
                        fontSize = 12.sp,
                        color = md_theme_light_onPrimaryContainer
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
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painterResource(R.drawable.ic_alarm), contentDescription = "Time", Modifier.size(20.dp), tint = md_theme_light_outlineVariant)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = time,
                            fontWeight = FontWeight.Bold,
                            fontFamily = NunitoFontFamily,
                            fontSize = 11.sp,
                            color = md_theme_light_outlineVariant
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
        time = "10:00"
    )
}
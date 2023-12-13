package ch2ps299.ajiananta.nutriwise.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.InputNumberField
import ch2ps299.ajiananta.nutriwise.ui.navbar.Screen
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        HomeProfile(
            userName = "Aji Ananta",
            historyClick = { navController.navigate(route = Screen.HistoryScreen.route) },
            changeProfileClick = {navController.navigate(route = Screen.ProfileChangeChild.route)}
        )
        StuntingCheck(
            stuntingClick = {navController.navigate(route = Screen.StuntingResult.route)}
        )
    }
}
@Composable
fun HomeProfile(
    modifier: Modifier = Modifier,
    userName: String,
    historyClick: () -> Unit = {},
    changeProfileClick: () -> Unit = {}
) {

    Box{
        Image(
            painter = painterResource(id = R.drawable.background_home),
            contentDescription = "Backgorund Profile",
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp),
            contentScale = ContentScale.FillWidth
        )
        Column (
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Row {
                Text(
                    text = "Halo,",
                    fontWeight = FontWeight.Medium,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp

                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = "$userName!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Box(
                modifier = modifier
                    .background(
                        color = md_theme_light_secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = "Nama Anak",
                                fontWeight = FontWeight.Bold,
                                fontFamily = NunitoFontFamily,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = modifier.height(8.dp))
                            Text(
                                text = "1 Bulan",
                                fontWeight = FontWeight.Medium,
                                fontFamily = NunitoFontFamily,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Button(
                                onClick = historyClick,
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = md_theme_light_primary
                                ),
                                shape = RoundedCornerShape(4.dp)
                                
                            ) {
                                Text(
                                    text = "Cek Riwayat",
                                    modifier = modifier.align(Alignment.CenterVertically),
                                    fontSize = 12.sp,
                                    fontFamily = NunitoFontFamily,
                                    fontWeight =  FontWeight.Bold
                                )
                            }
                        }
                        TextButton(onClick = changeProfileClick) {
                            Text(
                                text = "Ganti Profil Anak",
                                modifier = modifier.align(Alignment.CenterVertically),
                                fontSize = 12.sp,
                                fontFamily = NunitoFontFamily,
                                fontWeight =  FontWeight.Bold,
                                color = md_theme_light_secondary
                            )
                        }
                    }

                }

            }
        }
    }
}

@Composable
fun StuntingCheck(
    modifier: Modifier = Modifier,
    stuntingClick: () -> Unit = {},
) {
    var weightText by remember { mutableStateOf(TextFieldValue()) }
    var heightText by remember { mutableStateOf(TextFieldValue()) }
    var circleText by remember { mutableStateOf(TextFieldValue()) }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Pengecekan Stunting",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            color = md_theme_light_primary)
        Spacer(modifier = modifier.height(8.dp))
        InputNumberField(labelText = "Berat Badan (kg)", text = weightText, onTextChange = { weightText = it} , placeholder = "Contoh: 3.5")
        Spacer(modifier = modifier.height(8.dp))
        InputNumberField(labelText = "Tinggi Badan (cm)", text = heightText, onTextChange = { heightText = it} , placeholder = "Contoh: 49.5")
        Spacer(modifier = modifier.height(8.dp))
        InputNumberField(labelText = "Lingkar Kepala (cm)", text = circleText, onTextChange = { circleText = it} , placeholder = "Contoh: 35.5")
        Spacer(modifier = modifier.height(16.dp))
        ButtonComponent(text = "Stunting Cek", onClick = {stuntingClick()})
    }
}

@Composable
@Preview(showBackground = true)
fun HomeProfilePreview() {
    NutriWiseApplicationTheme {
        HomeScreen(
            navController = NavController(
                context = androidx.compose.ui.platform.LocalContext.current
            )
        )
    }
}
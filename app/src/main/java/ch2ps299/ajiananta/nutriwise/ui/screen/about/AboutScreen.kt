package ch2ps299.ajiananta.nutriwise.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer

@Composable
fun AboutScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(labelText = "Tentang Kami", onBackClick = { navController.popBackStack() })
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Wise Team",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 20.sp,
                color = md_theme_light_onPrimaryContainer,
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            ProfileTeamItem(
                image = R.drawable.ajik,
                nameTeam = "Aji Ananta",
                position = "as an Android Developer"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTeamItem(
                image = R.drawable.attariq,
                nameTeam = "Attariq Fadly Yudi Saputra",
                position = "as a Cloud Computing Engineer"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTeamItem(
                image = R.drawable.ryu,
                nameTeam = "Ryu Alvano",
                position = "as a Cloud Computing Engineer"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTeamItem(
                image = R.drawable.emy,
                nameTeam = "Hudaemy Achmad Akbar",
                position = "as a Machine Learning Developer"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTeamItem(
                image = R.drawable.dira,
                nameTeam = "Dira Riana Agustina",
                position = "as a Machine Learning Developer"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProfileTeamItem(
                image = R.drawable.junita,
                nameTeam = "Junita Cintia Dewi Br Pinem",
                position = "as a Machine Learning Developer"
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "\"Together with Nutri Wise, Building a Healthier Indonesia.\"",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 20.sp,
                color = md_theme_light_onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileTeamItem(
    image: Int,
    nameTeam: String,
    position: String,
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Photo Profile",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = nameTeam,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 16.sp,
                color = md_theme_light_onPrimaryContainer
            )
            Text(text = position,
                fontWeight = FontWeight.SemiBold,
                fontFamily = NunitoFontFamily,
                fontSize = 12.sp,
                color = md_theme_light_onPrimaryContainer
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
    AboutScreen(
        navController = NavController(LocalContext.current)
    )
}
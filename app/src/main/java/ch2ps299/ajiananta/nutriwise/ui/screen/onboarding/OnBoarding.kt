package ch2ps299.ajiananta.nutriwise.ui.screen.onboarding

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onSecondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary


@Composable
fun OnBoardingAll(
    navController: NavController
){
    val (currentScreen, setCurrentScreen) = remember { mutableIntStateOf(0) }
    val screens = listOf(OnBoardingScreen1(navController), OnBoardingScreen2(navController), OnBoardingScreen3(navController))

   /* HorizontalPager(count = screens.size) { page ->
        screens[page]
    }
    Column {
       
    }*/
}

@Composable
fun OnBoardingScreen1(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        OnBoarding(
            onSkipClick = { /*TODO*/ },
            onNextClick = { /*TODO*/ },
            image = R.drawable.onboarding_1,
            title = "Pendeteksian Stunting",
            text = "Input data pertumbuhan anak Anda, kami akan berikan analisis perkembangannya.",
            isActive = 0
        )
    }
}

@Composable
fun OnBoardingScreen2(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        OnBoarding(
            onSkipClick = { /*TODO*/ },
            onNextClick = { /*TODO*/ },
            image = R.drawable.onboarding_2,
            title = "Rekomendasi Makanan",
            text = "Dapatkan rekomendasi makanan sesuai kebutuhan nutrisi anak.",
            isActive = 1
        )
    }
}

@Composable
fun OnBoardingScreen3(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        OnBoarding(
            onSkipClick = { /*TODO*/ },
            onNextClick = { /*TODO*/ },
            image = R.drawable.onboarding_3,
            title = "Resep Masakan",
            text = "Dapatkan resep makanan untuk diolah menjadi makanan si kecil.",
            isActive = 2
        )
    }
}

@Composable
fun OnBoarding(
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    image: Int,
    title: String,
    text: String,
    isActive: Int,
    modifier: Modifier = Modifier
) {
        Column(verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)) {
            TopBar(onSkipClick = onSkipClick)
            OnBoardingContent(image = image, title = title, text = text)
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                IndicatorOnBoarding(activeIndex = isActive)
                OnBoardingButtom(onNextClick = onNextClick)
            }
        }
}
@Composable
fun TopBar(
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxWidth()) {

        TextButton(
            onClick = onSkipClick,
            modifier = modifier.align(Alignment.CenterEnd)
        ) {
            Text(text = stringResource(R.string.skip_label),
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    image: Int,
    title: String,
    text: String,
) {
    Column {
        Image(painter = (painterResource(image)),
            contentDescription = "OnBoarding Image",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth())
        Spacer(modifier = Modifier.height(90.dp))
        Text(text = title,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = md_theme_light_onSecondaryContainer,)
        Spacer(modifier = modifier.height(8.dp))
        Text(text = text,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = md_theme_light_onSecondaryContainer,)
    }
}

@Composable
fun OnBoardingButtom(
    onNextClick: () -> Unit
) {
    FloatingActionButton(onClick = onNextClick,
        content = { Icon(Icons.Default.ArrowForward, contentDescription = "Next") })
}

@Composable
fun IndicatorOnBoarding(
    modifier: Modifier = Modifier,
    activeIndex: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        for (i in 0 until 3) {
            Box(
                modifier = modifier
                    .width(40.dp)
                    .height(10.dp)
                    .background(
                        if (i == activeIndex) md_theme_light_primary else md_theme_light_outlineVariant,
                        shape = RoundedCornerShape(5.dp)
                    )
            )
            Spacer(modifier = modifier.width(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnBoarding() {
    NutriWiseApplicationTheme {
        OnBoarding(
            onSkipClick = {},
            onNextClick = {},
            image = R.drawable.onboarding_1,
            title = "NutriWise",
            text = "NutriWise adalah aplikasi yang dapat membantu anda untuk menghitung kebutuhan kalori anda",
            isActive = 0
        )
    }
}
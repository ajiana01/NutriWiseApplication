package ch2ps299.ajiananta.nutriwise.ui.screen.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.ui.navbar.Screen
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    val isTimeOver = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        isTimeOver.value = true
    }
    if (!isTimeOver.value) {
        Box(modifier = Modifier.fillMaxSize().background(md_theme_light_primary), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.logo_without_bg), contentDescription = "Logo")
        }
    } else {
        navController.navigate(Screen.LoginScreen.route) {
            popUpTo("splash_screen") { inclusive = true }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}

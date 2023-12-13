package ch2ps299.ajiananta.nutriwise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ch2ps299.ajiananta.nutriwise.ui.navbar.NavbarItem
import ch2ps299.ajiananta.nutriwise.ui.navbar.Screen
import ch2ps299.ajiananta.nutriwise.ui.screen.about.AboutScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.adddatachild.AddDataChildScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.childdata.ChildDataScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.foodrecipes.FoodRecipesScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.history.HistoryScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.home.HomeScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.nutritionresult.NutritionResultScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.profile.ProfileScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.recommendfood.RecommendFoodScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.stuntingcheckresult.StuntingCheckResultScreen
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary

@Composable
fun shouldHideBottomBar(currentRoute: String?): Boolean {
    val hideBottomBarRoutes = setOf("stunting_result", "nutrition_result")
    if (currentRoute in hideBottomBarRoutes) return true
    return currentRoute?.startsWith("food_recipes/") == true
}

@Composable
fun NutriWiseApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (!shouldHideBottomBar(currentRoute)) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeMenu.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {

            }
            composable(Screen.OnBoardingScreen.route) {

            }
            composable(Screen.HomeMenu.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.ProfileChangeChild.route) {
                ChildDataScreen(navController = navController)
            }
            composable(Screen.AddDataChild.route) {
                AddDataChildScreen(navController = navController)
            }
            composable(Screen.HistoryScreen.route) {
                HistoryScreen(navController = navController)
            }
            composable(Screen.StuntingResult.route){
                StuntingCheckResultScreen(navController)
            }
            composable(Screen.Nutrition.route){
                RecommendFoodScreen(navController)
            }
            composable(Screen.NutritionResult.route){
                NutritionResultScreen(navController)
            }
            composable(Screen.FoodRecipes.route) {
                FoodRecipesScreen(navController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }
            composable(Screen.About.route) {
                AboutScreen(navController)
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column {
        Divider(thickness = 1.dp, color = md_theme_light_outlineVariant)
        NavigationBar(
            modifier = modifier.height(56.dp),
            contentColor = md_theme_light_outlineVariant,
            containerColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val navigationItem = listOf(
                NavbarItem(
                    icon = {isSelected ->
                        Icon(
                            painter = painterResource(R.drawable.ic_foodbank),
                            contentDescription = "icon navbar",
                            modifier = Modifier.size(32.dp),
                            tint = if (isSelected) md_theme_light_primary else md_theme_light_outlineVariant
                        )
                    },
                    screen = Screen.FoodRecipes
                ),
                NavbarItem(
                    icon = {isSelected ->
                        Icon(
                            painter = painterResource(R.drawable.ic_nutrition),
                            contentDescription = "icon navbar",
                            modifier = Modifier.size(32.dp),
                            tint = if (isSelected) md_theme_light_primary else md_theme_light_outlineVariant
                        )
                    },
                    screen = Screen.Nutrition
                ),
                NavbarItem(
                    icon = {isSelected ->
                        Icon(
                            painter = painterResource(R.drawable.ic_child),
                            contentDescription = "icon navbar",
                            modifier = Modifier.size(32.dp),
                            tint = if (isSelected) md_theme_light_primary else md_theme_light_outlineVariant
                        )
                    },
                    screen = Screen.HomeMenu
                ),
                NavbarItem(
                    icon = {isSelected ->
                        Icon(
                            painter = painterResource(R.drawable.ic_profile),
                            contentDescription = "icon navbar",
                            modifier = Modifier.size(32.dp),
                            tint = if (isSelected) md_theme_light_primary else md_theme_light_outlineVariant
                        )
                    },
                    screen = Screen.Profile
                )
            )
            navigationItem.map { item ->
                val isSelected = currentRoute == item.screen.route
                NavigationBarItem(
                    icon = {
                        item.icon(isSelected)
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomBarPreview() {
    NutriWiseApplicationTheme {
        val navController: NavHostController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomBar(navController = navController)
        }
    }
}
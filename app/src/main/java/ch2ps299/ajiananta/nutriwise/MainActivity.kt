package ch2ps299.ajiananta.nutriwise

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ch2ps299.ajiananta.nutriwise.data.GoogleAuthClient
import ch2ps299.ajiananta.nutriwise.ui.navbar.NavbarItem
import ch2ps299.ajiananta.nutriwise.ui.navbar.Screen
import ch2ps299.ajiananta.nutriwise.ui.screen.about.AboutScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.adddatachild.AddDataChildScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.childdata.ChildDataScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.detailfood.DetailFoodScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.foodrecipes.FoodRecipesScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.history.HistoryScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.home.HomeScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.login.LoginScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.nutritionresult.NutritionResultScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.profile.ProfileScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.recommendfood.RecommendFoodScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.splashscreen.SplashScreen
import ch2ps299.ajiananta.nutriwise.ui.screen.stuntingcheckresult.StuntingCheckResultScreen
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val googleAuthClient by lazy {
        GoogleAuthClient(
            applicationContext,
            Identity.getSignInClient(applicationContext)
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriWiseApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    Scaffold(
                        bottomBar = {
                            if (!shouldHideBottomBar(currentRoute)) {
                                BottomBar(navController)
                            }
                        }
                    ) {innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.SplashScreen.route,
                            modifier = Modifier.padding(innerPadding),
                        ) {
                            composable(Screen.SplashScreen.route) {
                                SplashScreen(navController = navController)
                            }
                            composable(Screen.OnBoardingScreen.route) {

                            }
                            composable(Screen.LoginScreen.route) {
                                val viewModel = viewModel<LoginViewModel>()
                                val state by viewModel.signInState.collectAsStateWithLifecycle()

                                LaunchedEffect(key1 = Unit) {
                                    if (googleAuthClient.getSignedInUser() != null) {
                                        navController.navigate(Screen.HomeMenu.route)
                                    }
                                }

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult =
                                                    googleAuthClient.signInWithIntent(
                                                        intent = result.data ?: return@launch
                                                    )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )
                                LaunchedEffect(key1 = state.isSignIsSuccessful) {
                                    if (state.isSignIsSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Login Berhasil",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        navController.navigate(Screen.HomeMenu.route)
                                        viewModel.resetState()
                                    }
                                }
                                LoginScreen(state = state,
                                    onSignIn = {
                                        lifecycleScope.launch {
                                            val signInIntentSender =
                                                googleAuthClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch,
                                                ).build()
                                            )
                                        }
                                    }
                                )
                            }
                            composable(Screen.HomeMenu.route) { backStackEntry ->
                                val childId = backStackEntry.arguments?.getString("childId") ?: ""
                                HomeScreen(navController = navController,userData = googleAuthClient.getSignedInUser() ?: return@composable, childIdArg = childId)
                            }
                            composable("home_screen/{childId}") { backStackEntry ->
                                val childId = backStackEntry.arguments?.getString("childId") ?: ""
                                HomeScreen(navController = navController, userData = googleAuthClient.getSignedInUser() ?: return@composable, childIdArg = childId)
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
                            composable(
                                Screen.StuntingResult.route,
                                arguments = listOf(
                                    navArgument("childId") { type = NavType.StringType },
                                    navArgument("stunting_status") { type = NavType.StringType }),
                                ){
                                val childId = it.arguments?.getString("childId") ?: ""
                                val stuntingStatus = it.arguments?.getString("stunting_status")?.toBoolean() ?: false
                                StuntingCheckResultScreen(
                                    navController=navController,
                                    navToDetail = {id ->
                                    navController.navigate(Screen.DetailRecipe.createRoute(id))
                                    },
                                    childId = childId,
                                    isStunting = stuntingStatus)
                            }
                            composable(Screen.Nutrition.route){
                                RecommendFoodScreen(navController)
                            }
                            composable(Screen.NutritionResult.route){
                                NutritionResultScreen(navController)
                            }
                            composable(Screen.FoodRecipes.route) {
                                FoodRecipesScreen(navToDetail = {id ->
                                    navController.navigate(Screen.DetailRecipe.createRoute(id))
                                })
                            }
                            composable(
                                Screen.DetailRecipe.route,
                                arguments = listOf(navArgument("id") { type = NavType.LongType }),
                            ) {
                                val id = it.arguments?.getLong("id") ?: -1L
                                DetailFoodScreen(id = id, navigateBack = {
                                    navController.popBackStack()
                                })
                            }
                            composable(Screen.Profile.route) {
                                ProfileScreen(
                                    navController = navController,
                                    userData = googleAuthClient.getSignedInUser() ?: return@composable,
                                    onClickLogout = {
                                        lifecycleScope.launch {
                                            googleAuthClient.signOut()
                                            navController.navigate(Screen.LoginScreen.route) {
                                                // Bersihkan seluruh stack navigasi
                                                popUpTo(navController.graph.startDestinationId) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                            composable(Screen.About.route) {
                                AboutScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun shouldHideBottomBar(currentRoute: String?): Boolean {
    val hideBottomBarRoutes = setOf("stunting_result", "nutrition_result","login","splash")
    if (currentRoute in hideBottomBarRoutes) return true
    return currentRoute?.startsWith("food_recipes/") == true
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    fun isRouteSelected(route: String?, itemRoute: String): Boolean {
        return route?.startsWith(itemRoute) == true
    }
    Column {
        HorizontalDivider(thickness = 1.dp, color = md_theme_light_outlineVariant)
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
                val isSelected = isRouteSelected(currentRoute, item.screen.route)
                NavigationBarItem(
                    icon = {
                        item.icon(isSelected)
                    },
                    selected = isSelected,
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


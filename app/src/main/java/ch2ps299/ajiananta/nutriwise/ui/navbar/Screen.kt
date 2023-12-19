package ch2ps299.ajiananta.nutriwise.ui.navbar

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash")
    data object OnBoardingScreen : Screen("onboarding")
    data object LoginScreen : Screen("login")
    data object HomeMenu : Screen("home")
    data object HistoryScreen : Screen("history")
    data object ProfileChangeChild : Screen("profile_change_child")
    data object StuntingResult: Screen("stunting_result")
    data object AddDataChild : Screen("add_data_child")
    data object FoodRecipes : Screen("food_recipes")
    data object DetailRecipe: Screen("food_recipes/{id}") {
        fun createRoute(id: Long) = "food_recipes/$id"
    }
    data object Profile : Screen("profile")
    data object About : Screen("about")
    data object Nutrition : Screen("nutrition")
    data object NutritionResult : Screen("nutrition_result")
}
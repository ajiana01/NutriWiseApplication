package ch2ps299.ajiananta.nutriwise.ui.navbar

import androidx.compose.runtime.Composable

data class NavbarItem(
    val icon: @Composable (isSelected: Boolean) -> Unit,
    val screen: Screen
)
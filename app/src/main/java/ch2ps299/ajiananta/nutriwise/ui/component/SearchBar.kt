package ch2ps299.ajiananta.nutriwise.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Icon Search",
                tint = md_theme_light_outlineVariant
            )
        },
        placeholder = {
            Text(
                text = "Cari resep masakan terbaik",
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = md_theme_light_outlineVariant
            )
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
            dividerColor = md_theme_light_outlineVariant,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                focusedTextColor = md_theme_light_onPrimaryContainer,
                unfocusedTextColor = md_theme_light_onPrimaryContainer,
            )
        ),
        modifier = modifier
            .border(
                width = 1.dp,
                color = md_theme_light_outlineVariant,
                shape = RoundedCornerShape(8.dp),
            )
            .background(Color.White)
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
    }
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
    var text: String by remember { mutableStateOf("") }
    SearchBar(
        query = text,
        onQueryChange = { newQuery ->
            text = newQuery
        }
    )
}

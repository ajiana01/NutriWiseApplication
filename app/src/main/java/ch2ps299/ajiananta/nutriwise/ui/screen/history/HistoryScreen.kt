package ch2ps299.ajiananta.nutriwise.ui.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_dark_error
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_dark_errorContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_errorContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_inversePrimary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        TopBar(labelText = "Riwayat Pengecekan Stunting", onBackClick = { /*TODO*/ })
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            LazyColumn( verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    HistoryItemStunting()
                    Spacer(modifier = Modifier.padding(8.dp))
                    HistoryItemFit()
                }
            }
        }
    }
}

@Composable
fun HistoryItemStunting(
    historycheck: String = "N/A"
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = md_theme_light_errorContainer, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Halo Parent! Si Kecil Terindikasi Masalah Stunting", fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = md_theme_dark_errorContainer)
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.CheckCircle, contentDescription = "Icon check", tint = md_theme_dark_error, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Terakhir Di Cek Pada ${historycheck}", fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 10.sp, color = md_theme_dark_error)
            }
        }
    }
}

@Composable
fun HistoryItemFit(
    historycheck: String = "N/A"
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = md_theme_light_secondaryContainer, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Halo Parent! Si Kecil Tumbuh Dengan Baik", fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = md_theme_light_primary)
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.CheckCircle, contentDescription = "Icon check", tint = md_theme_light_inversePrimary, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Terakhir Di Cek Pada ${historycheck}", fontFamily = NunitoFontFamily, fontWeight = FontWeight.Bold, fontSize = 10.sp, color = md_theme_light_inversePrimary)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryItemStuntingPreview() {
    HistoryItemStunting()
}

@Composable
@Preview(showBackground = true)
fun HistoryItemFitPreview() {
    HistoryItemFit()
}

@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview() {
    HistoryScreen()
}


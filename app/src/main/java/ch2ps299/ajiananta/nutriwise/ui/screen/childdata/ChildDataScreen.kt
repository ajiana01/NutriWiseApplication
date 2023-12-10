package ch2ps299.ajiananta.nutriwise.ui.screen.childdata

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer

@Composable
fun ChildDataScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TopBar(labelText = "Data Anak", onBackClick = {})
            Column(
                modifier = modifier
                    .padding(16.dp)
            ) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        //TODO: Add Child Data Item
                        ChildDataItem(childname = "Aji Ananta", childage = 2)
                    }
                }
            }
        }
        Column(modifier = modifier.padding(16.dp)) {
            ButtonComponent(text = "Tambah Data Anak", onClick = {})
        }
    }
}

@Composable
fun ChildDataItem(
    modifier: Modifier = Modifier,
    childname: String,
    childage: Int,
    onDeleteChildData: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = md_theme_light_secondaryContainer, shape = RoundedCornerShape(8.dp))

    ) {
        Box(modifier = modifier.padding(16.dp)){
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = childname,
                        fontWeight = FontWeight.Bold,
                        fontFamily = NunitoFontFamily,
                        fontSize = 16.sp,
                        color = md_theme_light_primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$childage Bulan",
                        fontWeight = FontWeight.Medium,
                        fontFamily = NunitoFontFamily,
                        fontSize = 14.sp,
                        color = md_theme_light_primary
                    )
                }
                TextButton(onClick = onDeleteChildData) {
                    Text(text = "Hapus Data",
                        fontWeight = FontWeight.Medium,
                        fontFamily = NunitoFontFamily,
                        fontSize = 14.sp,
                        color = md_theme_light_primary
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChildDataPreview() {
    ChildDataItem( childname = "Aji Ananta", childage = 2)
}
@Composable
@Preview(showBackground = true)
fun ChildDataScreenPreview() {
    ChildDataScreen()
}
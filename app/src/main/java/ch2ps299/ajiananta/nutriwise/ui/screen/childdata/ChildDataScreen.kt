package ch2ps299.ajiananta.nutriwise.ui.screen.childdata

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.di.RetrofitClient
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ChildDataViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory
import ch2ps299.ajiananta.nutriwise.utils.ageInMonths

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChildDataScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ChildDataViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value
    val deleteStatus = viewModel.deleteStatus.collectAsState(initial = UiState.Loading).value

    LaunchedEffect(deleteStatus) {
        if (deleteStatus is UiState.Success) {
            viewModel.getChildrenData()
        }
    }

    when(uiState) {
        is UiState.Loading -> {
            viewModel.getChildrenData()
        }
        is UiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    TopBar(labelText = "Data Anak", onBackClick = {
                        navController.popBackStack()
                    } )
                    Column(
                        modifier = modifier
                            .padding(16.dp)
                    ) {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(uiState.data) {
                                val childName = it.child_name
                                val childAge =
                                    ageInMonths(it.date_of_birth)
                                ChildDataItem(
                                    childname = childName,
                                    childage = childAge,
                                    onDeleteChildData = {
                                        viewModel.deleteChildById(it.id)
                                    },
                                    modifier = Modifier
                                        .clickable {
                                            it.id.also { viewModel.selectedChildId }
                                            viewModel.selectChild(it.id)
                                            Log.d("ChildDataScreen", "ChildDataScreen: ${viewModel.selectedChildId.value}")
                                            navController.navigate("home_screen/${it.id}") {
                                                popUpTo("child_data_screen") { inclusive = true }
                                            }
                                        }
                                )
                            }
                            Log.d("ChildDataScreen", "ChildDataScreen: ${uiState.data}")
                        }
                    }
                }
                Column(modifier = modifier.padding(16.dp)) {
                    ButtonComponent(text = "Tambah Data Anak", onClick = { navController.navigate(route = "add_data_child")})
                }
            }
        }
        is UiState.Error -> {

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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun ChildDataScreenPreview() {
    ChildDataScreen(
        navController = NavController(LocalContext.current)
    )
}
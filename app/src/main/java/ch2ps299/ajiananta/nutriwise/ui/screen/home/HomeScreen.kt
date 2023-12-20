package ch2ps299.ajiananta.nutriwise.ui.screen.home

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.di.RetrofitClient
import ch2ps299.ajiananta.nutriwise.model.PredictBody
import ch2ps299.ajiananta.nutriwise.model.UserData
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.InputNumberField
import ch2ps299.ajiananta.nutriwise.ui.navbar.Screen
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondary
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_secondaryContainer
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ChildDataViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory
import ch2ps299.ajiananta.nutriwise.utils.ageInMonths
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userData: UserData,
    viewModel: ChildDataViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
    stuntingViewModel: ChildDataViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
    childIdArg: String,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    // State untuk menampilkan dialog konfirmasi
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val stuntingStatus = stuntingViewModel.uiStateStunting.collectAsState()
    // Menangani tombol kembali
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { _ ->
        BackHandler {
            // Mengubah state untuk menampilkan dialog
            showDialog = true

        }
        if (showDialog) {
            ConfirmExitDialog(onConfirm = {
                // Keluar dari aplikasi
                (context as? Activity)?.finishAffinity()
            }, onDismiss = {
                // Sembunyikan dialog tanpa keluar
                showDialog = false
            })
        }
        val childId = childIdArg.ifEmpty { viewModel.selectedChildId.value }
        Log.d("ChildId Home Screen", childId.toString())
        LaunchedEffect(childId) {
            if (childId != null) {
                viewModel.fetchChildData(childId)
            }
        }
        val childData by viewModel.childData.collectAsState()
        Column (
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            HomeProfile(
                userName = userData.name ?: "",
                historyClick = { navController.navigate(route = Screen.HistoryScreen.route) },
                changeProfileClick = {navController.navigate(route = Screen.ProfileChangeChild.route)},
                childName = childData?.child_name ?: "Belum Ada Data",
                childAge = childData?.date_of_birth?.let { ageInMonths(it) } ?: 0
            )
            StuntingCheck(
                stuntingClick = {
                    val checkGender = if (childData?.gender == "Male") 0 else 1
                    val childAge = childData?.date_of_birth?.let { ageInMonths(it) } ?: 0
                    val childWeight = viewModel.weightText.value.text.toDouble()
                    val childHeight = viewModel.heightText.value.text.toDouble()
                    val childCircle = viewModel.circleText.value.text.toDouble()

                    val predictBody = PredictBody(input = listOf(checkGender.toDouble(), childAge.toDouble(), childWeight, childHeight, childCircle))
                    stuntingViewModel.postStuntingPrediction(predictBody)
                }
            )
            LaunchedEffect(stuntingStatus.value) {
                if (stuntingStatus.value is UiState.Success) {
                    val checkStatus = (stuntingStatus.value as UiState.Success).data == "Your child is stunting"
                    navController.navigate("stunting_result/$childId/$checkStatus")
                }
            }
        }
    }

}

@Composable
fun HomeProfile(
    modifier: Modifier = Modifier,
    userName: String,
    childName: String,
    childAge: Int,
    historyClick: () -> Unit = {},
    changeProfileClick: () -> Unit = {}
) {

    Box{
        Image(
            painter = painterResource(id = R.drawable.background_home),
            contentDescription = "Backgorund Profile",
            modifier = modifier
                .fillMaxWidth()
                .height(160.dp),
            contentScale = ContentScale.FillWidth
        )
        Column (
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Row {
                Text(
                    text = "Halo,",
                    fontWeight = FontWeight.Medium,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp

                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = "$userName!",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Box(
                modifier = modifier
                    .background(
                        color = md_theme_light_secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = childName,
                                fontWeight = FontWeight.Bold,
                                fontFamily = NunitoFontFamily,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = modifier.height(8.dp))
                            Text(
                                text = "$childAge Bulan",
                                fontWeight = FontWeight.Medium,
                                fontFamily = NunitoFontFamily,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = modifier.height(16.dp))
                            Button(
                                onClick = historyClick,
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = md_theme_light_primary
                                ),
                                shape = RoundedCornerShape(4.dp)
                                
                            ) {
                                Text(
                                    text = "Cek Riwayat",
                                    modifier = modifier.align(Alignment.CenterVertically),
                                    fontSize = 12.sp,
                                    fontFamily = NunitoFontFamily,
                                    fontWeight =  FontWeight.Bold
                                )
                            }
                        }
                        TextButton(onClick = changeProfileClick) {
                            Text(
                                text = "Ganti Profil Anak",
                                modifier = modifier.align(Alignment.CenterVertically),
                                fontSize = 12.sp,
                                fontFamily = NunitoFontFamily,
                                fontWeight =  FontWeight.Bold,
                                color = md_theme_light_secondary
                            )
                        }
                    }

                }

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StuntingCheck(
    modifier: Modifier = Modifier,
    viewModel: ChildDataViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
    stuntingClick: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { _ ->
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Pengecekan Stunting",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 16.sp,
                color = md_theme_light_primary)
            Spacer(modifier = modifier.height(8.dp))
            InputNumberField(labelText = "Berat Badan (kg)", text = viewModel.weightText.value, onTextChange = { viewModel.onWeightChange(it)} , placeholder = "Contoh: 3.5")
            Spacer(modifier = modifier.height(8.dp))
            InputNumberField(labelText = "Tinggi Badan (cm)", text = viewModel.heightText.value, onTextChange = { viewModel.onHeightChange(it)} , placeholder = "Contoh: 49.5")
            Spacer(modifier = modifier.height(8.dp))
            InputNumberField(labelText = "Lingkar Kepala (cm)", text = viewModel.circleText.value, onTextChange = { viewModel.onCircleChange(it)} , placeholder = "Contoh: 35.5")
            Spacer(modifier = modifier.height(16.dp))

            val isInputValid = remember(
                viewModel.weightText.value,
                viewModel.heightText.value,
                viewModel.circleText.value
            ) {
                val weight = viewModel.weightText.value.text.toDoubleOrNull()
                val height = viewModel.heightText.value.text.toDoubleOrNull()
                val circle = viewModel.circleText.value.text.toDoubleOrNull()

                weight != null && weight > 0 &&
                        height != null && height > 0 &&
                        circle != null && circle > 0
            }

            ButtonComponent(text = "Stunting Cek", onClick = {
                if (isInputValid) {
                    stuntingClick()
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Mohon isi semua data dengan benar.")
                    }
                }
            })
        }
    }
}

@Composable
fun ConfirmExitDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Konfirmasi",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            color = md_theme_light_onPrimaryContainer) },
        text = { Text("Apakah Anda yakin ingin keluar dari aplikasi?",
            fontWeight = FontWeight.SemiBold,
            fontFamily = NunitoFontFamily,
            color = md_theme_light_onPrimaryContainer) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Keluar",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    color = Color.White)
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Batal",
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    color = Color.White)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun HomeProfilePreview() {
    NutriWiseApplicationTheme {
        HomeScreen(
            navController = NavController(
                context = LocalContext.current
            ),
            userData = UserData(
                "1",
                "Aji Ananta",
                "Aji Ananta",
                "1",
            ),
            childIdArg = ""
        )
    }
}
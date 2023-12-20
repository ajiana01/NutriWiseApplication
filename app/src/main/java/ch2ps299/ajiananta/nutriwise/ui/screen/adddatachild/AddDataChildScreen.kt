package ch2ps299.ajiananta.nutriwise.ui.screen.adddatachild

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.di.RetrofitClient
import ch2ps299.ajiananta.nutriwise.model.SendChildData
import ch2ps299.ajiananta.nutriwise.ui.common.UiState
import ch2ps299.ajiananta.nutriwise.ui.component.ButtonComponent
import ch2ps299.ajiananta.nutriwise.ui.component.DatePicker
import ch2ps299.ajiananta.nutriwise.ui.component.InputNumberField
import ch2ps299.ajiananta.nutriwise.ui.component.InputTextField
import ch2ps299.ajiananta.nutriwise.ui.component.TopBar
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.NutriWiseApplicationTheme
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.AddDataChildViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory
import ch2ps299.ajiananta.nutriwise.utils.reformatDate
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddDataChildScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddDataChildViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    ),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val postStatus = viewModel.postStatus.collectAsState().value
    val contxt = LocalContext.current
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { _ ->
        Column(modifier = modifier.fillMaxSize()) {
            TopBar(labelText = "Tambah Data Anak", onBackClick = { navController.popBackStack()})
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    InputTextField(labelText = "Nama Anak ", text = viewModel.childName.value , onTextChange = {viewModel.onChildNameChange(it)} , placeholder = "Masukkan nama anak")
                    Spacer(modifier = modifier.padding(8.dp))
                    DatePicker(labelText = "Tanggal Lahir", context = contxt, selectedDate = viewModel.selectedDate )
                    Log.d("selectedDate", viewModel.selectedDate.value)
                    Spacer(modifier = modifier.padding(8.dp))
                    GenderRadioButtonGroup(onGenderSelected = { gender ->
                        viewModel.childGender.value = gender
                    })
                    Text(text = "Data Kelahiran",
                        fontWeight = FontWeight.Bold,
                        fontFamily = NunitoFontFamily,
                        fontSize = 16.sp,
                        color = md_theme_light_primary
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    InputNumberField(labelText = "Berat Badan (kg)", text = viewModel.weightText.value, onTextChange = { viewModel.onWeightTextChange(it)} , placeholder = "Contoh: 3.5")
                    Spacer(modifier = modifier.height(8.dp))
                    InputNumberField(labelText = "Tinggi Badan (cm)", text = viewModel.heightText.value, onTextChange = { viewModel.onHeightTextChange(it)} , placeholder = "Contoh: 49.5")
                    Spacer(modifier = modifier.height(8.dp))
                    InputNumberField(labelText = "Lingkar Kepala (cm)", text = viewModel.circleText.value, onTextChange = { viewModel.onCircleTextChange(it)} , placeholder = "Contoh: 35.5")
                    Spacer(modifier = modifier.height(16.dp))
                }
                val isFormValid = remember(
                    viewModel.childName.value.text,
                    viewModel.selectedDate.value,
                    viewModel.heightText.value.text,
                    viewModel.weightText.value.text,
                    viewModel.circleText.value.text,
                    viewModel.childGender.value
                ) {
                    isFormValid(
                        viewModel.childName.value.text,
                        viewModel.selectedDate.value,
                        viewModel.heightText.value.text,
                        viewModel.weightText.value.text,
                        viewModel.circleText.value.text,
                        viewModel.childGender.value
                    )
                }

                ButtonComponent(text = "Simpan",
                    onClick = {
                        if (isFormValid) {
                            val formattedDate = reformatDate(viewModel.selectedDate.value, "dd/MM/yyyy", "yyyy-MM-dd")
                            val childData = SendChildData(
                                child_name = viewModel.childName.value.text,
                                date_of_birth = formattedDate,
                                height = viewModel.heightText.value.text.toDoubleOrNull() ?: 0.0,
                                weight = viewModel.weightText.value.text.toDoubleOrNull() ?: 0.0,
                                gender = if (viewModel.childGender.value == "Male") "male" else "female",
                                head_circumference = viewModel.circleText.value.text.toDoubleOrNull() ?: 0.0
                            )
                            viewModel.postChild(childData)
                            Log.d("childData", childData.toString())
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Silakan isi semua data terlebih dahulu")
                            }
                        }
                    },
                )
                LaunchedEffect(postStatus) {
                    when (postStatus) {
                        is UiState.Success -> {
                            // Navigasi setelah sukses
                            navController.navigate(route = "profile_change_child") {
                                popUpTo("profile_change_child"){
                                    inclusive = true
                                }
                            }
                        }
                        is UiState.Error -> {
                            // Tampilkan pesan error
                        }
                        is UiState.Loading -> {
                            // Tampilkan indikator loading jika diperlukan
                        }
                    }
                }
            }
        }
    }

}

fun isFormValid(
    childName: String,
    dateOfBirth: String,
    height: String,
    weight: String,
    headCircumference: String,
    gender: String
): Boolean {
    return childName.isNotBlank() &&
            dateOfBirth.isNotBlank() &&
            height.isNotBlank() &&
            weight.isNotBlank() &&
            headCircumference.isNotBlank() &&
            gender.isNotBlank()
}

@Composable
fun GenderRadioButtonGroup(
    modifier: Modifier = Modifier,
    onGenderSelected: (String) -> Unit
) {
    var selectedGender by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Text("Jenis Kelamin:",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 14.sp,
            color = md_theme_light_primary)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedGender == "Male",
                onClick = {
                    selectedGender = "Male"
                    onGenderSelected(selectedGender)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = md_theme_light_primary,
                    unselectedColor = md_theme_light_outlineVariant
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text("Laki-laki",
                fontWeight = FontWeight.SemiBold,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer)
            Spacer(modifier = Modifier.padding(8.dp))
            RadioButton(
                selected = selectedGender == "Female",
                onClick = {
                    selectedGender = "Female"
                    onGenderSelected(selectedGender)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = md_theme_light_primary,
                    unselectedColor = md_theme_light_outlineVariant
                )
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text("Perempuan",
                fontWeight = FontWeight.SemiBold,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddDataChildScreenPreview() {
    NutriWiseApplicationTheme {
        AddDataChildScreen(
            navController = NavController(
                context = LocalContext.current
            )
        )
    }
}
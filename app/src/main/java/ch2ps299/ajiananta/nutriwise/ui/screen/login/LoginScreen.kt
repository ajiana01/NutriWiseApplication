package ch2ps299.ajiananta.nutriwise.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ch2ps299.ajiananta.nutriwise.R
import ch2ps299.ajiananta.nutriwise.di.DataInjection
import ch2ps299.ajiananta.nutriwise.di.RetrofitClient
import ch2ps299.ajiananta.nutriwise.model.SignInState
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.LoginViewModel
import ch2ps299.ajiananta.nutriwise.ui.viewmodel.ViewModelFactory

@Composable
fun LoginScreen(
    state: SignInState,
    onSignIn: () -> Unit,
    viewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory(
            DataInjection.provideRepository(),
            RetrofitClient.provideRepository2()
        )
    )
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        LoginContent(
            onSignIn = onSignIn
        )
    }
}

@Composable
fun LoginContent(
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(2f) // Gunakan weight untuk membagi ruang secara adil
                .fillMaxWidth()
        ) {
            Image(
                painterResource(R.drawable.login_image),
                contentDescription = "Login Image",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.drawable.logo_with_bg),
                    contentDescription = "Logo Image",
                    modifier = Modifier
                        .offset(y = 32.dp)
                        .size(96.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape( topStart = 32.dp, topEnd = 32.dp))
                .weight(1f) // Gunakan weight untuk membagi ruang secara adil
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .width(56.dp)
                                .height(4.dp)
                                .background(md_theme_light_outlineVariant, shape = RoundedCornerShape(4.dp))
                        )
                    }
                    Text(text = "Selamat Datang,",
                        fontWeight = FontWeight.Bold,
                        fontFamily = NunitoFontFamily,
                        fontSize = 24.sp,
                        color = md_theme_light_primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Pantau si kecil untuk terhindar dari stunting dan penuhi gizi harian si kecil tiap harinya!",
                        fontWeight = FontWeight.Medium,
                        fontFamily = NunitoFontFamily,
                        fontSize = 16.sp,
                        color = md_theme_light_primary,
                        maxLines = 3
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onSignIn()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = md_theme_light_primary,
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, md_theme_light_outlineVariant)

                ) {
                    Image(painter = painterResource(R.drawable.ic_google), contentDescription = "Google Icon", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Masuk dengan Google",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontSize = 16.sp,
                        fontFamily = NunitoFontFamily,
                        fontWeight =  FontWeight.Bold
                    )
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    LoginScreen(
        state = SignInState(),
        onSignIn = {}
    )
}
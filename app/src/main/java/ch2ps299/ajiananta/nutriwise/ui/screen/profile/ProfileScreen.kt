package ch2ps299.ajiananta.nutriwise.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ch2ps299.ajiananta.nutriwise.model.UserData
import ch2ps299.ajiananta.nutriwise.ui.theme.NunitoFontFamily
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_onPrimaryContainer
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_outlineVariant
import ch2ps299.ajiananta.nutriwise.ui.theme.md_theme_light_primary
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    navController: NavController,
    userData: UserData,
    onClickLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            userData.photoUrl?.let {
                ProfileContent(
                    imageProfile = it,
                    userName = userData.name ?: "",
                    email = userData.email ?: "",
                    onClickChildData = {
                        navController.navigate("profile_change_child")
                    },
                    onClickAbout = {
                        navController.navigate("about")
                    },
                    onClickLogout = {
                        onClickLogout()
                    }
                )
            }
        }

    }
}

@Composable
fun ProfileContent(
    imageProfile: String,
    userName: String,
    email: String,
    onClickChildData: () -> Unit = {},
    onClickAbout: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    Column {
        Text(text = "Profil",
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            color = md_theme_light_primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageProfile,
                contentDescription = "Photo Profile",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = userName,
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 16.sp,
                    color = md_theme_light_onPrimaryContainer
                )
                Text(text = email,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 12.sp,
                    color = md_theme_light_onPrimaryContainer
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItem(
            icon = Icons.Outlined.Face,
            title = "Data Anak",
            contentText = "Tambah, ubah dan hapus profil anak",
            onClickDetail = { onClickChildData() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItem(
            icon = Icons.Outlined.Info,
            title = "Tentang Kami",
            contentText = "Tentang Wise Team",
            onClickDetail = { onClickAbout() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        LogoutFromApp(
            onClickLogout = { onClickLogout() }
        )
    }

}

@Composable
fun ProfileItem(
    icon: ImageVector,
    title: String,
    contentText: String,
    onClickDetail: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClickDetail() }),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = "Icon $title", tint = md_theme_light_outlineVariant, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = NunitoFontFamily,
                    fontSize = 14.sp,
                    color = md_theme_light_onPrimaryContainer
                )
                Text(text = contentText,
                    fontWeight = FontWeight.Medium,
                    fontFamily = NunitoFontFamily,
                    fontSize = 11.sp,
                    color = md_theme_light_onPrimaryContainer
                )
            }
        }
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Icon $title", tint = md_theme_light_onPrimaryContainer)
    }
}

@Composable
fun LogoutFromApp(
    onClickLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { onClickLogout() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ExitToApp, contentDescription = "Exit App", tint = md_theme_light_outlineVariant, modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Keluar",
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                fontSize = 14.sp,
                color = md_theme_light_onPrimaryContainer
            )
        }
        Text(text = "Versi 1.0",
            fontWeight = FontWeight.Medium,
            fontFamily = NunitoFontFamily,
            fontSize = 11.sp,
            color = md_theme_light_onPrimaryContainer
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = NavController(LocalContext.current),
        userData = UserData(
            "1",
            "",
            "Aji Ananta",
            "https://avatars.githubusercontent.com/u/24792674?v=4"
        ),
        onClickLogout = {}
    )
}

@Composable
@Preview(showBackground = true)
fun ProfileItemPreview() {
    ProfileItem(
        icon = Icons.Outlined.Face,
        title = "Data Anak",
        contentText = "Tambah, ubah dan hapus profil anak",
        onClickDetail = {}
    )
}
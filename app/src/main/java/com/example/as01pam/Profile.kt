package com.example.as01pam

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.as01pam.ui.theme.AS01PAMTheme
import com.example.as01pam.ui.theme.Burgundy
import com.example.as01pam.ui.theme.Cream
import com.example.as01pam.ui.theme.DarkBrown
import com.example.as01pam.ui.theme.MutedBrown
import com.example.as01pam.ui.theme.SoftWhite

class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstName = intent.getStringExtra("firstName") ?: ""
        val lastName = intent.getStringExtra("lastName") ?: ""
        val username = intent.getStringExtra("username") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val dateOfBirth = intent.getStringExtra("dateOfBirth") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val address = intent.getStringExtra("address") ?: ""

        setContent {
            AS01PAMTheme {
                ProfileScreen(
                    firstName = firstName,
                    lastName = lastName,
                    username = username,
                    email = email,
                    password = password,
                    dateOfBirth = dateOfBirth,
                    phone = phone,
                    address = address,
                    onAvatarClick = {
                        startActivity(Intent(this, AvatarActivity::class.java))
                    },
                    onLogout = {
                        val intent = Intent(this, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(
    firstName: String,
    lastName: String,
    username: String,
    email: String,
    password: String,
    dateOfBirth: String,
    phone: String,
    address: String,
    onAvatarClick: () -> Unit,
    onLogout: () -> Unit
) {
    val pillShape = RoundedCornerShape(50)
    val cardShape = RoundedCornerShape(24.dp)

    // PERBAIKAN LOGIKA PENGECEKAN NAMA DAN USERNAME
    val validFirstName = firstName.takeIf { it.isNotBlank() && it != "-" }
    val validLastName = lastName.takeIf { it.isNotBlank() && it != "-" }

    val fullName = when {
        validFirstName != null && validLastName != null -> "$validFirstName $validLastName"
        validFirstName != null -> validFirstName
        validLastName != null -> validLastName
        else -> "Name"
    }

    val validUsername = username.takeIf { it.isNotBlank() && it != "-" }
    val displayUsername = if (validUsername != null) "@$validUsername" else "@username"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "User Profile",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = cardShape,
            color = SoftWhite,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // AVATAR ICON
                Surface(
                    onClick = onAvatarClick,
                    modifier = Modifier.size(76.dp),
                    shape = CircleShape,
                    color = Burgundy.copy(alpha = 0.12f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Avatar",
                            tint = Burgundy,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // NAMA USER (Tidak akan kosong lagi)
                Text(
                    text = fullName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    textAlign = TextAlign.Center
                )

                // USERNAME
                Text(
                    text = displayUsername,
                    fontSize = 13.sp,
                    color = MutedBrown,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = MutedBrown.copy(alpha = 0.2f), thickness = 1.dp)

                Spacer(modifier = Modifier.height(16.dp))

                InfoItem(label = "First Name", value = firstName)
                InfoItem(label = "Last Name", value = lastName)
                InfoItem(label = "Username", value = username)
                InfoItem(label = "Email Address", value = email)
                InfoItem(label = "Password", value = password)
                InfoItem(label = "Date of Birth", value = dateOfBirth)
                InfoItem(label = "Phone Number", value = phone)
                InfoItem(label = "Address", value = address)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = pillShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Burgundy
                    )
                ) {
                    Text(
                        text = "Logout",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun InfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            shape = RoundedCornerShape(50),
            color = Cream.copy(alpha = 0.35f),
            border = BorderStroke(
                width = 1.dp,
                color = MutedBrown.copy(alpha = 0.15f)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (value.isNotBlank() && value != "-") value else "-",
                    fontSize = 13.sp,
                    color = DarkBrown,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
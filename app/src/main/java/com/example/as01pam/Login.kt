package com.example.as01pam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.as01pam.ui.theme.AS01PAMTheme
import com.example.as01pam.ui.theme.Burgundy
import com.example.as01pam.ui.theme.Cream
import com.example.as01pam.ui.theme.DarkBrown
import com.example.as01pam.ui.theme.MutedBrown
import com.example.as01pam.ui.theme.SoftWhite

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AS01PAMTheme {
                LoginScreen(
                    onLogin = { username, password ->
                        val intent = Intent(this, ProfileActivity::class.java).apply {
                            putExtra("username", username)
                            putExtra("password", password)
                        }
                        startActivity(intent)
                    },
                    onForgotPassword = {
                        Toast.makeText(this, "Fitur Lupa Password diklik", Toast.LENGTH_SHORT).show()
                    },
                    onSignUp = {
                        startActivity(Intent(this, RegistrationActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val pillShape = RoundedCornerShape(50)
    val cardShape = RoundedCornerShape(24.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // =========================
        // TOP HEADER
        // =========================
        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(28.dp))

        // =========================
        // FORM CARD CONTAINER
        // =========================
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

                Text(
                    text = "Welcome Back",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Please enter your details to sign in.",
                    fontSize = 12.sp,
                    color = MutedBrown,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 1. INPUT USERNAME
                FormLabel(text = "Username")
                PillTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Enter your username"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. INPUT PASSWORD (BLINDED TEXT)
                FormLabel(text = "Password")
                PillTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter your password",
                    visualTransformation = PasswordVisualTransformation()
                )

                // 3. LINK / TOMBOL "LUPA PASSWORD"
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(
                        onClick = onForgotPassword,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(
                            text = "Lupa Password?",
                            color = Burgundy,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 4. TOMBOL LOGIN
                Button(
                    onClick = { onLogin(username, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = pillShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Burgundy
                    )
                ) {
                    Text(
                        text = "Login",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // LINK KE HALAMAN SIGN UP
                TextButton(
                    onClick = onSignUp
                ) {
                    Text(
                        text = "Don't have an account? Sign Up",
                        color = DarkBrown,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Custom Pill Input Field
@Composable
private fun PillTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var isFocused by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(50),
        color = if (isFocused) Cream.copy(alpha = 0.5f) else Cream.copy(alpha = 0.3f),
        border = BorderStroke(
            width = if (isFocused) 1.5.dp else 1.dp,
            color = if (isFocused) Burgundy else MutedBrown.copy(alpha = 0.2f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 13.sp,
                    color = MutedBrown
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { isFocused = it.isFocused },
                singleLine = true,
                visualTransformation = visualTransformation,
                cursorBrush = SolidColor(Burgundy),
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    color = DarkBrown,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Composable
private fun FormLabel(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = DarkBrown,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, start = 4.dp)
    )
}
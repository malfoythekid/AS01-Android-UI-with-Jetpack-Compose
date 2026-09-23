package com.example.as01pam

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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

class RegistrationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AS01PAMTheme {
                RegistrationScreen(
                    onSave = { firstName, lastName, username, email,
                               password, dateOfBirth, phone, address ->

                        val intent = Intent(
                            this,
                            ProfileActivity::class.java
                        )

                        intent.putExtra("firstName", firstName)
                        intent.putExtra("lastName", lastName)
                        intent.putExtra("username", username)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        intent.putExtra("dateOfBirth", dateOfBirth)
                        intent.putExtra("phone", phone)
                        intent.putExtra("address", address)

                        startActivity(intent)
                    },

                    onLogin = {
                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            )
                        )
                    }
                )
            }
        }
    }
}


@Composable
fun RegistrationScreen(
    onSave: (
        String,
        String,
        String,
        String,
        String,
        String,
        String,
        String
    ) -> Unit,

    onLogin: () -> Unit
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var dateOfBirth by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(20.dp))

        // =========================
        // TOP HEADER
        // =========================
        Text(
            text = "Sign Up",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                    text = "Personal Info",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Complete your profile to get started.",
                    fontSize = 12.sp,
                    color = MutedBrown,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                // YOUR NAME
                FormLabel(text = "Your Name")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PillTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        placeholder = "First Name",
                        modifier = Modifier.weight(1f)
                    )

                    PillTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        placeholder = "Last Name",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // EMAIL ADDRESS
                FormLabel(text = "Email Address")
                PillTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "example@gmail.com"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // USERNAME
                FormLabel(text = "Username")
                PillTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = "Username"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // PASSWORD
                FormLabel(text = "Password")
                PillTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Password",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // DATE OF BIRTH
                FormLabel(text = "Date of Birth")
                PillTextField(
                    value = dateOfBirth,
                    onValueChange = { dateOfBirth = it },
                    placeholder = "DD / MM / YYYY"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // PHONE NUMBER
                FormLabel(text = "Phone Number")
                PillTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = "Your Phone Number"
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ADDRESS
                FormLabel(text = "Address")
                PillTextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = "Your Address"
                )

                Spacer(modifier = Modifier.height(20.dp))

                // SAVE BUTTON
                Button(
                    onClick = {
                        onSave(
                            firstName,
                            lastName,
                            username,
                            email,
                            password,
                            dateOfBirth,
                            phone,
                            address
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = pillShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Burgundy
                    )
                ) {
                    Text(
                        text = "Save & Continue",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // BACK TO LOGIN LINK
                TextButton(
                    onClick = onLogin
                ) {
                    Text(
                        text = "Back to Login",
                        color = DarkBrown,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

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
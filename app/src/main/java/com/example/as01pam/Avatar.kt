package com.example.as01pam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.as01pam.ui.theme.AS01PAMTheme
import com.example.as01pam.ui.theme.Burgundy
import com.example.as01pam.ui.theme.Cream
import com.example.as01pam.ui.theme.DarkBrown
import com.example.as01pam.ui.theme.MutedBrown


class AvatarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AS01PAMTheme {
                AvatarScreen()
            }
        }
    }
}


@Composable
fun AvatarScreen() {
    var showBrow by remember { mutableStateOf(true) }
    var showEye by remember { mutableStateOf(true) }
    var showNose by remember { mutableStateOf(true) }
    var showMouth by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // =========================
        // TITLE
        // =========================

        Text(
            text = "My Avatar",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBrown
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Customize your avatar",
            fontSize = 14.sp,
            color = MutedBrown
        )

        // Jarak dari subtitle ke Avatar diperluas (dari 20dp -> 32dp)
        Spacer(modifier = Modifier.height(32.dp))


        // =========================
        // AVATAR CONTAINER
        // =========================

        Box(
            modifier = Modifier.size(width = 330.dp, height = 420.dp),
            contentAlignment = Alignment.Center
        ) {

            // BASE FACE
            Image(
                painter = painterResource(id = R.drawable.face_0004),
                contentDescription = "Avatar face",
                modifier = Modifier.width(320.dp),
                contentScale = ContentScale.Fit
            )

            // EYEBROW
            if (showBrow) {
                Image(
                    painter = painterResource(id = R.drawable.face_0001),
                    contentDescription = "Eyebrow",
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.Center)
                        .offset(y = (-35).dp),
                    contentScale = ContentScale.Fit
                )
            }

            // EYES
            if (showEye) {
                Image(
                    painter = painterResource(id = R.drawable.face_0003),
                    contentDescription = "Eyes",
                    modifier = Modifier
                        .width(185.dp)
                        .align(Alignment.Center)
                        .offset(y = (-1).dp),
                    contentScale = ContentScale.Fit
                )
            }

            // NOSE
            if (showNose) {
                Image(
                    painter = painterResource(id = R.drawable.face_0002),
                    contentDescription = "Nose",
                    modifier = Modifier
                        .width(50.dp)
                        .align(Alignment.Center)
                        .offset(y = 33.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // MOUTH
            if (showMouth) {
                Image(
                    painter = painterResource(id = R.drawable.face_0000),
                    contentDescription = "Mouth",
                    modifier = Modifier
                        .width(125.dp)
                        .align(Alignment.Center)
                        .offset(y = 78.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }


        // Jarak dari Avatar ke Judul Komponen diperluas (dari 20dp -> 32dp)
        Spacer(modifier = Modifier.height(32.dp))


        // =========================
        // COMPONENT TITLE
        // =========================

        Text(
            text = "Face Components",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBrown
        )

        // Jarak dari Judul Komponen ke Checkbox diperluas (dari 8dp -> 16dp)
        Spacer(modifier = Modifier.height(16.dp))


        // =========================
        // CHECKBOXES
        // =========================

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarCheckbox(
                text = "Brow",
                checked = showBrow,
                onCheckedChange = { showBrow = it }
            )

            AvatarCheckbox(
                text = "Eye",
                checked = showEye,
                onCheckedChange = { showEye = it }
            )

            AvatarCheckbox(
                text = "Nose",
                checked = showNose,
                onCheckedChange = { showNose = it }
            )

            AvatarCheckbox(
                text = "Mouth",
                checked = showMouth,
                onCheckedChange = { showMouth = it }
            )
        }
    }
}


@Composable
fun AvatarCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Burgundy
            )
        )

        Text(
            text = text,
            fontSize = 14.sp,
            color = DarkBrown
        )
    }
}
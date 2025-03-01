package com.example.evhub.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evhub.R
import com.example.evhub.ui.theme.AzureBlue
import com.example.evhub.ui.theme.CharcoalBlue
import com.example.evhub.ui.theme.DarkBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.MidnightBlue
import com.example.evhub.ui.theme.SlateBlue
import com.example.evhub.ui.theme.White
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Loginpage() {
    // State to hold the mobile number input
    val mobileNumber = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBlue),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.999f)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = CharcoalBlue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Login title aligned to start with gradient text
                Text(
                    text = "Enter Your Mobile Number",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        brush = Brush.linearGradient(
                            colors = listOf(FuturisticGreen, AzureBlue)
                        )
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Row with Indian flag, country code, and mobile number input
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Indian flag image (ensure R.drawable.flag exists)
                    Image(
                        painter = painterResource(id = R.drawable.flag),
                        contentDescription = "Indian Flag",
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Country code text
                    Text(
                        text = "+91",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        color = White
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = mobileNumber.value,
                        onValueChange = { newValue ->
                            // Allow only digits and limit input to 10 characters
                            val filtered = newValue.filter { it.isDigit() }
                            if (filtered.length <= 10) {
                                mobileNumber.value = filtered
                            }
                        },
                        placeholder = {
                            Text(
                                text = "Mobile Number",
                                style = MaterialTheme.typography.bodyMedium.copy(color = LightGray)
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(color = White),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = FuturisticGreen,
                            unfocusedBorderColor = LightGray,
                            cursorColor = FuturisticGreen,
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Login Button
                Button(
                    onClick = { /* Handle login action here */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightGreen,
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "Get OTP",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginPage() {
    Loginpage()
}

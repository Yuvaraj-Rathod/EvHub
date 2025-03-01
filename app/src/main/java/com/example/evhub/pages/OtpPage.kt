package com.example.evhub.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.evhub.ui.theme.AzureBlue
import com.example.evhub.ui.theme.CharcoalBlue
import com.example.evhub.ui.theme.DarkBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.MidnightBlue
import com.example.evhub.ui.theme.SlateBlue
import com.example.evhub.ui.theme.White


// Custom OTP input row with six boxes
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInputRow(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit = {}
) {
    // Create a list of mutable state values, one for each digit.
    val otpValues = List(otpLength) { mutableStateOf("") }
    // Create a list of FocusRequesters to manage focus for each box.
    val focusRequesters = List(otpLength) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until otpLength) {
            OutlinedTextField(
                value = otpValues[i].value,
                onValueChange = { newValue ->
                    // Accept only one digit
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        otpValues[i].value = newValue
                        // Move focus to next box if available
                        if (newValue.isNotEmpty() && i < otpLength - 1) {
                            focusRequesters[i + 1].requestFocus()
                        }
                        // When the last box is filled, combine all digits and call onOtpComplete
                        if (i == otpLength - 1 && newValue.isNotEmpty()) {
                            val otp = otpValues.joinToString("") { it.value }
                            onOtpComplete(otp)
                            focusManager.clearFocus()
                        }
                    }
                },
                placeholder = {
                    Text(
                        text = "",
                        style = TextStyle(
                            color = LightGray,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(
                    color = White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(42.dp)
                    .focusRequester(focusRequesters[i]),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = FuturisticGreen,
                    unfocusedBorderColor = LightGray,
                    cursorColor = FuturisticGreen,
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpPage() {
    // State to hold the complete OTP (if needed elsewhere)
    val otpValue = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightBlue),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(16.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = CharcoalBlue)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title with gradient text
                Text(
                    text = "Enter OTP \uD83D\uDD12",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(FuturisticGreen, AzureBlue)
                        )
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                // OTP input row with six boxes
                OtpInputRow(
                    otpLength = 6,
                    onOtpComplete = { otp ->
                        otpValue.value = otp
                        // Handle OTP complete if needed
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Verify OTP Button
                Button(
                    onClick = { /* Handle verify OTP action */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LightGreen,
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "Verify OTP",
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Resend OTP text
                Text(
                    text = "Resend OTP?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = FuturisticGreen
                    ),
                    modifier = Modifier.clickable { /* Handle resend OTP action */ }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOtpPage() {
    OtpPage()
}


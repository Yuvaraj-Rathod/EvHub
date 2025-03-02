package com.example.evhub.pages.bookingpage

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evhub.pages.homepage.ElevatedIcon
import java.util.Calendar
import com.example.evhub.ui.theme.AzureBlue
import com.example.evhub.ui.theme.CharcoalBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.MidnightBlue
import com.example.evhub.ui.theme.SlateBlue
import com.example.evhub.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingPage(navController: NavController) {
    val context = LocalContext.current

    // Dummy options for charging station, time slot, and payment selection
    val stationOptions = listOf("Station A", "Station B", "Station C")
    val timeOptions = listOf("08:00 AM", "10:00 AM", "12:00 PM", "02:00 PM", "04:00 PM")
    val paymentOptions = listOf("PhonePe", "Google Pay", "Paytm") // Default is PhonePe

    // State holders for dropdowns and date
    val expandedStation = remember { mutableStateOf(false) }
    val selectedStation = remember { mutableStateOf(stationOptions[0]) }
    val expandedTime = remember { mutableStateOf(false) }
    val selectedTime = remember { mutableStateOf(timeOptions[0]) }
    val expandedPayment = remember { mutableStateOf(false) }
    val selectedPayment = remember { mutableStateOf(paymentOptions[0]) }
    val dateState = remember { mutableStateOf("") }

    // Setup a DatePickerDialog for the date field
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            dateState.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year,
        month,
        day
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Booking",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(AzureBlue, Color.Cyan)
                            )
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-16).dp),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    ElevatedIcon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        onClick = { /* Handle back navigation */ },
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MidnightBlue
                )
            )
        },
        containerColor = MidnightBlue
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = CharcoalBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Book Charging Slot",
                        style = MaterialTheme.typography.displaySmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            brush = Brush.horizontalGradient(
                                colors = listOf(AzureBlue, Color.Cyan)
                            )
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    // Charging Station Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expandedStation.value,
                        onExpandedChange = { expandedStation.value = !expandedStation.value }
                    ) {
                        OutlinedTextField(
                            value = selectedStation.value,
                            onValueChange = {},
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Select Charging Station",
                                    style = TextStyle(color = LightGray, fontSize = 12.sp)
                                )
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStation.value)
                            },
                            modifier = Modifier.menuAnchor(),
                            textStyle = TextStyle(color = White, fontSize = 14.sp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = FuturisticGreen,
                                unfocusedBorderColor = LightGray,
                                cursorColor = FuturisticGreen,
                                containerColor = Color.Transparent
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expandedStation.value,
                            onDismissRequest = { expandedStation.value = false }
                        ) {
                            stationOptions.forEach { station ->
                                DropdownMenuItem(
                                    text = { Text(station, style = TextStyle(color = MidnightBlue, fontSize = 14.sp)) },
                                    onClick = {
                                        selectedStation.value = station
                                        expandedStation.value = false
                                    }
                                )
                            }
                        }
                    }

                    // Date Field
                    OutlinedTextField(
                        value = dateState.value,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text(
                                text = "Select Date",
                                style = TextStyle(color = LightGray, fontSize = 14.sp)
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Date",
                                tint = FuturisticGreen
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { datePickerDialog.show() },
                        textStyle = TextStyle(color = White, fontSize = 16.sp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = FuturisticGreen,
                            unfocusedBorderColor = LightGray,
                            cursorColor = FuturisticGreen,
                            containerColor = Color.Transparent
                        )
                    )

                    // Time Slot Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expandedTime.value,
                        onExpandedChange = { expandedTime.value = !expandedTime.value }
                    ) {
                        OutlinedTextField(
                            value = selectedTime.value,
                            onValueChange = {},
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Select Time Slot",
                                    style = TextStyle(color = LightGray, fontSize = 12.sp)
                                )
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTime.value)
                            },
                            modifier = Modifier.menuAnchor(),
                            textStyle = TextStyle(color = White, fontSize = 14.sp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = FuturisticGreen,
                                unfocusedBorderColor = LightGray,
                                cursorColor = FuturisticGreen,
                                containerColor = Color.Transparent
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expandedTime.value,
                            onDismissRequest = { expandedTime.value = false }
                        ) {
                            timeOptions.forEach { time ->
                                DropdownMenuItem(
                                    text = { Text(time, style = TextStyle(color = MidnightBlue, fontSize = 14.sp)) },
                                    onClick = {
                                        selectedTime.value = time
                                        expandedTime.value = false
                                    }
                                )
                            }
                        }
                    }

                    // Payment Mode Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expandedPayment.value,
                        onExpandedChange = { expandedPayment.value = !expandedPayment.value }
                    ) {
                        OutlinedTextField(
                            value = selectedPayment.value,
                            onValueChange = {},
                            readOnly = true,
                            label = {
                                Text(
                                    text = "Select Payment Mode",
                                    style = TextStyle(color = LightGray, fontSize = 12.sp)
                                )
                            },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPayment.value)
                            },
                            modifier = Modifier.menuAnchor(),
                            textStyle = TextStyle(color = White, fontSize = 14.sp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = FuturisticGreen,
                                unfocusedBorderColor = LightGray,
                                cursorColor = FuturisticGreen,
                                containerColor = Color.Transparent
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expandedPayment.value,
                            onDismissRequest = { expandedPayment.value = false }
                        ) {
                            paymentOptions.forEach { mode ->
                                DropdownMenuItem(
                                    text = { Text(mode, style = TextStyle(color = MidnightBlue, fontSize = 14.sp)) },
                                    onClick = {
                                        selectedPayment.value = mode
                                        expandedPayment.value = false
                                    }
                                )
                            }
                        }
                    }

                    // Confirm Booking Button
                    Button(
                        onClick = { /* Handle confirm booking action */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = LightGreen,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Confirm Booking",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBookingPage() {
    BookingPage(navController = rememberNavController())
}


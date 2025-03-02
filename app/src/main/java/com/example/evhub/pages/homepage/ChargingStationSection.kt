package com.example.evhub.pages.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evhub.model.Station
import com.example.evhub.ui.theme.CharcoalBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.White

val sampleStations = listOf(
    Station(
        name = "Station A1",
        status = "Available",
        powerOutput = "150 kW",
        waitingTime = "0 min",
        queue = "No Queue",
        pricePerKwh = "₹12"
    ),
    Station(
        name = "Station A2",
        status = "Occupied",
        powerOutput = "150 kW",
        waitingTime = "25 min",
        queue = "No Queue",
        pricePerKwh = "₹12"
    ),
    Station(
        name = "Station A3",
        status = "Available",
        powerOutput = "150 kW",
        waitingTime = "0 min",
        queue = "No Queue",
        pricePerKwh = "₹12"
    )
)

@Composable
fun ChargingStationCard(station: Station, navController: NavController) {
    Card(
        modifier = Modifier
            .width(250.dp), // fixed width to allow horizontal scrolling
        shape = RoundedCornerShape(12.dp),
        colors = cardColors(containerColor = CharcoalBlue),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Name & Status row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = station.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = White
                )
                val statusColor = if (station.status == "Available") FuturisticGreen else Color.Red
                Text(
                    text = station.status,
                    style = MaterialTheme.typography.labelMedium,
                    color = statusColor
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Station details
            Text(
                text = "Power Output: ${station.powerOutput}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray
            )
            Text(
                text = "Waiting Time: ${station.waitingTime}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray
            )
            if (station.queue.isNotEmpty()) {
                Text(
                    text = station.queue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightGray
                )
            }
            Text(
                text = "Price/kWh: ${station.pricePerKwh}",
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Action button
            val buttonText = if (station.status == "Available") "Book Now" else "Currently In Use"
            Button(
                onClick = { navController.navigate("booking") },
                modifier = Modifier.fillMaxWidth(),
                enabled = (station.status == "Available"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGreen,
                    contentColor = White
                )
            ) {
                Text(buttonText, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
package com.example.evhub.pages.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.evhub.model.EVService
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.SlateBlue
import com.example.evhub.ui.theme.White

val dummyEVServices = listOf(
    EVService("Maintenance", "Schedule routine checkups for your EV"),
    EVService("Cleaning", "Premium cleaning and detailing services")
)

@Composable
fun EVServiceCard(service: EVService,navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleMedium,
                color = White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate("404")},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightGreen,
                    contentColor = White
                )
            ) {
                Text("Book Service", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

package com.example.evhub.pages.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.evhub.R
import com.example.evhub.model.Zonecard
import com.example.evhub.ui.theme.CharcoalBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGray
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.White

@Composable
fun ZoneCardUI(zonecard: Zonecard, navController: NavController) {
    Card(
        modifier = Modifier.width(250.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CharcoalBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image section with 16:9 aspect ratio
            Image(
                painter = zonecard.imagePainter,
                contentDescription = zonecard.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = zonecard.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = White,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Fixed lower section for description and button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)   // Fixed height for the lower area
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Description with a maximum of 2 lines (adjust as needed)
                Text(
                    text = zonecard.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = LightGray,
                    maxLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                // "View Menu" Button anchored at the bottom
                Button(
                    onClick = zonecard.onMenuClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FuturisticGreen,
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "View Menu",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

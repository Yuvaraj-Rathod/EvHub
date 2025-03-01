package com.example.evhub.pages

import android.app.DatePickerDialog
import android.hardware.lights.Light
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

// Dummy data models
data class Station(
    val name: String,
    val status: String,         // "Available" or "Occupied"
    val powerOutput: String,
    val waitingTime: String,
    val queue: String,
    val pricePerKwh: String
)
data class EVService(val name: String, val description: String)

private val sampleStations = listOf(
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

val dummyEVServices = listOf(
    EVService("Maintenance", "Schedule routine checkups for your EV"),
    EVService("Cleaning", "Premium cleaning and detailing services")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val greenGrad = Brush.horizontalGradient(
        colors = listOf(FuturisticGreen, White)
    )
    val zoneCardList = listOf(
        Zonecard(
            imagePainter = painterResource(R.drawable.macd),
            title = "McDonald's",
            description = "Enjoy your favorite burgers while your EV charges.",
            onMenuClick = { /* Handle 'View Menu' click */ }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.dominos),
            title = "Domino's",
            description = "Fresh pizzas delivered to your charging spot.",
            onMenuClick = { /* Handle 'View Menu' click */ }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.gamingzone),
            title = "Gaming Zone",
            description = "Fun arcade games while you wait.",
            onMenuClick = { /* Handle 'View Menu' click */ }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.kidsplayzone),
            title = "Kids Play Zone",
            description = "A safe place for kids to have fun.",
            onMenuClick = { /* Handle 'View Menu' click */ }
        )
    )
    Scaffold(
        topBar = {
            EvHubAppBar(
                onLeftIconClick = { /* Handle left icon click, e.g., open navigation drawer */ },
                onSearchClick = { /* Handle search action */ },
                onProfileClick = { /* Handle profile action */ }
            )
        },
        floatingActionButton = {
            GradientFAB(
                onClick = { /* Handle FAB action */ },
                icon = Icons.Filled.Notifications,
                contentDescription = "Notifications"
            )
        },
        containerColor = MidnightBlue
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Charging Stations Section
            item { SectionTitle(title = "Charging Stations") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(sampleStations) { station ->
                        ChargingStationCard(station)
                    }
                }
            }

            // EV Services Section
            item { SectionTitle(title = "EV Services") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(dummyEVServices) { service ->
                        EVServiceCard(service)
                    }
                }
            }

            //Zones
            item { SectionTitle(title = "Other Zones") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(zoneCardList) { zone ->
                        ZoneCardUI(zonecard = zone)
                    }
                }
            }

            }
        }
    }


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = White,
        modifier = Modifier.padding(vertical = 6.dp)
    )
}

@Composable
fun ChargingStationCard(station: Station) {
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
                onClick = { /* Handle booking or usage info */ },
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

@Composable
fun EVServiceCard(service: EVService) {
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
                onClick = { /* TODO: Handle service booking */ },
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvHubAppBar(
    onLeftIconClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            ElevatedIcon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                onClick = onLeftIconClick
            )
        },
        title = {
            // Shift the title a bit to the left with an offset modifier
            Row(modifier = Modifier.offset(x = (-16).dp)) {
                Text(
                    text = "BatteryFy",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp,
                        brush = Brush.sweepGradient(
                            colors = listOf(LightGreen, AzureBlue)
                        )
                    )
                )
            }
        },
        actions = {
            ElevatedIcon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                onClick = onSearchClick
            )
            Spacer(modifier = Modifier.width(13.dp))
            ElevatedIcon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                onClick = onProfileClick
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MidnightBlue
        )
    )
}

@Composable
fun ElevatedIcon(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    val greenGrad = Brush.horizontalGradient(
        colors = listOf(FuturisticGreen, White)
    )
    Box(
        modifier = Modifier
            .size(40.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            color = LightGreen
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

data class Zonecard(val imagePainter: Painter,
                    val title: String,
                    val description: String,
                    val onMenuClick: () -> Unit)
@Composable
fun ZoneCardUI(
    zonecard: Zonecard
) {
    Card(
        modifier = Modifier.width(250.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CharcoalBlue),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image now fills the width with a 16:9 aspect ratio
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

            // Description
            Text(
                text = zonecard.description,
                style = MaterialTheme.typography.bodyMedium,
                color = LightGray,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // "View Menu" Button
            Button(
                onClick = zonecard.onMenuClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
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

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Composable
fun GradientFAB(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(50.dp) // Default FAB size
            .clip(RoundedCornerShape(15.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Cyan, LightGreen)
                )
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    HomePage()
}
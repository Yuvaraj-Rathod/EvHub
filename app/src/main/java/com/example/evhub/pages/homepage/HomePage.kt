package com.example.evhub.pages.homepage

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.evhub.R
import com.example.evhub.model.EVService
import com.example.evhub.model.Station
import com.example.evhub.model.Zonecard
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
fun HomePage(navController: NavController) {
    val zoneCardList = listOf(
        Zonecard(
            imagePainter = painterResource(R.drawable.macd),
            title = "McDonald's",
            description = "Enjoy your favorite burgers while your EV charges.",
            onMenuClick = { navController.navigate("404") }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.dominos),
            title = "Domino's",
            description = "Fresh pizzas delivered to your charging spot.",
            onMenuClick = { navController.navigate("404") }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.gamingzone),
            title = "Gaming Zone",
            description = "Fun arcade games while you wait.",
            onMenuClick = { navController.navigate("404") }
        ),
        Zonecard(
            imagePainter = painterResource(R.drawable.kidsplayzone),
            title = "Kids Play Zone",
            description = "A safe place for kids to have fun.",
            onMenuClick = { navController.navigate("404") }
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
                onClick = { navController.navigate("booking") },
                icon = Icons.Filled.ShoppingCart,
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
                        ChargingStationCard(station,navController)
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
                        EVServiceCard(service,navController)
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
                        ZoneCardUI(zonecard = zone,navController)
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

    HomePage(navController = rememberNavController())
}
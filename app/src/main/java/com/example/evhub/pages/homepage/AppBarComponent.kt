package com.example.evhub.pages.homepage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evhub.ui.theme.AzureBlue
import com.example.evhub.ui.theme.DarkBlue
import com.example.evhub.ui.theme.FuturisticGreen
import com.example.evhub.ui.theme.LightGreen
import com.example.evhub.ui.theme.MidnightBlue
import com.example.evhub.ui.theme.SlateBlue
import com.example.evhub.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvHubAppBar(
    onLeftIconClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        // Add horizontal padding so the app bar content is inset
        modifier = Modifier.padding(horizontal = 10.dp),
        navigationIcon = {
            ElevatedIcon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                onClick = onLeftIconClick,
                modifier = Modifier.padding(start = 2.dp)
            )
        },
        title = {
            // Title with gradient text; here we remove the left offset,
            // relying on the app bar's padding instead.
            Text(
                text = "BatteryFy",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(AzureBlue, Color.Cyan)
                    )
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        actions = {
            ElevatedIcon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search",
                onClick = onSearchClick,
                modifier = Modifier.padding(end = 2.dp)
            )
            Spacer(modifier = Modifier.width(13.dp))
            ElevatedIcon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                onClick = onProfileClick,
                modifier = Modifier.padding(end = 2.dp)
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
            color = SlateBlue
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

@Preview
@Composable
private fun AppBarPreview() {
EvHubAppBar(onLeftIconClick = {}, onProfileClick = {}, onSearchClick = {})
}
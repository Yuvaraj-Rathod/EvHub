package com.example.evhub.model

import androidx.compose.ui.graphics.painter.Painter

data class Station(
    val name: String,
    val status: String,         // "Available" or "Occupied"
    val powerOutput: String,
    val waitingTime: String,
    val queue: String,
    val pricePerKwh: String
)
data class EVService(
    val name: String,
    val description: String
)

data class Zonecard(
    val imagePainter: Painter,
    val title: String,
    val description: String,
    val onMenuClick: () -> Unit
)

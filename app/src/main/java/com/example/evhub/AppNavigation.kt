package com.example.evhub

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evhub.pages.UnavailablePage
import com.example.evhub.pages.bookingpage.BookingPage
import com.example.evhub.pages.homepage.HomePage

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController,"home") {
        composable("home") {
            HomePage(navController = navController)
        }
        composable("booking") {
            BookingPage(navController = navController)
        }
        composable("404") {
            UnavailablePage(navController = navController)
        }
    }
}

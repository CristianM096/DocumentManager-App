package com.sophos.documentmanager.ui.navigation

sealed class Destinations(
    val route: String,
){
    object Login:Destinations("Login")
    object Home:Destinations("Home")
    object DocumentShow:Destinations("DocumentShow")
    object DocumentCreate:Destinations("DocumentCreate")
    object OfficeShow:Destinations("OfficeShow")
    object Copy:Destinations("Copy")
}

package com.sophos.documentmanager.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sophos.documentmanager.ui.view.document.DocumentCreateScreen
import com.sophos.documentmanager.ui.view.document.DocumentShowScreen
import com.sophos.documentmanager.ui.view.login.HomeScreen
import com.sophos.documentmanager.ui.view.login.LoginScreen
import com.sophos.documentmanager.ui.view.office.OfficeShowScreen
import com.sophos.documentmanager.ui.viewmodel.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavigation(
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    documentCreateViewModel: DocumentCreateViewModel,
    documentShowViewModel: DocumentShowViewModel,
    officeShowViewModel: OfficeShowViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.Login.route){
        composable(route = Destinations.Login.route){
            //"+{text}",arguments = listOf(navArgument(name="text") { type = NavType.StringType}
            LoginScreen(navController,loginViewModel)
            //,it.arguments?.getString("text"))
        }
        composable(
            route = Destinations.Home.route + "/{auth}",
            arguments = listOf(navArgument(name = "auth"){type = NavType.StringType})
        ){
            val auth = it.arguments?.getString("auth")
            HomeScreen(navController,homeViewModel,auth)
        }
        composable(
            route = Destinations.DocumentShow.route + "/{auth}",
            arguments = listOf(navArgument(name = "auth"){type = NavType.StringType})
        ){
            val auth = it.arguments?.getString("auth")
            DocumentShowScreen(navController,documentShowViewModel,auth)
        }
        composable(

            route = Destinations.DocumentCreate.route+ "/{auth}",
            arguments = listOf(navArgument(name = "auth"){type = NavType.StringType})
        ){
            val auth = it.arguments?.getString("auth")
            DocumentCreateScreen(navController,documentCreateViewModel,auth)
        }
        composable(
            route = Destinations.OfficeShow.route+ "/{auth}",
            arguments = listOf(navArgument(name = "auth"){type = NavType.StringType})
        ){
            val auth = it.arguments?.getString("auth")
            OfficeShowScreen(navController,officeShowViewModel,auth)
        }
    }
}
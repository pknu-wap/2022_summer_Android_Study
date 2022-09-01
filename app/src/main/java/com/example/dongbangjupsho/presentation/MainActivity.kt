package com.example.dongbangjupsho.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dongbangjupsho.presentation.home.HomeScreen
import com.example.dongbangjupsho.presentation.home.HomeViewModel
import com.example.dongbangjupsho.presentation.home.UserEnterEvent
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpViewModel
import com.example.dongbangjupsho.presentation.user.signin.UserSignInScreen
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpScreen
import com.example.dongbangjupsho.presentation.util.Screen
import com.example.dongbangjupsho.ui.theme.DongBangJupShoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.onEvent(UserEnterEvent.LoadLocation)
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
        setContent {
            DongBangJupShoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.UserSignInScreen.route
                    ){
                        composable(route = Screen.UserSignInScreen.route){
                            UserSignInScreen(navController = navController)
                        }
                        composable(route = Screen.UserSignUpScreen.route){
                            UserSignUpScreen(navController = navController)
                        }
                        composable(route = Screen.HomeScreen.route){
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

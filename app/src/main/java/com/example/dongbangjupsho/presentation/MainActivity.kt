package com.example.dongbangjupsho.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpViewModel
import com.example.dongbangjupsho.presentation.user.signin.UserSignInScreen
import com.example.dongbangjupsho.presentation.user.signup.UserSignUpScreen
import com.example.dongbangjupsho.presentation.util.Screen
import com.example.dongbangjupsho.ui.theme.DongBangJupShoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userSignUpViewModel : UserSignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

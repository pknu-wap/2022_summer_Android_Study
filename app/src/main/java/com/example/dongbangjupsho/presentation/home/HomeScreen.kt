package com.example.dongbangjupsho.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
    fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel() //todo state hoisting mainActivity
) {
        //firebase db
        val isFirstVisit = true

    Scaffold(
    ){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Text("현재 동방 인원 : ${viewModel.todayEnterPeople}")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                          //todo find Location
                          viewModel.loadLocation()
                     },
            ){
                if(isFirstVisit) {
                    Text("입장")
                }else{
                    Text("퇴장")
                }
            }
        }
    }
}
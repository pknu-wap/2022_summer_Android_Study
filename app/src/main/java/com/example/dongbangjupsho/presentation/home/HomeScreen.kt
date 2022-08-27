package com.example.dongbangjupsho.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
    fun HomeScreen(
        navController: NavController
    ) {
        //firebase db
        val totalCount = 32
        val isFirstVisit = true

    Scaffold(
    ){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Text("현재 동방 인원 : $totalCount")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                          //todo find Location
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
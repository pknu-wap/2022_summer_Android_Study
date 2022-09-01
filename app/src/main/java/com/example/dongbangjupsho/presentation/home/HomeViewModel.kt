package com.example.dongbangjupsho.presentation.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.domain.location.LocationTracker
import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val databaseRepository: FirebaseDatabaseRepository
): ViewModel(){

    var todayEnterPeople by mutableStateOf("")
        private set

    init{
        getTodayEnterPeople()
    }

    private fun getTodayEnterPeople(){
        viewModelScope.launch {
            databaseRepository.getTodayEnterPeople()?.let{ peopleCount ->
                todayEnterPeople = peopleCount
            }
        }
    }
    fun loadLocation(){
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let{ location ->
                Log.d("tag", location.longitude.toString())
                Log.d("tag", location.latitude.toString())
            }
        }
    }
}
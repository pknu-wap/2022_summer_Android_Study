package com.example.dongbangjupsho.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.domain.location.LocationTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationTracker: LocationTracker
): ViewModel(){

    fun loadLocation(){
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let{ location ->
                Log.d("tag", location.longitude.toString())
                Log.d("tag", location.latitude.toString())
            }
        }
    }
}
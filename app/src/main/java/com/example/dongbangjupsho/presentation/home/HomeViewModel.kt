package com.example.dongbangjupsho.presentation.home

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongbangjupsho.domain.location.LocationTracker
import com.example.dongbangjupsho.domain.model.UserEnterInfo
import com.example.dongbangjupsho.domain.repository.FirebaseDatabaseRepository
import com.example.dongbangjupsho.domain.util.FirebaseManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val databaseRepository: FirebaseDatabaseRepository,
    private val prefs : SharedPreferences
): ViewModel(){

    var todayEnterPeople by mutableStateOf("")
        private set

    init{
        getTodayEnterPeople()
    }

    fun onEvent(event : UserEnterEvent){
        when(event){
            is UserEnterEvent.LoadLocation -> {
                loadLocation()
            }
            is UserEnterEvent.SetUserEnter -> {
                setTodayEnterPeople()
            }
        }
    }

    private fun getTodayEnterPeople(){
        viewModelScope.launch {
            val time = SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis())
            databaseRepository.getTodayEnterPeople(time).collectLatest {
                it?.let{
                    todayEnterPeople = it
                }
            }
        }
    }
    private fun setTodayEnterPeople(){
        viewModelScope.launch {
            val time = SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis())
            prefs.getString("nickName", null)?.let{ uid ->
                databaseRepository.setTodayEnterPeople(UserEnterInfo(
                    uid = FirebaseManager.firebaseAuth.uid.toString(),
                    timeStamp = time,
                    nickName = uid
                ))
            }
        }
    }
    private fun loadLocation(){
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let{ location ->
                Log.d("tag", location.longitude.toString())
                Log.d("tag", location.latitude.toString())
            }
        }
    }
}
package com.example.wapmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.wapmanager.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding //optional 안씀 becuase lateinit
    val test = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //네비게이션 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        //네비게이션 컨트롤러
        val navController = navHostFragment.navController

        //바텀 네비게이션 뷰와 네비게이션을 묶어줌
        NavigationUI.setupWithNavController(mBinding.myBottomNav, navController)
//        setMainFragment()
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("Hello,Firebase!")

        val userEmail = intent.getStringExtra("email").toString()

    }





}


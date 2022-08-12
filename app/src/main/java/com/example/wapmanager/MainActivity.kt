package com.example.wapmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMainFragment()
    }

    fun setMainFragment(){
        val mainFragment: MainFragment = MainFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView,mainFragment)
        transaction.commit()
    }
}


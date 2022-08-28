package com.example.wapmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wapmanager.databinding.ActivityMainBinding
import com.example.wapmanager.loginFragments.SigninFragment
import com.example.wapmanager.loginFragments.SignupFragment
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    //var signupFragment: SignupFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as SignupFragment
    //var signinFragment: SigninFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as SigninFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //setFragment()
    }

    //fun setFragment(){
//        val signinFragment:SigninFragment = SigninFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.framelayout, signinFragment)
//        transaction.commit()
    //}

    fun goSignup(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView,signupFragment)
        transaction.addToBackStack("signup")
        transaction.commit()
    }

    fun goBack(){
        onBackPressed()
    }


}
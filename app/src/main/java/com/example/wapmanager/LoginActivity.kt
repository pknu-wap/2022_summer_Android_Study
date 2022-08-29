package com.example.wapmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wapmanager.databinding.ActivityLoginBinding
import com.example.wapmanager.databinding.ActivityMainBinding
import com.example.wapmanager.loginFragments.SigninFragment
import com.example.wapmanager.loginFragments.SignupFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy{ActivityLoginBinding.inflate(layoutInflater)}
    private var auth:FirebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        //setFragment()
    }

    //fun setFragment(){
//        val signinFragment:SigninFragment = SigninFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.framelayout, signinFragment)
//        transaction.commit()
    //}

    fun goSignup(){
        val signupFragment = SignupFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainerView,signupFragment)
        transaction.addToBackStack("signup")
        transaction.commit()
    }

    fun goBack(){
        onBackPressed()
    }

//    //현재 인증된 사용자라면 main 실행
//    override fun onStart(){
//        super.onStart()
//        if(auth.currentUser != null){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//    }

}
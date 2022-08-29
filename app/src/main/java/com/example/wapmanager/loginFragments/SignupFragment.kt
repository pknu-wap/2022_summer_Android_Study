package com.example.wapmanager.loginFragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wapmanager.LoginActivity
import com.example.wapmanager.R
import com.example.wapmanager.databinding.ActivityLoginBinding
import com.example.wapmanager.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignupFragment : Fragment() {
    private val binding by lazy{ FragmentSignupBinding.inflate(layoutInflater)}
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth
        binding.btnSignup.setOnClickListener{
            registerUser()
        }
        return binding.root
    }

    private fun registerUser(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            Toast.makeText(
                                activity,
                                "회원가입이 성공하였습니다.", Toast.LENGTH_SHORT
                            ).show()
                        }
                    withContext(Dispatchers.Main) {

                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            activity,
                            "회원가입이 실패하였습니다.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
    private fun checkLoggedInstate(){

    }
}
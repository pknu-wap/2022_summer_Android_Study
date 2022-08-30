package com.example.wapmanager.loginFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wapmanager.LoginActivity
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentSigninBinding
import com.example.wapmanager.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class SigninFragment : Fragment() {
    var loginActivity: LoginActivity? = null
    private val binding by lazy{ FragmentSigninBinding.inflate(layoutInflater)}
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth
        binding.btnGoSignup.setOnClickListener{loginActivity?.goSignup()}
        binding.btnSignin.setOnClickListener{
            userLogin()
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is LoginActivity) loginActivity = context
    }

    private fun userLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task->
                            if(task.isSuccessful) {
                                Toast.makeText(
                                    activity,
                                    "로그인에 성공하였습니다.", Toast.LENGTH_SHORT
                                ).show()
                            handleSuccessLogin()
                            }
                            else{
                                Toast.makeText(
                                    activity,
                                    "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            activity,
                            "로그안에 실패하였습니다.", Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun handleSuccessLogin(){
//
    }
}
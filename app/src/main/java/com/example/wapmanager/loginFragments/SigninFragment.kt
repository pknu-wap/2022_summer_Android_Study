package com.example.wapmanager.loginFragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wapmanager.LoginActivity
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentSigninBinding


class SigninFragment : Fragment() {
    var loginActivity: LoginActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSigninBinding.inflate(inflater,container,false)
        binding.btnGoSignup.setOnClickListener{loginActivity?.goSignup()}
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is LoginActivity) loginActivity = context
    }


}
package com.example.wapmanager.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wapmanager.MainActivity
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentListBinding
import com.example.wapmanager.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

        private var mBinding : FragmentMyPageBinding? = null
        lateinit var mainActivity: MainActivity

            override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
                override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = FragmentMyPageBinding.inflate(inflater, container, false)
                    binding.txMyEmail.text = mainActivity.intent.getStringExtra("email")
            mBinding = binding
            return mBinding?.root
        }


                override fun onDestroyView() {
            mBinding = null
            super.onDestroyView()
        }


}
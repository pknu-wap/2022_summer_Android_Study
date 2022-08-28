package com.example.wapmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentListBinding
import com.example.wapmanager.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

        private var mBinding : FragmentMyPageBinding? = null

                override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = FragmentMyPageBinding.inflate(inflater, container, false)

            mBinding = binding

            return mBinding?.root
        }

                override fun onDestroyView() {
            mBinding = null
            super.onDestroyView()
        }

}
package com.example.wapmanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentEventBinding

class EventFragment: Fragment(){
    private var mBinding : FragmentEventBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
}

override fun onDestroyView() {
    mBinding = null
    super.onDestroyView()
}

}
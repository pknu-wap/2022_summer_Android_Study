package com.example.wapmanager.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wapmanager.CustomAdapter
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentListBinding
import com.example.wapmanager.model.User

class ListFragment : Fragment() {

    private var mBinding : FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater, container, false)

        mBinding = binding
        val data:MutableList<User> = loadData()
        var adapter = CustomAdapter()
        adapter.listData = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    fun loadData(): MutableList<User>{
        val data:MutableList<User> = mutableListOf()
        for(i in 1..100) {
            val id = "0000${i}"
            val name = "홍길동${i}"
            val date = System.currentTimeMillis()
            var user = User(id,name,date)
            data.add(user)
        }
        return data
    }


}
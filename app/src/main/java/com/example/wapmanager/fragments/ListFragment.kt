package com.example.wapmanager.fragments

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wapmanager.CustomAdapter
import com.example.wapmanager.R
import com.example.wapmanager.databinding.FragmentListBinding
import com.example.wapmanager.model.User
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ListFragment : Fragment() {

    private var mBinding : FragmentListBinding? = null
    private val currentUserDB : DatabaseReference by lazy{
        Firebase.database.reference.child("User")}
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
        writeNewUser("000","201912345","구교황", 0)
        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    fun loadData(): MutableList<User>{
        val data:MutableList<User> = mutableListOf()
        val user1 = User("000","201912345","구교황", 0)
        val user2 = User("001","201412566","박정빈", 0)
        data.add(user1)
        data.add(user2)

//        for (i in 1..data.size) {
//            = data.get(i)
//        }

//        database.child("User").get().addOnSuccessListener {
//            val user = it.getValue<User>()
//        }
////            val id = "0000${i}"
////            val name = "홍길동${i}"
////            val date = System.currentTimeMillis()
////            var user = User("",id,name,date)
////            data.add(user)
////        }

        return data
    }

    fun writeNewUser(profile:String ,userId: String, userName: String, clubUpDate:Long) {
        val user1 = User(profile,userId,userName,clubUpDate)
        currentUserDB.child("user02").setValue(user1)

    }




}
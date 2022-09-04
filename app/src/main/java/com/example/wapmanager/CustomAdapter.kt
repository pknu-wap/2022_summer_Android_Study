package com.example.wapmanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wapmanager.databinding.ItemRecyclerBinding
import com.example.wapmanager.model.User
import java.text.SimpleDateFormat

class CustomAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = listData.get(position)
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}

class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
    fun setUser(user:User){
        binding.txStudentId.text = "${user.studentId}"
        binding.txName.text = "${user.studentName}"

        var sdf = SimpleDateFormat("yyyy/MM/dd")
        var formattedDate = sdf.format(user.clubUpDate)
        binding.txClupUpDate.text = formattedDate
    }
}
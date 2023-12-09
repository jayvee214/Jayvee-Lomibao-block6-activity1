package com.example.saloonbookingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saloonbookingapp.databinding.ServicesItemBinding
import com.example.saloonbookingapp.model.Services

class ServicesAdapter(private val context: Context, private val ListService: MutableList<Services>):
    RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemList = ServicesItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ServiceViewHolder(itemList)
    }

    override fun getItemCount() = ListService.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.ImgService.setImageResource(ListService[position].img!!)
        holder.txtService.text = ListService[position].name
    }

    inner class ServiceViewHolder(binding: ServicesItemBinding): RecyclerView.ViewHolder(binding.root){
        val ImgService = binding.imgService
        val txtService = binding.txtService

    }
}
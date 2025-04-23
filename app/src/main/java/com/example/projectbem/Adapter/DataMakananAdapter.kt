package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.divisi.MakananResponseItem
import com.example.projectbem.R

class DataMakananAdapter: RecyclerView.Adapter<DataMakananAdapter.ViewHolder>() {
    private val makananList = mutableListOf<MakananResponseItem>()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tv_judul)
        val tvDesk: TextView = itemView.findViewById(R.id.tv_deskripsi)
        val ivFoto: ImageView = itemView.findViewById(R.id.iv_foto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_makanan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = makananList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = makananList[position]
        holder.tvJudul.text = item.namaMakanan
        holder.tvDesk.text = item.deskripsi
        Glide.with(holder.itemView.context)
            .load(item.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivFoto)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<MakananResponseItem>) {
        makananList.clear()
        makananList.addAll(newList)
        notifyDataSetChanged()
    }
}
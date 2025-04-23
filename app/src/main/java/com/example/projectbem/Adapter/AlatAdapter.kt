package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.response.divisi.AlatResponseItem
import com.example.projectbem.Data.response.divisi.ObatResponseItem
import com.example.projectbem.R

class AlatAdapter: RecyclerView.Adapter<AlatAdapter.ViewHolder>() {
    private val alatList = mutableListOf<AlatResponseItem>()
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvAlat: TextView = itemView.findViewById(R.id.tv_alat)
        val tvKondisi: TextView = itemView.findViewById(R.id.tv_kondisi)
        val tvStok: TextView = itemView.findViewById(R.id.tv_stok)
        val tvKeterangan: TextView = itemView.findViewById(R.id.tv_keterangan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = alatList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alatList[position]
        holder.tvAlat.text = item.namaAlat
        holder.tvKondisi.text = "Kondisi: ${item.kondisi}"
        holder.tvStok.text = "Stok: ${item.stok}"
        holder.tvKeterangan.text = "Keterangan: ${item.keterangan}"
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<AlatResponseItem>) {
        alatList.clear()
        alatList.addAll(newList)
        notifyDataSetChanged()
    }
}
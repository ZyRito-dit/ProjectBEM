package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.response.divisi.DataMenu
import com.example.projectbem.R

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private val menuList = mutableListOf<DataMenu>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<DataMenu>) {
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMenu: TextView = itemView.findViewById(R.id.tv_menu)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
        val tvKeterangan: TextView = itemView.findViewById(R.id.tv_ket)
        val tvWaktu: TextView = itemView.findViewById(R.id.tv_wak)
        val tvHari: TextView = itemView.findViewById(R.id.tv_hari)
        val tvPekan: TextView = itemView.findViewById(R.id.tv_pekan)
        val tvBulan: TextView = itemView.findViewById(R.id.tv_bulan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = menuList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = menuList[position]
        holder.tvMenu.text = "Menu: ${item.menu}"
        holder.tvStatus.text = "Status: ${item.statusKesesuaian}"
        holder.tvKeterangan.text = "Keterangan: ${item.keterangan}"
        holder.tvWaktu.text = "Waktu: ${item.waktu}"
        holder.tvHari.text = "Hari: ${item.hari}"
        holder.tvPekan.text = "Pekan: ${item.pekan}"
        holder.tvBulan.text = item.bulan

    }
}
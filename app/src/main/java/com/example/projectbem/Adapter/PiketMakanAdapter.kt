package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.response.divisi.DataItem
import com.example.projectbem.R

class PiketMakanAdapter : RecyclerView.Adapter<PiketMakanAdapter.ViewHolder>() {

    private val piketList = mutableListOf<DataItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<DataItem>) {
        piketList.clear()
        piketList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaKelompok: TextView = itemView.findViewById(R.id.tv_kelompok)
        val tvAnggota: TextView = itemView.findViewById(R.id.tv_anggota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_piket, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = piketList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = piketList[position]
        holder.tvNamaKelompok.text = item.namaKelompok
        holder.tvAnggota.text = "Anggota: \n${item.anggotaKelompok}"
    }
}
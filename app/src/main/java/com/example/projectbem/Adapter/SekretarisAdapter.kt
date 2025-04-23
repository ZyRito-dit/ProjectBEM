package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.response.divisi.Notulen
import com.example.projectbem.R

class SekretarisAdapter(private var listNotulen: List<Notulen>) : RecyclerView.Adapter<SekretarisAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masalah = itemView.findViewById<TextView>(R.id.tv_masalah)
        val solusi = itemView.findViewById<TextView>(R.id.tv_solusi)
        val divisi = itemView.findViewById<TextView>(R.id.tv_divisi)
        val terlaksana = itemView.findViewById<TextView>(R.id.tv_laksana)
        val keterangan = itemView.findViewById<TextView>(R.id.tv_keterangan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sekretaris, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listNotulen.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notulen = listNotulen[position]
        holder.masalah.text = "Permasalahan :\n${notulen.permasalahan}"
        holder.solusi.text = "Solusi : ${notulen.solusi}"
        holder.divisi.text = "Divisi ${notulen.divisi}"
        holder.terlaksana.text = "Terlaksana : ${notulen.terlaksana}"
        holder.keterangan.text = "Keterangan : ${notulen.keterangan}"

    }
    fun updateData(newList: List<Notulen>) {
        listNotulen = newList
        notifyDataSetChanged()
    }
}
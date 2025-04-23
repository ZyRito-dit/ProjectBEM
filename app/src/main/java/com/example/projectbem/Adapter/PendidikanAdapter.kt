package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.divisi.PendidikanResponseItem
import com.example.projectbem.R

class PendidikanAdapter: RecyclerView.Adapter<PendidikanAdapter.ViewHolder>(){
    private val barangList = mutableListOf<PendidikanResponseItem>()
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: PendidikanResponseItem) {
            val barang = itemView.findViewById<TextView>(R.id.tv_barang)
            val kondisi = itemView.findViewById<TextView>(R.id.tv_kondisi)
            val keterangan = itemView.findViewById<TextView>(R.id.tv_keterangan)
            val stok = itemView.findViewById<TextView>(R.id.tv_stok)
            val imageBarang = itemView.findViewById<ImageView>(R.id.iv_image)

            barang.text = "Nama Barang: ${item.namaBarang}"
            kondisi.text = "Kondisi: ${item.kondisi}"
            keterangan.text = "Keterangan: ${item.keterangan}"
            stok.text = "Stok: ${item.stok}"


            Glide.with(itemView.context)
                .load(item.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageBarang)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pendidikan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = barangList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(barangList[position])
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<PendidikanResponseItem>) {
        barangList.clear()
        barangList.addAll(newList)
        notifyDataSetChanged()
    }
}
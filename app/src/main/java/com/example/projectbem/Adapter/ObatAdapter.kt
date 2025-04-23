package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Adapter.DataMakananAdapter.ViewHolder
import com.example.projectbem.Data.response.divisi.ObatResponseItem
import com.example.projectbem.Data.response.divisi.PendidikanResponseItem
import com.example.projectbem.R
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class ObatAdapter: RecyclerView.Adapter<ObatAdapter.ViewHolder>() {
    private val obatList = mutableListOf<ObatResponseItem>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvObat: TextView = itemView.findViewById(R.id.tv_obat)
        val tvKet: TextView = itemView.findViewById(R.id.tv_keterangan)
        val tvStok: TextView = itemView.findViewById(R.id.tv_stok)
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val ivFoto: ImageView = itemView.findViewById(R.id.iv_obat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_obat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = obatList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = obatList[position]
        holder.tvObat.text = item.namaObat
        holder.tvStok.text = "Stok: ${item.stok}"
        holder.tvKet.text = item.keterangan
        holder.tvTanggal.text = "Kadaluarsa: ${item.tglKadaluarsa?.let { formatTanggal(it) }}"
        Glide.with(holder.itemView.context)
            .load(item.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.ivFoto)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<ObatResponseItem>) {
        obatList.clear()
        obatList.addAll(newList)
        notifyDataSetChanged()
    }

    fun formatTanggal(tanggal: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
        outputFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

        val date = inputFormat.parse(tanggal)
        return outputFormat.format(date!!)
    }
}
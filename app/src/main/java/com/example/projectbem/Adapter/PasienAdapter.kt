package com.example.projectbem.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.response.divisi.ObatResponseItem
import com.example.projectbem.Data.response.divisi.SakitResponse
import com.example.projectbem.R

class PasienAdapter: RecyclerView.Adapter<PasienAdapter.ViewHolder>() {
    private val pasienList = mutableListOf<SakitResponse>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val tvPasien: TextView = itemView.findViewById(R.id.tv_pasien)
        val tvKelas: TextView = itemView.findViewById(R.id.tv_kelas)
        val tvPenyakit: TextView = itemView.findViewById(R.id.tv_penyakit)
        val tvPenanganan: TextView = itemView.findViewById(R.id.tv_penanganan)
        val tvPetugas: TextView = itemView.findViewById(R.id.tv_petugas)
        val tvStatus: TextView = itemView.findViewById(R.id.tv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pasien, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pasienList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pasienList[position]
        holder.tvTanggal.text = item.tanggal
        holder.tvPasien.text = item.nama
        holder.tvKelas.text = "Kelas ${item.kelas}"
        holder.tvPenyakit.text = "Sakit ${item.jenisPenyakit}"
        holder.tvPenanganan.text = "Penanganan${item.penanganan}"
        holder.tvPetugas.text = "Petugas ${item.petugas}"
        holder.tvStatus.text = "Status: ${item.statusPenyembuhan}"
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<SakitResponse>) {
        pasienList.clear()
        pasienList.addAll(newList)
        notifyDataSetChanged()
    }
}
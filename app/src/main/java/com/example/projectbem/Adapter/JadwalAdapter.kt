package com.example.projectbem.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.DummyData.Jadwal
import com.example.projectbem.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class JadwalAdapter(private val listJadwal: List<Jadwal>) :
    RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaMapel: TextView = view.findViewById(R.id.tvNamaMapel)
        val tvBab: TextView = view.findViewById(R.id.tvBab)
        val tvGuru: TextView = view.findViewById(R.id.tvGuru)
        val cardView: CardView = view.findViewById(R.id.cardJadwal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jadwal, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listJadwal.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listJadwal[position]
        holder.tvNamaMapel.text = item.namaKegiatan
        holder.tvBab.text = item.deskripsi
        holder.tvGuru.text = item.namaDivisi
        holder.cardView.setCardBackgroundColor(Color.WHITE)

        val now = getCurrentTimeAsDate()
        val jamMulai = parseTime(item.jamMulai)
        val jamSelesai = parseTime(item.jamSelesai)

        if (jamMulai != null && jamSelesai != null && now != null) {
            when {
                now.before(jamMulai) -> {
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                }
                now.after(jamSelesai) -> {
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#F8BBD0"))
                }
                now.after(jamMulai) && now.before(jamSelesai) -> {
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
                }
            }
        }
    }



    private fun getCurrentTimeAsDate(): Date? {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return try {
            sdf.parse(sdf.format(Date()))
        } catch (e: Exception) {
            null
        }
    }

    private fun parseTime(time: String): Date? {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return try {
            sdf.parse(time)
        } catch (e: Exception) {
            null
        }
    }
}

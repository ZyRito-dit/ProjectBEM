package com.example.projectbem.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.event.DataItem
import com.example.projectbem.R

class EventAdapter(private val list: List<DataItem>, private val onItemClick: (DataItem) -> Unit) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgEvent: ImageView = itemView.findViewById(R.id.imgEvent)
        val txtJudul: TextView = itemView.findViewById(R.id.txtJudulEvent)
        val txtDeskripsi: TextView = itemView.findViewById(R.id.txtDeskripsiEvent)
        fun bind(event: DataItem) {
            txtJudul.text = event.judulKegiatan
            txtDeskripsi.text = event.ringkasan

            Glide.with(itemView.context)
                .load(event.gambarLogo)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imgEvent)

            itemView.setOnClickListener { onItemClick(event) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_home, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
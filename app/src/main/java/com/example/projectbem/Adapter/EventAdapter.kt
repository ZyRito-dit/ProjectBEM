package com.example.projectbem.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbem.Data.EventModel
import com.example.projectbem.R

class EventAdapter(private val listEvent: List<EventModel>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgEvent: ImageView = itemView.findViewById(R.id.imgEvent)
        val txtJudul: TextView = itemView.findViewById(R.id.txtJudulEvent)
        val txtDeskripsi: TextView = itemView.findViewById(R.id.txtDeskripsiEvent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event_home, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = listEvent[position]
        holder.imgEvent.setImageResource(event.imageResId)
        holder.txtJudul.text = event.judul
        holder.txtDeskripsi.text = event.deskripsi
    }

    override fun getItemCount(): Int = listEvent.size
}

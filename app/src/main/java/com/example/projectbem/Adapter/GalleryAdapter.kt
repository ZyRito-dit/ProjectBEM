package com.example.projectbem.Home.dashboard_item

import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.ResponseGalleryItem
import com.example.projectbem.Home.dashboard_item.Gallery.FullScreenImageActivity
import com.example.projectbem.R
import java.util.Locale

class GalleryAdapter(
    private val context: Context,
    private var imageList: List<ResponseGalleryItem>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    fun updateData(newList: List<ResponseGalleryItem>) {
        imageList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)




        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedItem = imageList[position]

                    val rawDate = clickedItem.createdAt
                    val sdfInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                    val sdfDate = SimpleDateFormat("dd MMM", Locale.getDefault())
                    val sdfTime = SimpleDateFormat("HH:mm", Locale.getDefault())

                    val dateObj = sdfInput.parse(rawDate)
                    val formattedDate = sdfDate.format(dateObj!!)
                    val formattedTime = sdfTime.format(dateObj)

                    val imageUrls = ArrayList(imageList.map { item ->
                        item.image
                    })

                    val imageIds = ArrayList(imageList.map { it.id.toString() })


                    val intent = Intent(context, FullScreenImageActivity::class.java)
                    intent.putStringArrayListExtra("image_list", imageUrls)
                    intent.putStringArrayListExtra("image_id_list", imageIds)
                    intent.putExtra("position", position)
                    intent.putExtra("image_date", formattedDate)
                    intent.putExtra("image_time", formattedTime)
                    context.startActivity(intent)

                }
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)
        return ViewHolder(view)


    }



    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageList[position]

        Glide.with(context)
            .load(item.image)
            .placeholder(R.drawable.anby)
            .error(R.drawable.aether_post)
            .into(holder.imageView)
    }



}


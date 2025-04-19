package com.example.projectbem.Home.dashboard_item

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.Home.dashboard_item.Gallery.FullScreenImageActivity
import com.example.projectbem.R
import com.example.projectbem.databinding.ItemGalleryBinding

class GalleryAdapter(
    private val context: Context,
    private val imageList: List<Int>
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imageView.setOnClickListener {
                val intent = Intent(context, FullScreenImageActivity::class.java)
                intent.putIntegerArrayListExtra("image_list", ArrayList(imageList))
                intent.putExtra("position", adapterPosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Glide.with(context)
            .load(imageList[position])
            .into(holder.binding.imageView)
    }

    override fun getItemCount(): Int = imageList.size
}

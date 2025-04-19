package com.example.projectbem.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbem.databinding.ItemFullScreenImageBinding

class FullScreenImageAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<FullScreenImageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemFullScreenImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemFullScreenImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(images[position])
            .into(holder.binding.fullScreenImageView)
    }

    override fun getItemCount(): Int = images.size
}

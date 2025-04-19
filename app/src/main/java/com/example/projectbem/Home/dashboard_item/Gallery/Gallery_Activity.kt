package com.example.projectbem.Home.dashboard_item.Gallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectbem.Home.dashboard_item.GalleryAdapter
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityGalleryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Gallery_Activity : AppCompatActivity() {

    lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)



        findViewById<FloatingActionButton>(R.id.fabCamera).setOnClickListener {

        }

        findViewById<FloatingActionButton>(R.id.fabUpload).setOnClickListener {

        }

        binding.toolbarGallery.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        var isFabOpen = false

        fun setupFabAnimation() {
            val fabMenu = findViewById<LinearLayout>(R.id.fabMenu)
            val fabMain = findViewById<FloatingActionButton>(R.id.fabMain)
            val fabOverlay = findViewById<View>(R.id.fabOverlay)

            fabMain.setOnClickListener {
                if (isFabOpen) {
                    fabMenu.animate().alpha(0f).translationY(100f).setDuration(200).withEndAction {
                        fabMenu.visibility = View.GONE
                    }.start()
                    fabOverlay.visibility = View.GONE
                } else {
                    fabMenu.alpha = 0f
                    fabMenu.translationY = 100f
                    fabMenu.visibility = View.VISIBLE
                    fabMenu.animate().alpha(1f).translationY(0f).setDuration(200).start()
                    fabOverlay.visibility = View.VISIBLE
                }
                isFabOpen = !isFabOpen
            }
        }

        setupFabAnimation()

        val dummyImages = listOf(
            R.drawable.nahida_post,
        )

        val adapter = GalleryAdapter(this, dummyImages)
        binding.galleryRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.galleryRecyclerView.adapter = adapter
    }

}

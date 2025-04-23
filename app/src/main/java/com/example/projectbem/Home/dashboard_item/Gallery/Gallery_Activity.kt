package com.example.projectbem.Home.dashboard_item.Gallery

import android.Manifest
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectbem.Home.dashboard_item.GalleryAdapter
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityGalleryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File


class Gallery_Activity : AppCompatActivity() {

    lateinit var binding: ActivityGalleryBinding
    private val selectedImages = mutableListOf<Uri>()
    private lateinit var adapter: GalleryAdapter

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                showConfirmationDialog(it)
            } ?: Toast.makeText(this, "Tidak ada gambar yang dipilih", Toast.LENGTH_SHORT).show()
        }

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                val uri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, it, "Photo", null))
                showConfirmationDialog(uri)
            } ?: Toast.makeText(this, "Gagal mengambil gambar", Toast.LENGTH_SHORT).show()
        }

    private val REQUEST_CODE_CAMERA = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GalleryAdapter(this, selectedImages)
        binding.galleryRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.galleryRecyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabCamera).setOnClickListener {
            checkCameraPermissionAndOpen()
        }

        findViewById<FloatingActionButton>(R.id.fabUpload).setOnClickListener {
            pickImageLauncher.launch("image/*")
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
    }
    private fun checkCameraPermissionAndOpen() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CODE_CAMERA
            )
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        takePictureLauncher.launch(null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_CAMERA) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openCamera()
            } else {
                Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showConfirmationDialog(photoUri: Uri) {
        val dialogView = layoutInflater.inflate(R.layout.pop_up, null)
        val imagePreview = dialogView.findViewById<ImageView>(R.id.imagePreview)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnUpload = dialogView.findViewById<Button>(R.id.btnUpload)

        imagePreview.setImageURI(photoUri)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnUpload.setOnClickListener {
            selectedImages.add(photoUri)
            adapter.notifyItemInserted(selectedImages.size - 1)
            val fabMenu = findViewById<LinearLayout>(R.id.fabMenu)
            val fabOverlay = findViewById<View>(R.id.fabOverlay)

            fabMenu.animate().alpha(0f).translationY(100f).setDuration(200).withEndAction {
                fabMenu.visibility = View.GONE
            }.start()
            fabOverlay.visibility = View.GONE
            dialog.dismiss()
        }
        dialog.show()
    }
}

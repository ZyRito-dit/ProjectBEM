package com.example.projectbem.Home.dashboard_item.Gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.Home.dashboard_item.GalleryAdapter
import com.example.projectbem.databinding.ActivityGalleryBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

class Gallery_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var galleryAdapter: GalleryAdapter
    private val REQUEST_CODE_IMAGE_PICK = 1001
    private var isFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("GalleryActivity", "onCreate: Anda berada di halaman gallery")

        // Setup ViewModel
        val repository = GalleryRepository(ApiConfig.getApiService(this))
        galleryViewModel = ViewModelProvider(this, GalleryViewModelFactory(repository))
            .get(GalleryViewModel::class.java)

        // Setup RecyclerView
        galleryAdapter = GalleryAdapter(this, emptyList())
        binding.galleryRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.galleryRecyclerView.adapter = galleryAdapter

        // Observe Data
        galleryViewModel.galleryList.observe(this) {
            galleryAdapter.updateData(it)
        }

        galleryViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBarrecyclerEvent.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        galleryViewModel.errorMessage.observe(this) { error ->
            error?.let {
                Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }

        // Get initial gallery
        galleryViewModel.getGallery()

        binding.toolbarGallery.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Role check for FAB
        val role = getSharedPreferences("APP_PREF", MODE_PRIVATE).getString("ROLE", "user")
        if (role != "superadmin") {
            binding.fabMain.visibility = View.GONE
            binding.fabUpload.visibility = View.GONE
            binding.fabCamera.visibility = View.GONE
            binding.fabMenu.visibility = View.GONE
            binding.fabOverlay.visibility = View.GONE
        } else {
            setupFabAnimation(binding.fabMain, binding.fabMenu, binding.fabOverlay)
        }

        binding.fabUpload.setOnClickListener {
            Log.d("GalleryActivity", "FAB Upload diklik")
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png", "image/jpg"))
            }
            startActivityForResult(intent, REQUEST_CODE_IMAGE_PICK)
        }

        binding.fabCamera.setOnClickListener {
            // TODO: Implementasi kamera kalau perlu
        }
    }

    private fun setupFabAnimation(fabMain: FloatingActionButton, fabMenu: LinearLayout, fabOverlay: View) {
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



    private fun uploadImageWithText(
        imagePart: MultipartBody.Part,
        judul: RequestBody,
        deskripsiSingkat: RequestBody,
        deskripsiPanjang: RequestBody
    ) {
        val apiService = ApiConfig.getApiService(this)

        lifecycleScope.launch {
            Log.d("UploadDebug", "Mulai proses upload...")
            Log.d("UploadDebug", "Nama file: ${imagePart.body.contentType()} - ${imagePart.headers}")
            Log.d("UploadDebug", "Judul: ${judul.contentLength()}, DeskripsiSingkat: ${deskripsiSingkat.contentLength()}, DeskripsiPanjang: ${deskripsiPanjang.contentLength()}")

            try {
                val response = apiService.uploadImageGallery(imagePart, judul, deskripsiSingkat, deskripsiPanjang)

                Log.d("UploadDebug", "Status Code: ${response.code()}")
                Log.d("UploadDebug", "Response Message: ${response.message()}")

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("UploadDebug", "Upload berhasil. Response body: $responseBody")
                    Toast.makeText(this@Gallery_Activity, "Berhasil tambah gambar", Toast.LENGTH_SHORT).show()
                    galleryViewModel.getGallery()
                } else {
                    val errorMsg = response.errorBody()?.string()
                    Log.e("UploadError", "Upload gagal. ErrorBody: $errorMsg")
                    Toast.makeText(this@Gallery_Activity, "Gagal upload: $errorMsg", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e("UploadError", "Exception terjadi: ${e.localizedMessage}", e)
                Toast.makeText(this@Gallery_Activity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchGalleryList() {
        galleryViewModel.getGallery() // Ini cukup karena observer udah setup
    }

    // Tambahkan ini di dalam GalleryActivity
    private fun uploadGallery(file: File, judulText: String, deskripsiSingkatText: String, deskripsiPanjangText: String) {
        val judul = judulText.toRequestBody("text/plain".toMediaTypeOrNull())
        val deskripsiSingkat = deskripsiSingkatText.toRequestBody("text/plain".toMediaTypeOrNull())
        val deskripsiPanjang = deskripsiPanjangText.toRequestBody("text/plain".toMediaTypeOrNull())

        val requestImage = MultipartBody.Part.createFormData(
            "image",
            file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )



        lifecycleScope.launch {
            try {
                val response = ApiConfig.getApiService(this@Gallery_Activity)
                    .uploadImageGallery(requestImage, judul, deskripsiSingkat, deskripsiPanjang)

                if (response.isSuccessful) {
                    Toast.makeText(this@Gallery_Activity, "Berhasil upload", Toast.LENGTH_SHORT).show()
                    fetchGalleryList() // Refresh list galeri setelah upload
                } else {
                    Toast.makeText(this@Gallery_Activity, "Upload gagal: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@Gallery_Activity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_IMAGE_PICK && resultCode == RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                try {
                    val inputStream = contentResolver.openInputStream(imageUri)
                    val file = File(cacheDir, "upload_image.jpg")

                    inputStream?.use { input ->
                        FileOutputStream(file).use { output ->
                            input.copyTo(output)
                        }
                    }

                    Log.d("UploadDebug", "File dipersiapkan: ${file.name}, size: ${file.length()} bytes")

                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

                    val emptyText = "".toRequestBody("text/plain".toMediaTypeOrNull())

                    uploadImageWithText(imagePart, emptyText, emptyText, emptyText)
                } catch (e: Exception) {
                    Log.e("UploadError", "Error saat menyiapkan file: ${e.localizedMessage}")
                    Toast.makeText(this, "Gagal membaca file gambar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Gagal memilih gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }



}

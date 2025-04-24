package com.example.projectbem.Drawer.Profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.ResponseUpdateProfile
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.databinding.ActivityEdtBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class EdtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEdtBinding
    private var selectedImageUri: Uri? = null

    companion object {
        private const val REQUEST_IMAGE_PICK = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")
        val imageUrl = intent.getStringExtra("IMAGE_URL")

        binding.etName.setText(username)

        if (!imageUrl.isNullOrBlank()) {
            Glide.with(this)
                .load(imageUrl)
                .into(binding.imgProfile)
        }

        binding.btnChangePhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        binding.btnSave.setOnClickListener {
            updateProfile()
        }

        binding.btnedtbackprofile.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            selectedImageUri?.let {
                Glide.with(this)
                    .load(it)
                    .into(binding.imgProfile)
            }
        }
    }

    private fun updateProfile() {
        val newUsername = binding.etName.text.toString().trim()

        if (newUsername.isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val usernameRequest = newUsername.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = selectedImageUri?.let {
            val file = uriToFile(it)
            val requestImage = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", file.name, requestImage)
        }

        if (imagePart != null) {
            ApiConfig.getApiService(this).updateProfileWithImage(usernameRequest, imagePart)
                .enqueue(object : Callback<ResponseUpdateProfile> {
                    override fun onResponse(call: Call<ResponseUpdateProfile>, response: Response<ResponseUpdateProfile>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@EdtActivity, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@EdtActivity, "Gagal memperbarui profil", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseUpdateProfile>, t: Throwable) {
                        Toast.makeText(this@EdtActivity, "Kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun uriToFile(uri: Uri): File {
        val file = File(cacheDir, "profile_photo.jpg")
        contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        return file
    }
}

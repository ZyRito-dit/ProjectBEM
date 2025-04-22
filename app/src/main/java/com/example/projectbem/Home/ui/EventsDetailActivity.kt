package com.example.projectbem.Home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.projectbem.Data.response.event.DataItem
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityEventsDetailBinding
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class EventsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventsDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarDetail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Event"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        showLoading(false)

        val event = intent.getParcelableExtra<DataItem>("EVENT_DATA")
        event?.let {
            binding.tvJudul.text = it.judulKegiatan
            binding.tvSummary.text = it.ringkasan
            binding.tvOwner.text = it.penanggungJawab
            binding.tvKategori.text = it.kategori
            binding.tvKota.text = "📍 ${it.lokasiKegiatan}"
            binding.tvTanggal.text = "📆 ${formatDate(it.waktuMulai!!)} - ${formatDate(it.waktuSelesai!!)}"
            binding.tvWaktu.text = "⏰ ${formatTime(it.waktuMulai)} - ${formatTime(it.waktuSelesai)}"
            binding.tvQuota.text = "Batas Peserta: ${it.kuotaPeserta}"
            binding.tvDeskripsi.text = "${it.deskripsiLengkap}\n\nLink Pendaftaran: ${it.linkPendaftaran}"

            Glide.with(this)
                .load(it.gambarCover)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(binding.ivFoto)
        }
    }

    private fun formatDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // format Z itu UTC
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        return try {
            val parsedDate = inputFormat.parse(date)
            outputFormat.format(parsedDate!!)
        } catch (e: Exception) {
            date
        }
    }

    private fun formatTime(dateTimeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("HH:mm", Locale("id", "ID")) // 24 jam

        return try {
            val date = inputFormat.parse(dateTimeString)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            dateTimeString
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
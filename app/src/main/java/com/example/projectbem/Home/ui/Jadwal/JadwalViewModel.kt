package com.example.projectbem.Home.ui.Jadwal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectbem.Api.jadwal

class JadwalViewModel : ViewModel() {


    private val _listJadwal = MutableLiveData<List<jadwal>>()
    val listJadwal: LiveData<List<jadwal>> = _listJadwal

        init {
            _listJadwal.value = listOf(
                jadwal(
                    namaKegiatan = "shalat Subuh",
                    deskripsi = "Waktu Subuh, ayo shalat berjamaah!",
                    namaDivisi = "Ibadah",
                    jamMulai = "04:30",
                    jamSelesai = "05:00",
                ),
                jadwal(
                    namaKegiatan = "Shalat Dzuhur",
                    deskripsi = "Dzuhur Bro, Berhenti Dulu Ngodingnya",
                    namaDivisi = "Ibadah",
                    jamMulai = "11:58",
                    jamSelesai = "12:30",
                ),
                jadwal(
                    namaKegiatan = "Shalat Asar",
                    deskripsi = "Asar Bro,Habis Ini Lanjut Kuliah Pesantren ya!",
                    namaDivisi = "Ibadah",
                    jamMulai = "15:15",
                    jamSelesai = "15:45",
                ),
                jadwal(
                    namaKegiatan = "Shalat Maghrib",
                    deskripsi = "Maghrib",
                    namaDivisi = "Ibadah",
                    jamMulai = "17:58",
                    jamSelesai = "19:00",
                ),
                jadwal(
                    namaKegiatan = "Shalat Subuh",
                    deskripsi = "Waktu Subuh, ayo shalat berjamaah!",
                    namaDivisi = "Ibadah",
                    jamMulai = "19:10",
                    jamSelesai = "19:40",
                )
            )
        }

    fun updateJadwal(newList: List<jadwal>) {
        _listJadwal.value = newList
    }
}

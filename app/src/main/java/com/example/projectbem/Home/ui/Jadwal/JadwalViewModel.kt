package com.example.projectbem.Home.ui.Jadwal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectbem.Data.Jadwal

class JadwalViewModel : ViewModel() {


    private val _listJadwal = MutableLiveData<List<Jadwal>>()
    val listJadwal: LiveData<List<Jadwal>> = _listJadwal

        init {
            _listJadwal.value = listOf(
                Jadwal(
                    namaKegiatan = "shalat Subuh",
                    deskripsi = "Waktu Subuh, ayo shalat berjamaah!",
                    namaDivisi = "Ibadah",
                    jamMulai = "04:30",
                    jamSelesai = "05:00",
                ),
                Jadwal(
                    namaKegiatan = "Shalat Dzuhur",
                    deskripsi = "Dzuhur Bro, Berhenti Dulu Ngodingnya",
                    namaDivisi = "Ibadah",
                    jamMulai = "11:58",
                    jamSelesai = "12:30",
                ),
                Jadwal(
                    namaKegiatan = "Shalat Asar",
                    deskripsi = "Asar Bro,Habis Ini Lanjut Kuliah Pesantren ya!",
                    namaDivisi = "Ibadah",
                    jamMulai = "15:15",
                    jamSelesai = "15:45",
                ),
                Jadwal(
                    namaKegiatan = "Shalat Maghrib",
                    deskripsi = "Maghrib",
                    namaDivisi = "Ibadah",
                    jamMulai = "17:58",
                    jamSelesai = "19:00",
                ),
                Jadwal(
                    namaKegiatan = "Shalat Subuh",
                    deskripsi = "Waktu Subuh, ayo shalat berjamaah!",
                    namaDivisi = "Ibadah",
                    jamMulai = "19:10",
                    jamSelesai = "19:40",
                )
            )
        }

    fun updateJadwal(newList: List<Jadwal>) {
        _listJadwal.value = newList
    }
}

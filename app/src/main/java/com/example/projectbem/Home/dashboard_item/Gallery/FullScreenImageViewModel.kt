package com.example.projectbem.Home.dashboard_item.Gallery

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class FullScreenImageViewModel : ViewModel() {

    // LiveData untuk image URLs, posisi terpilih, tanggal, dan waktu
    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> get() = _imageUrls

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int> get() = _selectedPosition

    private val _imageDate = MutableLiveData<String>()
    val imageDate: LiveData<String> get() = _imageDate

    private val _imageTime = MutableLiveData<String>()
    val imageTime: LiveData<String> get() = _imageTime

    // List untuk menyimpan ID tiap gambar
    private val imageIdList = mutableListOf<String>()

    fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }

    // Set semua data ke LiveData dan list
    fun setData(
        imageUrls: List<String>,
        selectedPos: Int,
        date: String,
        time: String
    ) {
        _imageUrls.value = imageUrls
        _selectedPosition.value = selectedPos
        _imageDate.value = date
        _imageTime.value = time
    }

    fun setImageIdList(ids: List<String>) {
        imageIdList.clear()
        imageIdList.addAll(ids)
    }

    fun getImageIdAt(position: Int): String {
        return imageIdList.getOrNull(position) ?: ""
    }

    fun deleteGallery(context: Context, id: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val api = ApiConfig.getApiService(context)
                val response = api.deleteGallery(id)
                callback(response.isSuccessful)
            } catch (e: Exception) {
                callback(false)
            }
        }
    }
}


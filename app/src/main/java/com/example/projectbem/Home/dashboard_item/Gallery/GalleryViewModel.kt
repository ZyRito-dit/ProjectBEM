package com.example.projectbem.Home.dashboard_item.Gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.response.ResponseGalleryItem
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class GalleryViewModel(private val repository: GalleryRepository) : ViewModel() {

    private val _galleryList = MutableLiveData<List<ResponseGalleryItem>>()
    val galleryList: LiveData<List<ResponseGalleryItem>> = _galleryList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun getGallery() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.fetchGallery()
                _galleryList.postValue(result)
            } catch (e: Exception) {
                Log.e("GalleryViewModel", "Error: ${e.message}")
                _errorMessage.postValue(e.message ?: "Unexpected Error")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }



}

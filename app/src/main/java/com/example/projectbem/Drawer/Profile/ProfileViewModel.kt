package com.example.projectbem.Drawer.Profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.repository.ProfileRepository
import com.example.projectbem.Data.response.ProfileResponse
import kotlinx.coroutines.launch


class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _profileLiveData = MutableLiveData<ProfileResponse>()
    val profileLiveData: LiveData<ProfileResponse> = _profileLiveData



    fun loadProfile() {
        viewModelScope.launch {
            try {
                val response = repository.getProfile()
                if (response.isSuccessful && response.body() != null) {
                    _profileLiveData.postValue(response.body())
                } else {
                    Log.e("ProfileViewModel", "Gagal ambil profile: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error: ${e.message}")
            }
        }
    }
}


package com.example.projectbem.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectbem.Data.response.divisi.AlatResponseItem
import com.example.projectbem.Data.response.divisi.DataMenu
import com.example.projectbem.Data.response.divisi.MakananResponseItem
import com.example.projectbem.Data.response.divisi.Notulen
import com.example.projectbem.Data.response.divisi.ObatResponseItem
import com.example.projectbem.Data.response.divisi.PendidikanResponseItem
import com.example.projectbem.Data.response.divisi.SakitResponse
import com.example.projectbem.Data.response.event.DataItem
import com.example.projectbem.Data.response.user.LoginRequest
import com.example.projectbem.Data.room.BemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class UsersViewModel(private val repository: BemRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<Result<BemEntity>>(Result.Loading)
    val loginState: StateFlow<Result<BemEntity>> = _loginState

    private val _userData = MutableStateFlow<BemEntity?>(null)

    fun loginUser(username: String, nim: String) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username = username, nim = nim)
                val loginResponse = repository.login(loginRequest)

                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.token ?: return@launch

                    repository.saveUserData(BemEntity(
                        token = token,
                        role = "", username = null, nim = null, image = null, divisi = null
                    ))

                    val profileResponse = repository.getProfile(token)
                    if (profileResponse.isSuccessful) {
                        val profile = profileResponse.body()

                        val userEntity = BemEntity(
                            token = token,
                            role = profile?.role ?: "",
                            username = profile?.username,
                            nim = profile?.nim,
                            image = profile?.image,
                            divisi = profile?.divisi
                        )

                        repository.saveUserData(userEntity)
                        _loginState.value = Result.Success(userEntity)
                    } else {
                        _loginState.value = Result.Error("Gagal mengambil data profil: ${profileResponse.message()}")
                    }
                } else {
                    val errorBody = loginResponse.errorBody()?.string()
                    val errorMessage = if (!errorBody.isNullOrEmpty()) {
                        try {
                            JSONObject(errorBody).getString("message")
                        } catch (e: Exception) {
                            "Gagal melakukan login: ${loginResponse.message()}"
                        }
                    } else {
                        "Gagal melakukan login: ${loginResponse.message()}"
                    }
                    _loginState.value = Result.Error(errorMessage) // Gunakan pesan error spesifik
                }
            } catch (e: Exception) {
                _loginState.value = Result.Error("Terjadi kesalahan: ${e.message}")
            }
        }
    }

    suspend fun getUserNow(): BemEntity? {
        return repository.getUserData()
    }

    fun logoutUser() {
        viewModelScope.launch {
            repository.logout()
            _userData.value = null
        }
    }

    fun getToken(): LiveData<BemEntity?> {
        val tokenLiveData = MutableLiveData<BemEntity?>()
        viewModelScope.launch {
            tokenLiveData.postValue(repository.getUserData())
        }
        return tokenLiveData
    }

    private val _userProfile = MutableLiveData<BemEntity?>()
    val userProfile: LiveData<BemEntity?> = _userProfile

    private var currentToken: String? = null

    fun setToken(token: String) {
        currentToken = token
    }

    fun getProfile() {
        viewModelScope.launch {
            try {
                currentToken?.let { token ->
                    val response = repository.getProfile(token)
                    if (response.isSuccessful) {
                        val profile = response.body()
                        val entity = BemEntity(
                            token = token,
                            role = profile?.role ?: "",
                            username = profile?.username,
                            nim = profile?.nim,
                            image = profile?.image,
                            divisi = profile?.divisi
                        )
                        _userProfile.postValue(entity)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private val _pendidikanBarang = MutableLiveData<List<PendidikanResponseItem>>()
    val pendidikanBarang: LiveData<List<PendidikanResponseItem>> = _pendidikanBarang

    fun getPendidikanBarang(token: String) {
        viewModelScope.launch {
            val response = repository.getPendidikanBarang(token)
            response?.let {
                _pendidikanBarang.value = it
            }
        }
    }

    private val _events = MutableLiveData<List<DataItem>>()
    val events: LiveData<List<DataItem>> = _events

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAllEvents()
                if (response.success == true) {
                    _events.value = response.data?.filterNotNull()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
    private val _notulenList = MutableLiveData<List<Notulen>>()
    val notulenList: LiveData<List<Notulen>> get() = _notulenList

    private val _error = MutableLiveData<String>()

    fun fetchNotulenByBulan(bulan: String) {
        viewModelScope.launch {
            try {
                val result = repository.getNotulenByBulan(bulan)
                _notulenList.value = result
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    private val _piketList = MutableLiveData<List<com.example.projectbem.Data.response.divisi.DataItem>>()
    val piketList: LiveData<List<com.example.projectbem.Data.response.divisi.DataItem>> get() = _piketList

    fun fetchPiketMakan() {
        viewModelScope.launch {
            val result = repository.getAllPiketMakan()
            result?.let {
                _piketList.value = it.filterNotNull()
            }
        }
    }

    private val _menuList = MutableLiveData<List<DataMenu>>()
    val menuList: LiveData<List<DataMenu>> get() = _menuList

    fun fetchMenu() {
        viewModelScope.launch {
            val result = repository.getAllMenuMakan()
            result?.let {
                _menuList.value = it.filterNotNull()
            }
        }
    }

    private val _makananList = MutableLiveData<List<MakananResponseItem>>()
    val makananList: LiveData<List<MakananResponseItem>> = _makananList

    fun fetchMakanan() {
        viewModelScope.launch {
            val result = repository.getAllMakan()
            result?.let { _makananList.value = it.filterNotNull() }
        }
    }

    private val _obatList = MutableLiveData<List<ObatResponseItem>>()
    val obatList: LiveData<List<ObatResponseItem>> = _obatList

    fun  fetchObat(){
        viewModelScope.launch {
            val result = repository.getAllObat()
            result?.let { _obatList.value = it.filterNotNull() }
        }
    }
    private val _pasienList = MutableLiveData<List<SakitResponse>>()
    val pasienList: LiveData<List<SakitResponse>> = _pasienList

    fun fetchPasien(){
        viewModelScope.launch {
            val result = repository.getAllPasien()
            result?.let { _pasienList.value = it }
        }
    }
    private val _alatList = MutableLiveData<List<AlatResponseItem>>()
    val alatList: LiveData<List<AlatResponseItem>> = _alatList

    fun fetchAlat(){
        viewModelScope.launch {
            val result = repository.getAllAlat()
            result?.let { _alatList.value = it.filterNotNull() }
        }
    }
}